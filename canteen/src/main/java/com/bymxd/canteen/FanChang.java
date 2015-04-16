package com.bymxd.canteen;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class FanChang extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fanchang);
	}

	int i = 0;

	public void add(View view) {
		View v = LayoutInflater.from(this).inflate(R.layout.card_cai, null);
		LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.layout1);
		relativeLayout.addView(v);

		Button button = (Button) v.findViewById(R.id.cai_button_add);
		button.setTag(i);
		TextView textView = (TextView) v.findViewById(R.id.cai_price);
		textView.setText(String.valueOf(i));
		i++;
	}

	public void add2(View view) {
		View v = LayoutInflater.from(this).inflate(R.layout.card_zhaopin, null);
		LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.layout1);
		relativeLayout.addView(v);

		TextView textView = (TextView) v.findViewById(R.id.zhaopin_gongsi);
		textView.setText("Company" + String.valueOf(i));
		i++;
	}

	public void add3(View view) {
		View v = LayoutInflater.from(this).inflate(R.layout.card_huodong, null);
		LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.layout1);
		relativeLayout.addView(v);

		TextView textView = (TextView) v.findViewById(R.id.huodong_name);
		textView.setText("Name" + String.valueOf(i));
		i++;
	}

	public void add4(View view) {
		View v = LayoutInflater.from(this).inflate(R.layout.card_gouwuche, null);
		LinearLayout relativeLayout = (LinearLayout) findViewById(R.id.layout1);
		relativeLayout.addView(v);

		TextView textView = (TextView) v.findViewById(R.id.gouwuche_name);
		textView.setText("Name" + String.valueOf(i));
		i++;
	}

	public void cai_add(View v) {
		new AlertDialog.Builder(this).setMessage(String.valueOf(v.getTag())).setPositiveButton("确定", null).show();
	}
}