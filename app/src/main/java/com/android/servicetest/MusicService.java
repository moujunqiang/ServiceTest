package com.android.servicetest;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;


public class MusicService extends Service {
    private String path = "mnt/sdcard/123.mp3";
    private MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        player = new MediaPlayer();
        try {
            player.setDataSource(path);
            //准备资源
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("服务", "准备播放音乐");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyMusicBinder();
    }


    //该方法包含关于歌曲的操作
    public class MyMusicBinder extends Binder {

        //判断是否处于播放状态
        public boolean isPlaying() {
            return player.isPlaying();
        }

        //播放或暂停歌曲
        public void play() {
            if (player.isPlaying()) {
                player.pause();
            } else {
                player.start();
            }
            Log.e("服务", "播放音乐");
        }


    }
}
