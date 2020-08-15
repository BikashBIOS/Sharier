package com.example.shareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class ImageList extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseDatabase database;
    FirebaseStorage firebaseStorage;
    private RecyclerView mRecyclerView;
    private ImageAdapter mAdapter;
    private DatabaseReference databaseReference;
    private List<ImageUploadHandler> mUpload;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
        getSupportActionBar().setTitle("HOME");

        mAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();

        mRecyclerView=findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(ImageList.this));
        mRecyclerView.setHasFixedSize(true);
        mUpload=new ArrayList<>();
        databaseReference= FirebaseDatabase.getInstance().getReference("Uploads");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren())
                {
                    ImageUploadHandler upload=postSnapshot.getValue(ImageUploadHandler.class);
                    mUpload.add(upload);
                }
                mAdapter=new ImageAdapter(ImageList.this,mUpload);
                mRecyclerView.setAdapter(mAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ImageList.this, "Error:"+databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuLogout:
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(ImageList.this,MainActivity.class));
                break;
            case R.id.viewpdfs:
                startActivity(new Intent(ImageList.this,ListofPdf.class));

        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ImageList.this, MainActivity.class));
    }
}