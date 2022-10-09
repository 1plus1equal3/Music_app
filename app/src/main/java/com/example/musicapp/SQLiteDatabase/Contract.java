package com.example.musicapp.SQLiteDatabase;

import android.provider.BaseColumns;

import java.sql.Blob;

public final class Contract {
    private Contract(){}

    public static class TrendingSongs implements BaseColumns {
        public static final String TABLE_NAME = "TrendingSongs";
        public static final String SONG_NAME_COLUMN = "SongName";
        public static final String ARTIST_COLUMN = "Artist";
        public static final String SONG_COLUMN = "Song";

    }
}
