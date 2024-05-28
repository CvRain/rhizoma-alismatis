package com.example.rhizoma_alismatis.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.rhizoma_alismatis.models.RecentMusic;

import java.util.List;

public class RecentMusicAdapter extends ArrayAdapter<RecentMusic> {
    private final int resourceId;

    public RecentMusicAdapter(@NonNull Context context, int resource, List<RecentMusic> recentMusics) {
        super(context, resource, recentMusics);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        RecentMusic recentMusic = getItem(position);
        //TODO
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        return view;
    }
}