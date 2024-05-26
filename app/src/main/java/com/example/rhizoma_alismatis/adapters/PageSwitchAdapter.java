package com.example.rhizoma_alismatis.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import com.example.rhizoma_alismatis.fragments.*;
import org.jetbrains.annotations.NotNull;

public class PageSwitchAdapter extends FragmentStateAdapter {


    public PageSwitchAdapter(@NonNull @NotNull FragmentManager fragmentManager, @NonNull @NotNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
            default:
                return new PageHomeFragment();
            case 1:
                return new PageMusicListFragment();
            case 2:
                return new PageRandomMusicFragment();
            case 3:
                return new PageTunnelFragment();
            case 4:
                return new PageUserFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
