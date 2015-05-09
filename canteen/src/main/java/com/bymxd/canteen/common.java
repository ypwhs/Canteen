package com.bymxd.canteen;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import top.lizy.jsonz.client.handler.FailReason;
import top.lizy.jsonz.data.DataR;


/**
 * Created by yangpeiwen on 15/5/9.
 */

public class Common {
    static Activity thisActivity;

    Common(Activity act) {
        thisActivity = act;
    }

    static Toast toast;
    static String showString;

    static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    if (toast == null) toast = Toast.makeText(thisActivity,
                            showString, Toast.LENGTH_SHORT);
                    else toast.setText(showString);
                    toast.show();
                    break;
            }
        }
    }

    public void solveDataR(DataR r){
        switch(r.r){
            case DataR.PROPER:
                show("操作成功");
                break;
            case DataR.FAIL:
                show("操作失败");
                break;
            case DataR.REJECT:
                show("服务器拒绝响应");
                break;
            case DataR.TRYAGAIN:
                show("操作失败,请重试");
                break;
        }
    }

    public void solveFailReason(FailReason fr){
        if(fr==FailReason.NET){
            show("网络错误");
        }else if(fr==FailReason.SERVER) {
            show("服务器错误");
        }else if(fr==FailReason.VERSION) {
            show("当前版本错误");
        }else if(fr==FailReason.INTERNAL) {
            show("内部错误");
        }else if(fr==FailReason.VALIDATE) {
            show("验证错误");
        }else{
            show(fr.toString());
        }
    }

    Handler handler = new MyHandler();

    public void alert(String title, String message) {
        new AlertDialog.Builder(thisActivity)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("确定", null)
                .show();
    }

    public void show(String showstr) {
        showString = showstr;
        Message message = new Message();
        message.what = 1;
        handler.sendMessage(message);
    }
}
