package com.example.sharepreferenced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView tvHome;
    Button btnthoat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        tvHome.setText("Xin chao ban: " + userName);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    void init(){
        tvHome = findViewById(R.id.tv_home_activity);
        btnthoat = findViewById(R.id.btn_thoat);
    }
}
