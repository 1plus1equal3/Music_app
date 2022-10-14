package com.example.musicapp.fragment;

import static com.example.musicapp.fragment.SavedSongsAdapter.checkService;
import static com.example.musicapp.fragment.SavedSongsAdapter.checkService1;
import static com.example.musicapp.fragment.SavedSongsAdapter.checker1;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;


import java.util.ArrayList;

public class musicService extends Service {

    private ExoPlayer player;
    private Song song;
    private ArrayList<Song> getSongPaths = new ArrayList<>();


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        player = new ExoPlayer.Builder(getApplicationContext()).build();
        super.onCreate();
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Create notification
        NotificationChannel channel = new NotificationChannel("1", "Service", NotificationManager.IMPORTANCE_DEFAULT);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, PendingIntent.FLAG_IMMUTABLE);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setContentTitle("Foreground Service")
                .setContentText("Music")
                .setSmallIcon(R.drawable.ic_storage)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        getSongPaths = (ArrayList<Song>) intent.getSerializableExtra("Song lists");
/*        Bundle bundle = intent.getExtras();
        pathLists = bundle.getStringArrayList("Song list");*/
        //play music
      /*  Bundle bundle = intent.getExtras();
        pathLists = bundle.getStringArrayList("Song list");
        player.addListener(new Player.Listener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if(playbackState == Player.STATE_ENDED) {
                int currentPosition = (int) song.get_ID();
                Uri uri = Uri.parse(pathLists.get(currentPosition+1));
                MediaItem mediaItem = MediaItem.fromUri(uri);
                player.setMediaItem(mediaItem);
                player.prepare();
                player.play();
                }
                Player.Listener.super.onPlayerStateChanged(playWhenReady, playbackState);
            }
        });*/
        if(checkService1) {
/*            Log.e("Music: ", "Prepare");
            song = (Song) intent.getSerializableExtra("song");
            int currentPosition = (int) song.get_ID();
            for(int k = currentPosition; k<getSongPaths.size(); k++){
                Uri uri = Uri.parse(getSongPaths.get(k).getPath());
                MediaItem mediaItem = MediaItem.fromUri(uri);
                player.addMediaItem(mediaItem);
            }
            player.prepare();*/
            song = (Song) intent.getSerializableExtra("song");
            Uri uri = Uri.parse(song.getPath());
            MediaItem mediaItem = MediaItem.fromUri(uri);
            player.setMediaItem(mediaItem);
            player.prepare();
        }

        if (checkService) {
            player.setPlayWhenReady(true);
            player.getPlaybackState();
            Log.e("Music: ", "Started");
/*
            checkService = false;
*/
            startForeground(2, notification);
        } else {
            player.setPlayWhenReady(false);
            player.getPlaybackState();
            Log.e("Music: ", "Paused");
            startForeground(2, notification);

/*
            checkService = true;
*/
        }
        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }

    public void endService(int flags){
        player.stop();
        stopForeground(flags);
        stopSelf();
    }

}
