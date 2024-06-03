package com.example.rhizoma_alismatis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserLoginLayout extends AppCompatActivity {

    private Intent jumpRegisterIntent;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        TextView changeRegister = findViewById(R.id.jump_to_register);
        changeRegister.setOnClickListener(this::changeToRegister);

        jumpRegisterIntent = new Intent(this, UserRegisterLayout.class);
    }

    private void changeToRegister(View view){
        startActivity(jumpRegisterIntent);
    }
}
