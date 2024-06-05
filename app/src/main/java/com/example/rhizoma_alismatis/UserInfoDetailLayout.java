package com.example.rhizoma_alismatis;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rhizoma_alismatis.models.LoginViewModel;
import com.example.rhizoma_alismatis.models.UserInfo;
import com.example.rhizoma_alismatis.utils.ImageFormat;
import com.example.rhizoma_alismatis.utils.StrFormat;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;

public class UserInfoDetailLayout extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private ActivityResultLauncher<String> pickImageLauncher;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_detail);

        if (LoginViewModel.getInstance().getIsLoggedIn()) {
            interfaceInit();
        }

        // 注册 ActivityResultLauncher
        pickImageLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri == null) {
                        return;
                    }
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                        RoundedImageView userIcon = findViewById(R.id.user_detail_icon);
                        userIcon.setImageBitmap(bitmap);
                        UserInfo userInfo = DatabaseService.getInstance(this)
                                .GetUserInfo(LoginViewModel.getInstance().getLoginEmail());
                        userInfo.UserIcon = ImageFormat.BitmapToBase64(this, bitmap);
                        DatabaseService.getInstance(this).UpdateUserInfo(userInfo);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void interfaceInit() {
        TextView userId = findViewById(R.id.detail_layout_user_id);
        UserInfo userInfo = DatabaseService.getInstance(this)
                .GetUserInfo(LoginViewModel.getInstance().getLoginEmail());
        userId.setText(userInfo.UserId);

        EditText userEmail = findViewById(R.id.detail_layout_user_email);
        userEmail.setEnabled(false);
        userEmail.setText(userInfo.UserEmail);

        EditText userName = findViewById(R.id.detail_layout_user_name);
        userName.setEnabled(false);
        userName.setText(userInfo.UserName);

        //暂时关闭编辑
        userEmail.setOnClickListener(this::changeEmail);
        userName.setOnClickListener(this::changeName);

        Button signOut = findViewById(R.id.user_sign_out);
        signOut.setOnClickListener(this::userSignOut);

        RoundedImageView userIcon = findViewById(R.id.user_detail_icon);
        userIcon.setImageBitmap(
                ImageFormat.base64ToBitmap(
                        DatabaseService.getInstance(this)
                                .GetUserInfo(LoginViewModel.getInstance().getLoginEmail()
                                ).UserIcon)
        );
        userIcon.setOnClickListener(v -> changeUserIcon());
    }

    private void changeEmail(View view) {
        if (LoginViewModel.getInstance().getIsLoggedIn()) {
            EditText userEmail = findViewById(R.id.detail_layout_user_email);
            userEmail.setEnabled(true);
        }
    }

    private void changeName(View view) {
        if (LoginViewModel.getInstance().getIsLoggedIn()) {
            EditText userName = findViewById(R.id.detail_layout_user_name);
            userName.setEnabled(true);
        }
    }

    private void userSignOut(View view) {
        LoginViewModel.getInstance().setIsLoggedIn(false, "");
        finish();
    }

    private void changeUserIcon() {
        pickImageLauncher.launch("image/*");
    }

}
