package com.example.shareer.Layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shareer.ImageHandlerPages.ImageUpload;
import com.example.shareer.R;
import com.example.shareer.User.ModelUser;
import com.example.shareer.VideoActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainPage extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton btnVid;
    FloatingActionButton btnImg;
    FirebaseAuth mAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    TextView getUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar=findViewById(R.id.myToolbar);
        setSupportActionBar(toolbar);
        tabLayout=findViewById(R.id.tabLayout);
        viewPager=findViewById(R.id.viewPager);
        btnVid=findViewById(R.id.vidBtn);
        btnImg=findViewById(R.id.imgBtn);
        mAuth=FirebaseAuth.getInstance();
        getUsername=findViewById(R.id.username);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        reference= FirebaseDatabase.getInstance().getReference("Users").child(firebaseUser.getUid());

        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                ModelUser user=dataSnapshot.getValue(ModelUser.class);
                                                getUsername.setText(user.getName());
                                            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
                                        });


        btnVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, VideoActivity.class));
            }
        });

        btnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainPage.this, ImageUpload.class));
            }
        });

        setViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager)
    {
        ViewPageAdapter viewPageAdapter=new ViewPageAdapter(getSupportFragmentManager(),2);
        viewPageAdapter.addFragment(new ImageFragment(),"Images");
        viewPageAdapter.addFragment(new VideoFragment(),"Videos");
        viewPager.setAdapter(viewPageAdapter);

    }
}