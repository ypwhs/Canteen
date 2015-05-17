package top.lizy.jsonz;

import java.nio.charset.Charset;

/**
 * Specifications for JSONZ framework.
 * @author lizy
 *
 */
public class Spec {

	/**
	 * JSONZ header length in bytes. 
	 */
	public static final int HLEN = 40;
	
	/**
	 * The charset for decoding API name from JSONZ header.
	 */
	public static Charset cs4n = Charset.forName("US-ASCII"); 
}
