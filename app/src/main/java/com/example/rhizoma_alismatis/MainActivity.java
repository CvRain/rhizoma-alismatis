package com.example.rhizoma_alismatis;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.example.rhizoma_alismatis.adapters.PageSwitchAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.container_main);

        bottomNavigationView = findViewById(R.id.main_bottom_nav);
        setBottomNavigationView();

        PageSwitchAdapter switchAdapter = new PageSwitchAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(switchAdapter);
        viewPager.setCurrentItem(0);

        // 监听 ViewPager2 的页面切换事件
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // 根据当前页面索引更新 BottomNavigationView 的选中项
                updateBottomNavigationView(position);
            }
        });
    }

    private void updateBottomNavigationView(int position) {
        switch (position) {
            case 0:
                bottomNavigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
                break;
            case 1:
                bottomNavigationView.getMenu().findItem(R.id.nav_list).setChecked(true);
                break;
            case 2:
                bottomNavigationView.getMenu().findItem(R.id.nav_play).setChecked(true);
                break;
            case 3:
                bottomNavigationView.getMenu().findItem(R.id.nav_tunnel).setChecked(true);
                break;
            case 4:
                bottomNavigationView.getMenu().findItem(R.id.nav_my).setChecked(true);
                break;
        }
    }

    private void setBottomNavigationView() {
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                viewPager.setCurrentItem(0);
                return true;
            }
            if (itemId == R.id.nav_list) {
                viewPager.setCurrentItem(1);
                return true;
            }
            if (itemId == R.id.nav_play) {
                viewPager.setCurrentItem(2);
                return true;
            }
            if (itemId == R.id.nav_tunnel) {
                viewPager.setCurrentItem(3);
                return true;
            }
            if (itemId == R.id.nav_my) {
                viewPager.setCurrentItem(4);
                return true;
            }
            return false;
        });
    }
}