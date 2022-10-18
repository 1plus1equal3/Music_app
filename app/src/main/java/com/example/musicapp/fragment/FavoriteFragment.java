package com.example.musicapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import com.example.musicapp.R;

import java.util.ArrayList;

public class FavoriteFragment extends Fragment {
    ListView listView;
    SavedSongsAdapter savedSongsAdapter;
    ArrayList<Song> songs = new ArrayList<>();
    static Song songg;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.favorite_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        //List of favorite songs (adapter + addSong + notifDataSetChanged)
/*        savedSongsAdapter = new SavedSongsAdapter(songs, getContext());*/
        savedSongsAdapter = new SavedSongsAdapter(songs, getContext());
        listView = view.findViewById(R.id.song_list1);
        listView.setAdapter(savedSongsAdapter);
        if(songg!=null) {
        songs.add(songg);
        savedSongsAdapter.notifyDataSetChanged();
        }
        savedSongsAdapter.notifyDataSetChanged();
        super.onViewCreated(view, savedInstanceState);
    }

    public static void setSong(Song song) {
        FavoriteFragment.songg = song;
    }

    @Override
    public void onResume() {
        savedSongsAdapter.notifyDataSetChanged();
        super.onResume();
    }
}
