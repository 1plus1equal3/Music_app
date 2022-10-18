package com.example.musicapp.fragment;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.musicapp.MainActivity;
import com.example.musicapp.R;
import com.google.android.exoplayer2.ExoPlayer;

import java.util.ArrayList;

public class StorageFragment extends Fragment {
    public ListView songList;
    ArrayList<Song> songs = new ArrayList<>();
    SavedSongsAdapter savedSongsAdapter;
    static ExoPlayer player1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.storage_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        songList = view.findViewById(R.id.song_list);
        ContentResolver musicResolver = getActivity().getContentResolver();
        Uri musicUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String[] projections = new String[]{
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.TITLE,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DATA
        };
        Cursor cursor = musicResolver.query(musicUri, projections, null, null, null);
        int idColumn = cursor.getColumnIndex(MediaStore.Audio.Media._ID);
        int titleColumn = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
        int artistColumn = cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
        int pathColumn = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
        if (cursor.moveToFirst() && cursor != null) {
            int i = 1;
            do {
                long id = cursor.getLong(idColumn);
                //Taking audio file path
                String path = cursor.getString(pathColumn);
                String title = cursor.getString(titleColumn);
                String artist = cursor.getString(artistColumn);
                songs.add(new Song(i, slicer(title), artist, path));
                i++;
                /*Uri contentUri = ContentUris.withAppendedId(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, id);*/
                Log.e("Get music: ", "Success");
            } while (cursor.moveToNext());
            savedSongsAdapter = new SavedSongsAdapter(songs, getContext(), player1);
            songList.setAdapter(savedSongsAdapter);
            MainActivity.getSongs(songs);
        }
    }

    public String slicer(String name) {
        if (name.length() > 10) {
            String lim = name.charAt(5) + "";
            String[] slice = name.split(lim, 2);
            return slice[0] + "...";
        }
        return name;
    }

    public static void getPlayer(ExoPlayer player){
        player1 = player;
    }


}


