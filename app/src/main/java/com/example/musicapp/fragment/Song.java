package com.example.musicapp.fragment;

public class Song {
    private long _ID;
    private String title;
    private String artist;

    public Song(long _ID, String title, String artist) {
        this._ID = _ID;
        this.title = title;
        this.artist = artist;
    }

    public long get_ID() {
        return _ID;
    }

    public void set_ID(long _ID) {
        this._ID = _ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
