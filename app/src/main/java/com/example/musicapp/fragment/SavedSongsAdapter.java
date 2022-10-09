package com.example.musicapp.fragment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicapp.R;

import java.util.ArrayList;

public class SavedSongsAdapter extends BaseAdapter {
    TextView id, title, artist;
    final ArrayList<Song> songs1;
    Context context;

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
                openSelectDialog(songs1.get(i));
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
}
