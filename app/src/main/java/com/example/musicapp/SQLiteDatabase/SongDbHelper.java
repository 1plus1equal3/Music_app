package com.example.musicapp.SQLiteDatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Blob;

public class SongDbHelper extends SQLiteOpenHelper {
    private static final String CREATE_TABLE = "CREATE TABLE " + Contract.TrendingSongs.TABLE_NAME + " ("
            + Contract.TrendingSongs.SONG_NAME_COLUMN + " TEXT PRIMARY KEY, "
            + Contract.TrendingSongs.ARTIST_COLUMN + " TEXT)";
    private static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + Contract.TrendingSongs.TABLE_NAME;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TrendingSongs.db";

    public SongDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DELETE_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void addSong(String songName, String songArtist){
        SQLiteDatabase db = this.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(Contract.TrendingSongs.SONG_NAME_COLUMN, songName);
        values.put(Contract.TrendingSongs.ARTIST_COLUMN, songArtist);
        db.insert(Contract.TrendingSongs.TABLE_NAME, null, values);
        db.close();
    }
}
