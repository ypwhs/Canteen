package com.bymxd.canteen;

import top.lizy.bayi.data.RegReq_c;
import top.lizy.jsonz.client.JSONZ;
import top.lizy.jsonz.client.handler.CallHandler;
import top.lizy.jsonz.data.Data;
import top.lizy.jsonz.data.DataR;
import top.lizy.jsonz.data.PhoneNumber;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DengLu extends Activity {
    Common common;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denglu);
        common = new Common(this);
    }

    public void getYzm(View v) {
        RegReq_c c = new RegReq_c();
        EditText phone = (EditText) findViewById(R.id.denglu_phone);
        c.phone = new PhoneNumber(phone.getText().toString());
        try {
            c.Validate();
            JSONZ.call("RegReq", c, new CallHandler() {
                @Override
                public void success(Data c, DataR r, Object att) {
                    System.out.println(r);
                    common.solveDataR(r);
                }

                @Override
                public void fail(Data c, Object att) {
                    System.out.println(fr);
                    common.solveFailReason(fr);
                }

            });
        } catch (Exception e) {
            common.alert("提示", "手机号格式不正确");
        }

    }

    public void denglu(View v) {

    }

}