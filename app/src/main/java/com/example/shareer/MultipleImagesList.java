package com.example.shareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shareer.ImageHandlerPages.ImageAdapterUser;
import com.example.shareer.ImageHandlerPages.ImageUploadHandler;
import com.example.shareer.ImageHandlerPages.ListofImagesUser;
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

public class MultipleImagesList extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    private RecyclerView mIRecyclerView;
    private MultipleImagesAdapter mIAdapter;
    private DatabaseReference databaseReference;
    private List<MultipleImagesHandler> mIUpload;
    ImageView multipleImageView;
    String folderId="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiple_images_list);

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();

        mIRecyclerView=findViewById(R.id.multipleimagesrecyclerview);
        mIRecyclerView.setLayoutManager(new LinearLayoutManager(MultipleImagesList.this));
        mIRecyclerView.setHasFixedSize(true);
        mIUpload=new ArrayList<>();
        folderId=getIntent().getStringExtra("folderName");

        databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Multiple").child(folderId);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    MultipleImagesHandler uploadMultiple=postSnapshot.getValue(MultipleImagesHandler.class);
                    mIUpload.add(uploadMultiple);
                }
                mIAdapter=new MultipleImagesAdapter(MultipleImagesList.this,mIUpload);
                mIRecyclerView.setAdapter(mIAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MultipleImagesList.this, "Error:"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}