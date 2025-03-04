package com.example.rhizoma_alismatis.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.rhizoma_alismatis.DatabaseService;
import com.example.rhizoma_alismatis.R;
import com.example.rhizoma_alismatis.adapters.UsersAdapter;
import com.example.rhizoma_alismatis.models.UserInfo;
import com.example.rhizoma_alismatis.models.WeatherForecast;
import com.example.rhizoma_alismatis.utils.ImageFormat;
import com.example.rhizoma_alismatis.utils.NetworkRequest;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PageRandomMusicFragment extends Fragment {
    private static final String TAG = "page_random_play";
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
            myView = inflater.inflate(R.layout.layout_random_music, null);
        }
        clearParent(myView);
        Log.d(TAG, "onCreateView: ");



        List<WeatherForecast> forecastList = new ArrayList<>();
        NetworkRequest request = new NetworkRequest(forecasts -> {
            List<UserInfo> users = new ArrayList<>();
            for (WeatherForecast it : forecasts){
                users.add(new UserInfo(it.getSummary(),it.getTemperatureC() + " " + it.getSummary(),it.getSummary(),it.getDate(), getString(R.string.default_icon)));
            }
            forecastList.addAll(forecasts);
            UsersAdapter usersAdapter = new UsersAdapter(myView.getContext(), R.layout.user_component, users);
            ListView listView = myView.findViewById(R.id.random_list_view);
            listView.setAdapter(usersAdapter);

        });

        request.execute("http://10.0.2.2:5029/WeatherForecast");
        Log.d("weather", "execute");

        // 遍历forecastList
        for (WeatherForecast it : forecastList){
            Log.d("weather", it.getDate());
        }




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
