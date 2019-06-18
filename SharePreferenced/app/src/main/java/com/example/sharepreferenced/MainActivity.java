package com.example.sharepreferenced;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText edtUserName, edtPassword;
    Button btnLogin;
    CheckBox cbRemember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                if(checkEmptyString()){
                    String userName = edtUserName.getText().toString();
                    String passWord = edtPassword.getText().toString();
                    intent.putExtra("username", userName);
                        if(!cbRemember.isSelected()) {


                            SplashActivity.AppConfig.saveInfo(userName, passWord, MainActivity.this);
                        }
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Khong de trong cac o nhap", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    public boolean checkEmptyString(){
        if (edtUserName.getText().toString().length() ==0 || edtPassword.getText().toString().length() == 0){
            return false;
        }
        return true;

    }
    void init(){
        btnLogin = findViewById(R.id.btn_login);
        edtUserName = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        cbRemember = findViewById(R.id.cb_remember);
    }
}
