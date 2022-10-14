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
import com.google.android.exoplayer2.ui.StyledPlayerControlView;

import java.util.ArrayList;

public class SavedSongsAdapter extends BaseAdapter {
    static boolean checkService = true;
    private static ExoPlayer player;
    private static ArrayList<Boolean> checker = new ArrayList<>();
    public static ArrayList<Boolean> checker1 = new ArrayList<>();
    static boolean checkService1;
    final ArrayList<Song> songs1;
    private TextView id, title, artist;
    private final Context context;
    private Intent intent;
    private AppCompatActivity activity;

    public SavedSongsAdapter(ArrayList<Song> songs1, Context context) {
        this.context = context;
        this.songs1 = songs1;
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
        //Generate music player
        intent = new Intent(context, musicService.class);
        activity = (AppCompatActivity) context;
        player = new ExoPlayer.Builder(context).build();
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
                if (checker.get(i)) {
                    checkService = checker.get(i);
                    checkService1 = checker1.get(i);
                    for (int j = 0; j < songs1.size(); j++) {
                        checker1.set(j, true);
                    }
                    checker1.set(i, false);
                    intent.putExtra("song", songs1.get(i));
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

    public boolean getSongState() {
        return !player.isPlaying();
    }

}

