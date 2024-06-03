package com.example.rhizoma_alismatis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.rhizoma_alismatis.fragments.PageUserFragment;

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
    }

    private void changeToLogin(View view) {
        startActivity(jumpLoginIntent);
    }
}