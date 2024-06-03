package com.example.rhizoma_alismatis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserRegisterLayout extends AppCompatActivity {
    Intent jumpLoginIntent;
    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        TextView textView = findViewById(R.id.jump_to_login);
        textView.setOnClickListener(this::changeToLogin);

        jumpLoginIntent = new Intent(this, UserLoginLayout.class);
    }

    private void changeToLogin(View view){
        startActivity(jumpLoginIntent);
    }
}
