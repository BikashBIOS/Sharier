package com.example.shareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shareer.Client.LoginClient;
import com.example.shareer.User.LoginUser;
import com.example.shareer.User.RegisterUser;

public class MainActivity extends AppCompatActivity {

    Button ChoiceUser, ChoiceClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ChoiceClient=findViewById(R.id.choiceclient);
        ChoiceUser=findViewById(R.id.choiceuser);

        ChoiceUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginUser.class));
            }
        });

        ChoiceClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LoginClient.class));
            }
        });
    }
}