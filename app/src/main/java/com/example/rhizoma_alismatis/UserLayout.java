package com.example.rhizoma_alismatis;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserLayout extends AppCompatActivity {
    private Button userNameButton;
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_user);

        userNameButton = findViewById(R.id.layout_user_name);
        userNameButton.setOnClickListener(this::UserNameClicked);
        //componentInit();
    }

    private void componentInit(){
        userNameButton = findViewById(R.id.layout_user_name);
        userNameButton.setOnClickListener(this::UserNameClicked);
    }

    public void UserNameClicked(View view) {
        userNameButton.setText(R.string.login);
        //toast UserNameClicked
        Toast.makeText(this, "UserNameClicked", Toast.LENGTH_SHORT);
    }
}
