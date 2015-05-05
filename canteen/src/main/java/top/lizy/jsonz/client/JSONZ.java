package top.lizy.jsonz.client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

import top.lizy.bayi.data.*;
import top.lizy.jsonz.Spec;
import top.lizy.jsonz.Status;
import top.lizy.jsonz.TaskType;
import top.lizy.jsonz.client.handler.CallHandler;
import top.lizy.jsonz.client.handler.FailReason;
import top.lizy.jsonz.data.Data;
import top.lizy.jsonz.data.DataR;
import top.lizy.jsonz.data.PhoneNumber;
import top.lizy.jsonz.util.UUIDManager;

public class JSONZ {

	/**
	 * @MT
	 */
	static public ExecutorService es = Executors.newCachedThreadPool();
	
	static protected class Caller implements Runnable{

		public String api;
		
		public Data c;
		
		public DataR r = null;
		
		public CallHandler h;
		
		public Object att;
		
		public ByteBuffer bout;
		
		public byte[] bin;
		
		public Caller(String api, Data c, CallHandler h, Object att){
			if(api.getBytes(Spec.cs4n).length > 19){
				throw new IllegalArgumentException("API name '"+api+"' is too long.");
			}
			
			this.api = api;
			this.c = c;
			this.h = h;
			this.att = att;
		}
		
		@Override
		public void run() {
			try {
				cons_bout();
			} catch (IOException e) {
				e.printStackTrace();
				h.fr = FailReason.INTERNAL;
				h.fail(c, att);
			}
			
			Socket s;
			try {
				s = new Socket(saddr, sport);
				
				OutputStream out = s.getOutputStream();
				out.write(bout.array());
				out.flush();
				
				InputStream in = s.getInputStream();
				DataInputStream di = new DataInputStream(in);
				// status code
				byte status = di.readByte();
				switch(status){
				case Status.SERVER_ERROR:
					h.fr = FailReason.SERVER;
					h.fail(c, att);
					break;
				case Status.VALIDATE_ERROR:
					h.fr = FailReason.VALIDATE;
					h.fail(c, att);
					break;
				case Status.OK:
					// ret length
					int rlen = di.readInt();
					byte[] bin = new byte[rlen];
					di.readFully(bin);
					ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bin));
					r = (DataR)ois.readObject();
					h.success(c, r, att);
					break;
				}
			} catch (IOException e) {
				e.printStackTrace(System.out);
				h.fr = FailReason.NET;
				h.fail(c, att);
			} catch (ClassNotFoundException e) {
				e.printStackTrace(System.out);
				h.fr = FailReason.VERSION;
				h.fail(c, att);
			}
		}
		
		protected void cons_bout() throws IOException{
			ByteArrayOutputStream bs = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bs);
			oos.writeObject(c);
			oos.flush();
			byte[] ba = bs.toByteArray();
			
			byte[] b = new byte[Spec.HLEN + ba.length];
			bout = ByteBuffer.wrap(b);
			bout.put(TaskType.APICALL);
			bout.put(UUIDManager.toBytes(ses));
			bout.putInt(ba.length);
			byte spc = 0x20;
			byte[] bapi = api.getBytes(Spec.cs4n);
			bout.put(bapi);
			while(bout.position() < 40)
				bout.put(spc);
			bout.put(ba);
		}
	}
	
	static public InetAddress saddr;
	
	static public int sport = 19696;
	
	static public UUID ses;
	
	static public void init(UUID session) throws UnknownHostException{
		ses = session;
		saddr = InetAddress.getByName("127.0.0.1");
	}
	
	/**
	 * Equivalent to call(API, c, null, h).
	 * @param API
	 * @param c
	 * @param h
	 */
	static public void call(String api, Data c, CallHandler h){
		call(api, c, null, h);
	}
	
	/**
	 * 
	 * @param api
	 * @param c
	 * @param att
	 * @param h
	 * @throws RejectedExecutionException 
	 * If the calling cannot be processed. Client may try again. 
	 */
	static public void call(String api, Data c, Object att, CallHandler h) throws RejectedExecutionException{
		synchronized(es){
			es.execute(new Caller(api, c, h, att));
		}
	}
	
	public static UUID x = UUIDManager.gen();
	
	public static void main(String[] args) throws UnknownHostException{
		init(x);
		
//		RegReq_c c = new RegReq_c();
//		c.phone = new PhoneNumber("15965196928");
//		call("RegReq", c, new CallHandler(){
//
//			@Override
//			public void success(Data c, DataR r, Object att) {
//				System.out.println(r);
//			}
//
//			@Override
//			public void fail(Data c, Object att) {
//				System.out.println(fr);
//			}
//			
//		});
		
		
		call("SesSta", new Data(), new CallHandler(){

			@Override
			public void success(Data c, DataR r, Object att) {
				System.out.println(r);
			}

			@Override
			public void fail(Data c, Object att) {
				System.out.println(fr);
			}
			
		});
		
		System.out.println("GoodBye");
		es.shutdown();
	}
	
}
