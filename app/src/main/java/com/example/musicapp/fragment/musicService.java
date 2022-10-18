package com.example.musicapp.fragment;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.musicapp.MainActivity;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;

public class musicService extends Service {

    private static ExoPlayer player;
    private PlayerNotificationManager notificationManager;
    private final PlayerNotificationManager.MediaDescriptionAdapter mediaDescriptionAdapter = new PlayerNotificationManager.MediaDescriptionAdapter() {
        @Override
        public CharSequence getCurrentContentTitle(Player player) {
            return null;
        }

        @Nullable
        @Override
        public PendingIntent createCurrentContentIntent(Player player) {
            return null;
        }

        @Nullable
        @Override
        public CharSequence getCurrentContentText(Player player) {
            return null;
        }

        @Nullable
        @Override
        public Bitmap getCurrentLargeIcon(Player player, PlayerNotificationManager.BitmapCallback callback) {
            return null;
        }
    };


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Notification notification = new Notification();
        Log.e("Service: ", "Created");
        notificationManager = new PlayerNotificationManager.Builder(this, 1, "Channel")
                .setMediaDescriptionAdapter(mediaDescriptionAdapter)
                .build();
        notificationManager.setPlayer(player);
/*
        startForeground(1, notification);
*/
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        if(player.isPlaying()) {
            player.stop();
        }
        player.release();
        notificationManager.setPlayer(null);
        stopForeground(true);
        stopSelf();
        super.onDestroy();
    }

    public void endService(int flags) {
        player.stop();
        player.release();
        stopForeground(flags);
        stopSelf();
    }

    public static void getPlayer(ExoPlayer player) {
        musicService.player = player;
    }

}
