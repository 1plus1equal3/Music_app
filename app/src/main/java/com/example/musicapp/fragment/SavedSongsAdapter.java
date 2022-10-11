package com.example.musicapp.fragment;

import android.content.Context;
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
    private TextView id, title, artist;
    final ArrayList<Song> songs1;
    private Context context;
    private static ExoPlayer player;
    private static ArrayList<Boolean> checker = new ArrayList<>();

    public SavedSongsAdapter(ArrayList<Song> songs1, Context context) {
        this.context=context;
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
        for(int j = 0; j < songs1.size(); j++){
            checker.add(j, true);
        }
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
                /*openSelectDialog(songs1.get(i));*/
                Log.e("Log: ", songs1.get(i).getPath());
                //play song
                if(checker.get(i))
                    prepareSong(songs1.get(i).getPath(), context, i, songs1.size());
                if (getSongState()) {
                    player.setPlayWhenReady(true);
                    player.getPlaybackState();
                } else {
                    player.setPlayWhenReady(false);
                    player.getPlaybackState();
                }
            }
        });
        return rowView;
    }



    public void openSelectDialog(Song song) {
        //Open Dialog fragment here
        AppCompatActivity activity = (AppCompatActivity) context;
        SelectedSong fragment = new SelectedSong();
        fragment.setSong(song);
        activity.getSupportFragmentManager().beginTransaction()
                .add(fragment, "Selected song")
                .commit();
    }

    //prepare song here
    public static void prepareSong(String path, Context context, int i, int size) {
            StyledPlayerControlView view = new StyledPlayerControlView(context);
            Uri uri = Uri.parse(path);
            MediaItem mediaItem = MediaItem.fromUri(uri);
            player.setMediaItem(mediaItem);
            player.prepare();
        for(int j = 0; j<size; j++){
            checker.set(j, true);
        }
            checker.set(i, false);
/*            player.play();*/
    }

    public boolean getSongState (){
        if(player.isPlaying()){
            return false;
        }
        return true;
    }

}
