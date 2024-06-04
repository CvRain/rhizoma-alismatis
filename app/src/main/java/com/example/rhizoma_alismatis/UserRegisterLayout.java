package com.example.rhizoma_alismatis;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rhizoma_alismatis.fragments.PageUserFragment;
import com.example.rhizoma_alismatis.models.UserInfo;
import com.example.rhizoma_alismatis.utils.StrFormat;

import java.util.UUID;

public class UserRegisterLayout extends AppCompatActivity {

    private Intent jumpLoginIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        TextView textView = findViewById(R.id.jump_to_login);
        textView.setOnClickListener(this::changeToLogin);

        jumpLoginIntent = new Intent(this, UserLoginLayout.class);
        jumpLoginIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        // 添加返回按钮回调
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(UserRegisterLayout.this, PageUserFragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });

        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(this::registerUser);
    }

    private void changeToLogin(View view) {
        startActivity(jumpLoginIntent);
    }

    @SuppressLint("SetTextI18n")
    private void registerUser(View view) {
        //fetch form data
        TextView userName = findViewById(R.id.register_username);
        TextView userPassword = findViewById(R.id.register_password);
        TextView userRepeatPassword = findViewById(R.id.repeat_register_password);
        TextView userEmail = findViewById(R.id.register_email);

        TextView registerMessage = findViewById(R.id.register_result_textview);

        //check form data validity
        if (userName.getText().toString().isEmpty()
                || userPassword.getText().toString().isEmpty()
                || userRepeatPassword.getText().toString().isEmpty()
                || userEmail.getText().toString().isEmpty()) {
            registerMessage.setText("Please fill in all the fields");
            return;
        }

        if (!userPassword.getText().toString().equals(userRepeatPassword.getText().toString())) {
            registerMessage.setText("Passwords do not match");
            return;
        }

        DatabaseService instance = DatabaseService.getInstance(this);

        //check user email exist
        if (instance.SearchUserExist(userEmail.getText().toString())) {
            registerMessage.setText("User email already exists");
            return;
        }

        //insert user (fetch from R.strings.default_icon
        String defaultIcon = getString(R.string.default_icon);
        if (instance.InsertUserInfo(new UserInfo(
                generateUUid(),
                userName.getText().toString(),
                userEmail.getText().toString(),
                StrFormat.sha256(userPassword.getText().toString()),
                defaultIcon
        ))) {
            registerMessage.setText("Register Successfully");
            startActivity(jumpLoginIntent);
            finish();
        } else {
            registerMessage.setText("Register Failed");
        }


    }

    private String generateUUid() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}