package com.android.servicetest;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class UpdateService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        startTimer();

    }

    private Handler handler = new Handler();

    private Runnable task = new Runnable() {
        public void run() {
            // TODOAuto-generated method stub
            handler.postDelayed(this, 10 * 1000);//设置延迟时间，此处是10秒
            //需要执行的代码
            Log.e("service","有更新了。。。");
        }
    };

    public void startTimer() {
        handler.removeCallbacks(task);
        handler.post(task);//立即调用
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        handler.removeCallbacks(task);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
