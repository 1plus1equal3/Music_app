package com.example.musicapp;

import static com.example.musicapp.fragment.HomeFragment.continueMusic;
import static com.example.musicapp.fragment.HomeFragment.stopMusic;

import android.Manifest;
import android.app.PendingIntent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.musicapp.fragment.SavedSongsAdapter;
import com.example.musicapp.fragment.Song;
import com.example.musicapp.fragment.StorageFragment;
import com.example.musicapp.fragment.musicService;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerNotificationManager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    static ArrayList<Song> savedSongs;
    ViewPager2 viewPager2;
    Adapter adapter;
    TabLayout tabLayout;
    /*
        Button play, leftRewind, rightRewind;
    */
    //Player + playerView + playerNotificationManager
    private ExoPlayer player;
    private PlayerControlView playerControlView;

    //Get songs
    public static void getSongs(ArrayList<Song> songs) {
        savedSongs = songs;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //get view's ids
/*        songController = findViewById(R.id.song_controller);
        play = findViewById(R.id.play_btn);
        leftRewind = findViewById(R.id.left_rewind_btn);
        rightRewind = findViewById(R.id.right_rewind_btn);*/
        playerControlView = new PlayerControlView(this);
        playerControlView = findViewById(R.id.playercontrolView);
        viewPager2 = findViewById(R.id.vp2);
        tabLayout = findViewById(R.id.tab);
        adapter = new Adapter(this);
        viewPager2.setAdapter(adapter);
        viewPager2.setOffscreenPageLimit(2);
        //Tab config
        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Home");
                        tab.setIcon(R.drawable.ic_home);
                        break;
                    case 1:
                        tab.setText("Favorite");
                        tab.setIcon(R.drawable.ic_favor);
                        break;
                    case 2:
                        tab.setIcon(R.drawable.ic_storage);
                        tab.setText("Storage");
                        break;
                }
            }

        }).attach();
        tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        Log.e("Tab: ", "Home");
                        break;
                    case 1:
                        Log.e("Tab: ", "Favorite");
                    case 2:
                        Log.e("Tab: ", "Storage");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //check permission
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        }

        //Create player
        player = new ExoPlayer.Builder(this).build();
        //Pass player to StorageFragment
        StorageFragment.getPlayer(player);
        //Pass playerControlView to SavedSongsAdapter
        SavedSongsAdapter.getPlayerControlView(playerControlView);
        //Pass player to musicService
        musicService.getPlayer(player);
        //Set up playerView
        playerControlView.setPlayer(player);


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Read External Storage Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Read External Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.play_music:
                continueMusic();
                Toast.makeText(getApplicationContext(), "Play music", Toast.LENGTH_SHORT).show();
                break;
            case R.id.stop_music:
                stopMusic();
                Toast.makeText(getApplicationContext(), "Stop music", Toast.LENGTH_SHORT).show();
                break;
            case R.id.favorite_music:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}

//Git commit test