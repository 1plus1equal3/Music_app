package com.example.musicapp.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class SelectedSong extends DialogFragment {
    Song song;
    public SelectedSong() {
    }

    public void setSong(Song song) {
        this.song = song;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setTitle("Select favorite song")
                .setMessage("Select this song as a your favorite song?")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Set song as favorite song type
                        if(song!=null){
                        FavoriteFragment.setSong(song);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Don't set song as favorite song type

                    }
                })
                .create();
/*        AlertDialog.Builder selectDialog = new AlertDialog.Builder(getActivity());
        selectDialog.setTitle("Select favorite song");
        selectDialog.setMessage("Select this song as a your favorite song?");
        selectDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Set song as favorite song type
                SelectedSong.this.dismiss();
            }
        });
        selectDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //Don't set song as favorite song type
                SelectedSong.this.dismiss();
            }
        });
        selectDialog.create();
        return super.onCreateDialog(savedInstanceState);*/
    }
}
