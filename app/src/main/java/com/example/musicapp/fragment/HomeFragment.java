package com.example.musicapp.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.musicapp.ItemSong;
import com.example.musicapp.R;
import com.example.musicapp.SQLiteDatabase.SongDbHelper;

import java.util.ArrayList;

public class HomeFragment extends Fragment{
    static MediaPlayer mediaPlayer;
    SQLiteDatabase database;
    SongItemAdapter adapter;
    RecyclerView recyclerView;
    SongDbHelper songDbHelper;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mediaPlayer = MediaPlayer.create(view.getContext(), R.raw.laudaitinhaii);
        recyclerView = view.findViewById(R.id.rv);
        ArrayList<ItemSong> songs = new ArrayList<>();
        //generate songs
/*        for(int i = 0; i<100; i++) {
            songs.add(new ItemSong("Lau Dai Tinh Ai", "Dam Vinh Hung", R.drawable.laudaitinhai));
        }*/
        adapter = new SongItemAdapter(getActivity(), songs, mediaPlayer);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        songDbHelper = new SongDbHelper(getContext());
/*        for(int i=0; i<100; i++){
            songDbHelper.addSong(songs.get(i).getSongName(), songs.get(i).getArtistName());
        }*/

    }



    public static void stopMusic() {
        mediaPlayer.pause();
    }

    public static void continueMusic() {
        mediaPlayer.start();
    }

}

