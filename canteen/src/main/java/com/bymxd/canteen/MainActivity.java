package com.bymxd.canteen;

import top.lizy.jsonz.client.JSONZ;
import top.lizy.jsonz.util.UUIDManager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    JSONZ.init(UUIDManager.gen());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

    }


    public void click(View view) {
        Intent intent = new Intent();
        intent.setClass(this, DengLu.class);
        startActivity(intent);
    }

    public void click2(View view) {
        Intent intent = new Intent();
        intent.setClass(this, FanChang.class);
        startActivity(intent);
    }

    public void click3(View view) {
        Intent intent = new Intent();
        intent.setClass(this, GeRen.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
