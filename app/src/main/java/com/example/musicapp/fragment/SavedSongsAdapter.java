package com.example.musicapp.fragment;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

import java.util.ArrayList;

public class SavedSongsAdapter extends BaseAdapter {
    private TextView id, title, artist;
    final ArrayList<Song> songs1;
    private Context context;
    private static ExoPlayer player;
    private static ArrayList<Boolean> checker = new ArrayList<>();

    public SavedSongsAdapter(ArrayList<Song> songs1, Context context) {
        this.context = context;
        this.songs1 = songs1;
    }

    @Override
    public int getCount() {
        return songs1.size();
    }

    @Override
    public Object getItem(int i) {
        return songs1.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        for (int j = 0; j < songs1.size(); j++) {
            checker.add(j, true);
        }
        //Generate music player

        player = new ExoPlayer.Builder(context).build();
        View rowView = view.inflate(viewGroup.getContext(), R.layout.saved_songs_item, null);
        id = rowView.findViewById(R.id.song_id);
        title = rowView.findViewById(R.id.song_title);
        artist = rowView.findViewById(R.id.song_artist);
        id.setText(String.valueOf(songs1.get(i).get_ID()));
        title.setText(songs1.get(i).getTitle());
        artist.setText(songs1.get(i).getArtist());
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*                *//*openSelectDialog(songs1.get(i));*//*
                Log.e("Log: ", songs1.get(i).getPath());
                //play song
                if (checker.get(i)) {
                    prepareSong(songs1.get(i).getPath(), context, i, songs1.size());
                    Log.e("Prepare song: ", songs1.get(i).getTitle());
                }
                if (getSongState()) {
                    Log.e("Song: ", songs1.get(i).getTitle() + " has played");
                    player.setPlayWhenReady(true);
                    player.getPlaybackState();
                } else {
                    Log.e("Song: ", songs1.get(i).getTitle() + " has stopped");
                    player.setPlayWhenReady(false);
                    player.getPlaybackState();
                }     */
                Log.e("Service: ", "Run");
                AppCompatActivity activity = (AppCompatActivity) context;
                Intent intent = new Intent(context, musicService.class);
                intent.putExtra("song", songs1.get(i));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    activity.startForegroundService(intent);
                }
                {
                    activity.startService(intent);
                }
            }
        });
        return rowView;
    }

    public void musicServiceController() {


    }

    public void openSelectDialog(Song song) {
        //Open Dialog fragment here
        AppCompatActivity activity = (AppCompatActivity) context;
        SelectedSong fragment = new SelectedSong();
        fragment.setSong(song);
        activity.getSupportFragmentManager().beginTransaction().add(fragment, "Selected song").commit();
    }

    //Music service
    //prepare song here
    public static void prepareSong(String path, Context context, int i, int size) {
        StyledPlayerControlView view = new StyledPlayerControlView(context);
        Uri uri = Uri.parse(path);
        MediaItem mediaItem = MediaItem.fromUri(uri);
        player.setMediaItem(mediaItem);
        player.prepare();
        for (int j = 0; j < size; j++) {
            checker.set(j, true);
        }
        checker.set(i, false);
        /*            player.play();*/
    }

    public boolean getSongState() {
        if (player.isPlaying()) {
            return false;
        }
        return true;
    }

}

class musicService extends Service {

    private ExoPlayer player;
    private Song song;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
     super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Create notification
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), "1")
                .setContentTitle("Foreground Service")
                .setContentText("text")
                .setSmallIcon(R.drawable.ic_storage)
                .setContentIntent(pendingIntent)
                .build();
        //play music
        song = (Song) intent.getSerializableExtra("song");
        player = new ExoPlayer.Builder(getApplicationContext()).build();
        Uri uri = Uri.parse(song.getPath());
        MediaItem mediaItem = MediaItem.fromUri(uri);
        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
        Log.e("Service: ", "Started");
        startForeground(1, notification);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        super.onDestroy();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    "1",
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }

}

