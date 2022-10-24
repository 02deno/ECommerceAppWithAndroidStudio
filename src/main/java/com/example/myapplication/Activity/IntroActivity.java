package com.example.myapplication.Activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.google.android.material.button.MaterialButton;

public class IntroActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username =(TextView) findViewById(R.id.username);
        TextView password =(TextView) findViewById(R.id.password);

        MaterialButton loginbtn = (MaterialButton) findViewById(R.id.loginBtn);



        //deno and alper

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("deno") && password.getText().toString().equals("alper")){
                    //correct
                    Toast.makeText(IntroActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(IntroActivity.this, HomepageActivity.class));
                }else
                    //incorrect
                    Toast.makeText(IntroActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
