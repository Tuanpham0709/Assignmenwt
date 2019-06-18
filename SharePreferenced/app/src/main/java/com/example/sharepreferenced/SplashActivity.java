package com.example.sharepreferenced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                screenRouter();
            }
        }, 2000);

    }
    void screenRouter(){
        String username = AppConfig.getUsername(this);
        String password = AppConfig.getPassword(this);
        if(username  == null || password == null){
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else {
            Intent intent  = new Intent(SplashActivity.this, HomeActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
            finish();
        }
    }
    static class AppConfig{
        public static final String PREF_NAME = "USER_PASS";
        public static void saveInfo(String username, String password, Context context){
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor  = sharedPreferences.edit();
            editor.putString("user_name", username);
            editor.putString("pass_word", password);
            editor.apply();
        }
        public static String getUsername(Context context){
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
            return sharedPreferences.getString("user_name", null);
        }
        public static String getPassword(Context context){
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
            return sharedPreferences.getString("pass_word", null);
        }
    }

}
