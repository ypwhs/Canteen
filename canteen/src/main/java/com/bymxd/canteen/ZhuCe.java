package com.bymxd.canteen;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import top.lizy.bayi.data.*;
import top.lizy.jsonz.data.*;
import top.lizy.jsonz.client.*;
import top.lizy.jsonz.client.handler.*;
import android.view.*;
import android.widget.EditText;

public class ZhuCe extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);

    }

    public void denglu(View v){
        RegReq_c c = new RegReq_c();
        EditText phone = (EditText)findViewById(R.id.denglu_phone);
		c.phone = new PhoneNumber(phone.getText().toString());
        try{
            c.Validate();
            JSONZ.call("RegReq", c, new CallHandler() {

                @Override
                public void success(Data c, DataR r, Object att) {
                    DataR asd = r;
                    System.out.println(r);
                }

                @Override
                public void fail(Data c, Object att) {
                    System.out.println(fr);
                }

            });
        }catch (Exception e){
            new AlertDialog.Builder(this)
                    .setTitle("提示")
                    .setMessage("手机号不正确")
                    .setPositiveButton("确定", null)
                    .show();
        }

    }

}