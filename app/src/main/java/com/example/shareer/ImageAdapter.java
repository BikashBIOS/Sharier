package com.example.shareer;

import android.content.Context;
import android.content.Intent;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewerHolder>{
    private Context mContext;
    private List<ImageUploadHandler> mUpload;

    public ImageAdapter(Context context,List<ImageUploadHandler> uploads)
    {
        mContext=context;
        mUpload=uploads;
    }
    @NonNull
    @Override
    public ImageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewerHolder holder, int position) {

        final ImageUploadHandler uploadCurrent=mUpload.get(position);
        holder.textViewName.setText(uploadCurrent.getmName());
        Picasso.get().load(uploadCurrent.getmImageUri()).fit().centerCrop().into(holder.imageView);
        holder.mDescription.setText(uploadCurrent.getmDesc());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,ImageDetailActivity.class);
                intent.putExtra("imgKey",uploadCurrent.getmKey());
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mUpload.size();
    }

    public class ImageViewerHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView textViewName;
        public ImageView imageView;
        public TextView mDescription;
        public ItemClickListener listener;
        public ImageViewerHolder(@NonNull View itemView) {
            super(itemView);

            textViewName=itemView.findViewById(R.id.title);
            imageView=itemView.findViewById(R.id.image_view_upload);
            mDescription=itemView.findViewById(R.id.descriptionIv);
        }
        public void setOnclickListener(ItemClickListener listener){
            this.listener=listener;
        }
        @Override
        public void onClick(View v) {
            listener.onClick(v,getAdapterPosition(),false);
        }
    }
}
