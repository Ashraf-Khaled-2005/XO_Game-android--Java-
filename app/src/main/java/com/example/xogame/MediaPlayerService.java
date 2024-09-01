package com.example.xogame;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class MediaPlayerService extends Service {
    private MediaPlayer player;



    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(this, R.raw.music); // Your music file
        player.setLooping(true); // Loop the music
        Log.d("MediaPlayerService", "Service Created");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player.start();
        Log.d("MediaPlayerService", "Music Started");
        return START_STICKY;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null) {
            player.stop();
            player.release();
        }
        Log.d("MediaPlayerService", "Service Destroyed");
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
