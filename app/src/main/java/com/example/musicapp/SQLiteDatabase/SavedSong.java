package com.example.musicapp.SQLiteDatabase;

import java.sql.Blob;

public class SavedSong {
    private String SongName;
    private String Artist;
    private Blob SongFile;

    public SavedSong(String songName, String artist, Blob songFile) {
        SongName = songName;
        Artist = artist;
        SongFile = songFile;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public Blob getSongFile() {
        return SongFile;
    }

    public void setSongFile(Blob songFile) {
        SongFile = songFile;
    }
}
