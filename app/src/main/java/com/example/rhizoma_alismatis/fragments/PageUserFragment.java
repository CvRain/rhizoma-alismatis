package com.example.rhizoma_alismatis.fragments;

import android.content.Intent;
import android.os.Bundle;
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
import com.example.rhizoma_alismatis.models.LoginViewModel;
import com.example.rhizoma_alismatis.models.UserInfo;
import com.example.rhizoma_alismatis.utils.ImageFormat;
import com.makeramen.roundedimageview.RoundedImageView;
import org.jetbrains.annotations.NotNull;

public class PageUserFragment extends Fragment {
    private View myView;
    private Button userNameButton;
    private ListView recentPlayListView;
    private DatabaseService databaseService;

    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseService = DatabaseService.getInstance(getContext());
    }

    @Override
    public void onResume() {
        super.onResume();
        RoundedImageView userIcon = myView.findViewById(R.id.userIcon);
        if (LoginViewModel.getInstance().getIsLoggedIn()) {
            String userEmail = LoginViewModel.getInstance().getLoginEmail();
            if (userEmail == null) {
                return;
            }
            UserInfo userInfo = databaseService.GetUserInfo(userEmail);
            userNameButton.setText(userInfo.UserName);
            Toast.makeText(getContext(), "Welcome " + userInfo.UserName, Toast.LENGTH_SHORT).show();

            if (userInfo.UserIcon != null) {
                userIcon.setImageBitmap(ImageFormat.base64ToBitmap(userInfo.UserIcon));
            }
        }else{
            userIcon.setImageBitmap(ImageFormat.base64ToBitmap(getString(R.string.default_icon)));
        }
    }

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        if (myView == null) {
            myView = inflater.inflate(R.layout.layout_user, null);
        }
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
        Intent layoutIntent = new Intent(getContext(), this.getClass());
        if (LoginViewModel.getInstance().getIsLoggedIn()) {
            layoutIntent.setClass(getContext(), UserInfoDetailLayout.class);
        } else {
            layoutIntent.setClass(getContext(), UserLoginLayout.class);
        }
        if (getContext() != null) {
            getContext().startActivity(layoutIntent);
        }
    }
}

