package com.example.rhizoma_alismatis;

import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.rhizoma_alismatis.fragments.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;

    static class MyPageAdapter extends FragmentPagerAdapter {
        List<Fragment> fragments = new ArrayList<>();

        public MyPageAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
            fragments.add(new PageHomeFragment());
            fragments.add(new PageMusicListFragment());
            fragments.add(new PageRandomMusicFragment());
            fragments.add(new PageTunnelFragment());
            fragments.add(new PageUserFragment());
        }

        @NonNull
        @NotNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(@NonNull @NotNull View view, @NonNull @NotNull Object object) {
            return view == object;
        }
    }

    MyPageAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.container_main);
        fragmentAdapter = new MyPageAdapter(getSupportFragmentManager());
        viewPager.setAdapter(fragmentAdapter);
        viewPager.setCurrentItem(0);
    }

    public void gotoHome(MenuItem item) {
        viewPager.setCurrentItem(0);
    }

    public void gotoMusicList(MenuItem item) {
        viewPager.setCurrentItem(1);
    }

    public void gotoPlay(MenuItem item) {
        viewPager.setCurrentItem(2);
    }

    public void gotoTunnel(MenuItem item) {
        viewPager.setCurrentItem(3);
    }

    public void gotoUser(MenuItem item) {
        viewPager.setCurrentItem(4);
    }
}