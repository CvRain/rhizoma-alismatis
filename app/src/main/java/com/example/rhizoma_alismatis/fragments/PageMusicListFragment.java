package com.example.rhizoma_alismatis.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.rhizoma_alismatis.R;
import org.jetbrains.annotations.NotNull;

public class PageMusicListFragment extends Fragment {
    private static final String TAG = "page_music_list";
    View myView;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
    }

    @SuppressLint("InflateParams")
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        //防止在滑动组件之后销毁滑动前的组件,并且可以保证保留之前的组件状态
        if (myView == null) {
            myView = inflater.inflate(R.layout.layout_music_list, null);
        }
        clearParent(myView);
        Log.d(TAG, "onCreateView: ");
        return myView;
    }

    private void clearParent(View v) {
        ViewGroup pa = (ViewGroup) v.getParent();
        if (pa != null) {
            pa.removeView(v);
        }
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: ");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "onDetach: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }
}
