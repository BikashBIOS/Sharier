package com.example.shareer.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.shareer.Client.LoginClient;
import com.example.shareer.HomePage;
import com.example.shareer.ImageHandlerPages.ListofImagesUser;
import com.example.shareer.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UsersList extends AppCompatActivity {

    RecyclerView recyclerView;
    UsersAdapter usersAdapter;
    List<ModelUser> usersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        getSupportActionBar().setTitle("Users List");

        recyclerView=findViewById(R.id.userlistrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(UsersList.this));
        recyclerView.setHasFixedSize(true);
        usersList=new ArrayList<>();

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                usersList.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    ModelUser modelUser=dataSnapshot.getValue(ModelUser.class);
                    usersList.add(modelUser);

                    /*if (!modelUser.getUid().equals(firebaseUser.getUid())){

                    }*/
                    usersAdapter=new UsersAdapter(UsersList.this,usersList);
                    recyclerView.setAdapter(usersAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(UsersList.this, LoginClient.class));
    }
}