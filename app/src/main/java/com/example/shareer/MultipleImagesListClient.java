package com.example.shareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class MultipleImagesListClient extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    private RecyclerView mIRecyclerView;
    private MultipleImagesAdapterClient mIAdapter;
    private DatabaseReference databaseReference;
    private List<MultipleImagesHandler> mIUpload;
    ImageView multipleImageView;
    String userId="", folderId="";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_images_list_client);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        mIRecyclerView=findViewById(R.id.multipleimagesrecyclerviewclient);
        mIRecyclerView.setLayoutManager(new LinearLayoutManager(MultipleImagesListClient.this));
        mIRecyclerView.setHasFixedSize(true);
        mIUpload=new ArrayList<>();
        folderId=getIntent().getStringExtra("foldername");

        sharedPreferences=getApplicationContext().getSharedPreferences("MyUser", Context.MODE_PRIVATE);
        userId=sharedPreferences.getString("mUserId", "");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(userId).child("Multiple").child(folderId).child("Images");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    MultipleImagesHandler uploadMultiple=postSnapshot.getValue(MultipleImagesHandler.class);
                    mIUpload.add(uploadMultiple);
                }
                mIAdapter=new MultipleImagesAdapterClient(MultipleImagesListClient.this,mIUpload);
                mIRecyclerView.setAdapter(mIAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MultipleImagesListClient.this, "Error:"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}