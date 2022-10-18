package com.example.musicapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.R;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

import java.util.ArrayList;

public class SavedSongsAdapter extends BaseAdapter {
    public static ArrayList<Boolean> checker1 = new ArrayList<>();
    static boolean checkService = true;
    static boolean checkService1;
    private static ExoPlayer player;
    private static PlayerControlView playerView;
    private static ArrayList<Boolean> checker = new ArrayList<>();
    final ArrayList<Song> songs1;
    private final Context context;
    private TextView id, title, artist;
    private AppCompatActivity activity;

    public SavedSongsAdapter(ArrayList<Song> songs1, Context context) {
        this.context = context;
        this.songs1 = songs1;
    }

    public SavedSongsAdapter(ArrayList<Song> songs1, Context context, ExoPlayer player) {
        this.context = context;
        this.songs1 = songs1;
        this.player = player;
    }


    public static void getPlayerControlView(PlayerControlView playerControlView) {
        playerView = playerControlView;
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
        for (int j = 0; j < songs1.size(); j++) {
            checker1.add(j, true);
        }
        activity = (AppCompatActivity) context;
        View rowView = View.inflate(viewGroup.getContext(), R.layout.saved_songs_item, null);
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
/*                if (checker.get(i)) {
                    checkService = checker.get(i);
                    checkService1 = checker1.get(i);
                    for (int j = 0; j < songs1.size(); j++) {
                        checker1.set(j, true);
                    }
                    checker1.set(i, false);
                    intent.putExtra("song", songs1.get(i));
                    intent.putExtra("Song lists", songs1);
                    Log.e("Service: ", "Run");
                    activity.startForegroundService(intent);
                    for (int j = 0; j < songs1.size(); j++) {
                        checker.set(j, true);
                    }
                    checker.set(i, false);
                } else {
                    checkService = checker.get(i);
                    checkService1 = checker1.get(i);
                    activity.startForegroundService(intent);
                    checker.set(i, true);
                }*/

                //Set Visibility to PlayerControlView
                if (playerView.getVisibility() == View.GONE) {
                    playerView.setVisibility(View.VISIBLE);
                }
                Uri uri = Uri.parse(songs1.get(i).getPath());
                MediaItem mediaItem = MediaItem.fromUri(uri);
                player.setMediaItem(mediaItem);
                player.prepare();
                player.play();
                //Start foreground service
                Log.e("Service: ", "Started");
                Intent intent = new Intent(context.getApplicationContext(), musicService.class);
                activity.startService(intent);
            }
        });
        return rowView;
    }

}

