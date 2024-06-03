package com.example.rhizoma_alismatis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rhizoma_alismatis.fragments.PageUserFragment;

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

        // 添加返回按钮回调
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                Intent intent = new Intent(UserLoginLayout.this, PageUserFragment.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
            }
        });
    }

    private void changeToRegister(View view) {
        startActivity(jumpRegisterIntent);
    }
}

