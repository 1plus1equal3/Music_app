package com.example.musicapp.fragment;

public class Song {
    private long _ID;
    private String title;
    private String artist;
    private String path;


    public Song(long _ID, String title, String artist, String path) {
        this._ID = _ID;
        this.title = title;
        this.artist = artist;
        this.path = path;
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
