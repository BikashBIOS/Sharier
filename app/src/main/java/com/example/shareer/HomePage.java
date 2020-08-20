package com.example.shareer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.shareer.User.UsersList;

public class HomePage extends AppCompatActivity {

    Button AddPdf, AddImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setTitle("User Actions");

        AddPdf=findViewById(R.id.addpdf);
        AddImage=findViewById(R.id.addimage);

        AddPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, PdfUpload.class));
            }
        });

        AddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomePage.this, ImageUpload.class));
            }
        });
    }

    public void moveToImageListPage(View view) {
        startActivity(new Intent(HomePage.this, UsersList.class));
    }
}