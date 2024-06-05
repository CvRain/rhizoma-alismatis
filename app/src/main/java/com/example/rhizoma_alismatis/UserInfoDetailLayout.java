package com.example.rhizoma_alismatis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rhizoma_alismatis.models.LoginViewModel;
import com.example.rhizoma_alismatis.models.UserInfo;

public class UserInfoDetailLayout extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user_detail);

        if (LoginViewModel.getInstance().getIsLoggedIn()){
            interfaceInit();
        }
    }

    private void interfaceInit(){
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
    }

    private void changeEmail(View view){
        if (LoginViewModel.getInstance().getIsLoggedIn()){
            EditText userEmail = findViewById(R.id.detail_layout_user_email);
            userEmail.setEnabled(true);
        }
    }

    private void changeName(View view){
        if (LoginViewModel.getInstance().getIsLoggedIn()){
            EditText userName = findViewById(R.id.detail_layout_user_name);
            userName.setEnabled(true);
        }
    }

    private void userSignOut(View view){
        LoginViewModel.getInstance().setIsLoggedIn(false, "");
        finish();
    }
}
