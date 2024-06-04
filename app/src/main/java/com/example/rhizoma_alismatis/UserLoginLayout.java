package com.example.rhizoma_alismatis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.rhizoma_alismatis.fragments.PageUserFragment;
import com.example.rhizoma_alismatis.models.LoginViewModel;
import com.example.rhizoma_alismatis.models.UserInfo;
import com.example.rhizoma_alismatis.utils.StrFormat;

public class UserLoginLayout extends AppCompatActivity {
    private Intent jumpRegisterIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        TextView changeRegister = findViewById(R.id.jump_to_register);
        changeRegister.setOnClickListener(this::changeToRegister);

        jumpRegisterIntent = new Intent(this, UserRegisterLayout.class);
        jumpRegisterIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        });

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(this::userLogin);
    }

    private void changeToRegister(View view) {
        startActivity(jumpRegisterIntent);
    }

    private void userLogin(View view) {
        EditText emailInput = findViewById(R.id.login_email);
        EditText passwordInput = findViewById(R.id.login_password);

        if (emailInput.getText().toString().isEmpty() || passwordInput.getText().toString().isEmpty()) {
            return;
        }
        if (DatabaseService.getInstance(this).SearchUserExist(emailInput.getText().toString())) {
            UserInfo userInfo = DatabaseService.getInstance(this).GetUserInfo(emailInput.getText().toString());
            if (userInfo.UserToken.equals(StrFormat.sha256(passwordInput.getText().toString()))) {
                LoginViewModel.getInstance().setIsLoggedIn(true, emailInput.getText().toString());
                Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                Log.d("UserLoginLayout", "UserLoginLayout: " + userInfo.UserToken);
                finish(); // 结束当前Activity，返回到之前的Fragment
            }
        }
    }
}


