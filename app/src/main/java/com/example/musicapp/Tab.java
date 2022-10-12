package com.example.musicapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.musicapp.fragment.HomeFragment;
import com.example.musicapp.fragment.FavoriteFragment;
import com.example.musicapp.fragment.StorageFragment;

public class Tab extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}

class Adapter extends FragmentStateAdapter{

    Fragment homeFragment = null,
            favoriteFragment = null, storageFragment = null;

    public Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                if(homeFragment == null){
                    homeFragment = new HomeFragment();
                    Log.e("Log:",  "new Home Fragment");
                    return homeFragment;
                }
                Log.e("Log:",  "already created Home Fragment");
                return homeFragment;
            case 1:
                if(favoriteFragment == null){
                    favoriteFragment = new FavoriteFragment();
                    return favoriteFragment;
                }
                return favoriteFragment;
            case 2:
                if(storageFragment == null){
                    storageFragment = new StorageFragment();
                    return storageFragment;
                }
                return storageFragment;
            default:
                return new Tab();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
