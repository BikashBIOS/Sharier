package com.example.shareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageDetailActivity extends AppCompatActivity {

    TextView imgTitle, imgDescription;
    ImageView imgDownload;
    DatabaseReference reference;
    String uId = "";
    BitmapDrawable drawable;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        imgTitle = findViewById(R.id.imgTitle);
        imgDescription = findViewById(R.id.imgDetails);
        imgDownload = findViewById(R.id.detailImageView);


        uId = getIntent().getStringExtra("imgKey");
        reference = FirebaseDatabase.getInstance().getReference("Uploads").child(uId);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ImageUploadHandler uploadHandler = snapshot.getValue(ImageUploadHandler.class);
                imgTitle.setText(uploadHandler.getmName());
                imgDescription.setText(uploadHandler.getmDesc());
                Picasso.get().load(uploadHandler.getmImageUri()).into(imgDownload);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void savetoGallery(View view) {
        drawable =(BitmapDrawable)imgDownload.getDrawable();
        bitmap= drawable.getBitmap();
        FileOutputStream outputstream =null;
        File sdcard= Environment.getExternalStorageDirectory();
        File directory =new File(sdcard.getAbsolutePath()+"/Sharier");
        directory.mkdir();
        String fileName= String.format ("%d.jpg", System.currentTimeMillis());
        File outFile =new File (directory, fileName );

        Toast.makeText(ImageDetailActivity.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

        try{
            outputstream =new FileOutputStream(outFile);
            bitmap.compress (Bitmap.CompressFormat.JPEG, 100, outputstream);
            outputstream. flush ();
            outputstream.close ();
            Intent intent =new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData (Uri.fromFile(outFile));
            sendBroadcast(intent);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}