package com.example.musicapp.fragment;

import android.content.Context;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicapp.ItemSong;
import com.example.musicapp.R;

import java.util.ArrayList;

public class SongItemAdapter extends RecyclerView.Adapter<SongItemAdapter.ViewHolder> {

    MediaPlayer mediaPlayer;
    private Context context;
    private ArrayList<ItemSong> songs;

    public SongItemAdapter() {
    }



    public SongItemAdapter(Context context, ArrayList<ItemSong> songs, MediaPlayer mediaPlayer) {
        this.context = context;
        this.songs = songs;
        this.mediaPlayer = mediaPlayer;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.song_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int Position = holder.getAdapterPosition();
        holder.songName.setText(songs.get(Position).getSongName());
        holder.artistName.setText(songs.get(Position).getArtistName());
        holder.songIcon.setImageResource(songs.get(Position).getSongImageId());
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        public TextView songName, artistName;
        public ImageView songIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.song_item);
            songName = itemView.findViewById(R.id.song_name);
            artistName = itemView.findViewById(R.id.artist_name);
            songIcon = itemView.findViewById(R.id.song_icon);
        }
    }
}
