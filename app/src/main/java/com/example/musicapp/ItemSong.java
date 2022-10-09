package com.example.musicapp;

public class ItemSong {
    private String songName;
    private String artistName;
    private int songImageId;

    public ItemSong(String songName, String artistName, int songImageId) {
        this.songName = songName;
        this.artistName = artistName;
        this.songImageId = songImageId;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getSongImageId() {
        return songImageId;
    }

    public void setSongImageId(int songImageId) {
        this.songImageId = songImageId;
    }
}
