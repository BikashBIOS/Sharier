package com.example.shareer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.shareer.User.ModelUser;
import com.example.shareer.User.UsersAdapter;
import com.example.shareer.User.UsersList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FolderList extends AppCompatActivity {

    RecyclerView recyclerViewFolder;
    FolderAdapter foldersAdapter;
    List<FolderModel> foldersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_list);

        recyclerViewFolder=findViewById(R.id.folderlistrecyclerview);
        recyclerViewFolder.setLayoutManager(new LinearLayoutManager(FolderList.this));
        recyclerViewFolder.setHasFixedSize(true);
        foldersList=new ArrayList<>();

        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid()).child("Multiple");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                foldersList.clear();

                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    FolderModel modelFolder=dataSnapshot.getValue(FolderModel.class);
                    foldersList.add(modelFolder);

                    /*if (!modelUser.getUid().equals(firebaseUser.getUid())){

                    }*/
                    foldersAdapter=new FolderAdapter(FolderList.this,foldersList);
                    recyclerViewFolder.setAdapter(foldersAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}