package com.example.shareer.User;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shareer.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.MyHolder>{

    Context context;
    List<ModelUser> usersList;

    public UsersAdapter(Context context, List<ModelUser> usersList) {
        this.context = context;
        this.usersList = usersList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_users, parent,false);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        /*String userName=usersList.get(position).getName();
        String userEmail=usersList.get(position).getEmail();*/

        final ModelUser currentUser=usersList.get(position);

        holder.mNameUser.setText(currentUser.getName());
        holder.mEmailUser.setText(currentUser.getEmail());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return usersList.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{
        TextView mNameUser, mEmailUser;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            mNameUser=itemView.findViewById(R.id.usernameTv);
            mEmailUser=itemView.findViewById(R.id.useremailIv);
        }
    }
}
