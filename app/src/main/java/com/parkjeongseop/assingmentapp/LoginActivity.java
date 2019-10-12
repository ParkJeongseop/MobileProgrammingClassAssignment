package com.parkjeongseop.assingmentapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.FileInputStream;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.InputStreamReader;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Button loginButton = (Button)findViewById(R.id.btn_login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText et_id = (EditText)findViewById(R.id.et_ID);
                String id = et_id.getText().toString();

                EditText et_pw = (EditText)findViewById(R.id.et_PW);
                String pw = et_pw.getText().toString();

                if(pw.equals(getPassword(id))){
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }else{
                    if(getPassword(id) == null) {
                        Toast.makeText(getApplicationContext(), "존재하지 않는 아이디입니다.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(getApplicationContext(), "패스워드가 틀렸습니다.", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        final Button signUpButton = (Button) findViewById(R.id.btn_signUp);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


    public String getPassword(String id){
        String pass = null;

        try{
            FileInputStream fis = openFileInput(id);

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            pass = reader.readLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return pass;
    }

}
