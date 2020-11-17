package com.android.servicetest;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 打开更新服务
     */
    private Button mBtn1;
    /**
     * 关闭更新服务
     */
    private Button mBtn2;
    /**
     * 打开音乐服务
     */
    private Button mBtn3;
    /**
     * 关闭音乐服务
     */
    private Button mBtn4;
    private Intent service;
    ServiceConnection sc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn1.setOnClickListener(this);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn2.setOnClickListener(this);
        mBtn3 = (Button) findViewById(R.id.btn3);
        mBtn3.setOnClickListener(this);
        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn4.setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn1:
                service = new Intent(MainActivity.this, UpdateService.class);
                startService(service);
                break;
            case R.id.btn2:
                if (service != null) {
                    stopService(service);
                }
                break;
            case R.id.btn3:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, MusicService.class);
                sc = new ServiceConnection() {
                    @Override
                    public void onServiceConnected(ComponentName name, IBinder service) {
                        MusicService.MyMusicBinder myMusicBinder = ((MusicService.MyMusicBinder) service);
                        if (myMusicBinder != null) {
                            myMusicBinder.play();

                        }
                    }

                    @Override
                    public void onServiceDisconnected(ComponentName name) {

                    }
                };
                bindService(intent, sc, BIND_AUTO_CREATE);
                break;
            case R.id.btn4:
                if (sc != null) {
                    unbindService(sc);
                }
                break;
            case R.id.btn5:
                startService(new Intent(MainActivity.this, MyIntentService.class));
                break;
        }
    }
}