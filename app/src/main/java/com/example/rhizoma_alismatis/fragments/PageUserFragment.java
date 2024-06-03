package com.example.rhizoma_alismatis.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.rhizoma_alismatis.DatabaseService;
import com.example.rhizoma_alismatis.R;
import com.example.rhizoma_alismatis.UserInfoDetailLayout;
import com.example.rhizoma_alismatis.UserLoginLayout;
import com.example.rhizoma_alismatis.models.RecentMusic;
import org.jetbrains.annotations.NotNull;

public class PageUserFragment extends Fragment {
    private static final String TAG = "page_user";
    private View myView;
    private Button userNameButton;
    private ListView recentPlayListView;
    private DatabaseService databaseService;
    private boolean isLogin = false;
    private Intent layoutIntent;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseService = DatabaseService.getInstance(getContext());
        Log.d(TAG, "onCreate: ");
    }

    @SuppressLint("InflateParams")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //防止在滑动组件之后销毁滑动前的组件,并且可以保证保留之前的组件状态
        if (myView == null) {
            myView = inflater.inflate(R.layout.layout_user, null);
        }
        //获取组件实例
        userNameButton = myView.findViewById(R.id.layout_user_name);
        userNameButton.setOnClickListener(this::UserNameClicked);

        clearParent(myView);

        return myView;
    }

    private void clearParent(View v) {
        ViewGroup pa = (ViewGroup) v.getParent();
        if (pa != null) {
            pa.removeView(v);
        }
    }

    public void UserNameClicked(View view) {
        //如果用户没登陆，那么按钮跳转就是登陆界面。
        //用户登陆成功，那么按钮会显示用户名，此时点击会跳转用户信息页面。
        layoutIntent = new Intent(getContext(), this.getClass());
        if (isLogin) {
            //跳转到用户信息界面
            layoutIntent.setClass(getContext(), UserInfoDetailLayout.class);
        } else {
            //跳转到登陆界面
            layoutIntent.setClass(getContext(), UserLoginLayout.class);
        }
        if (getContext() != null) {
            getContext().startActivity(layoutIntent);
        }

    }
}
