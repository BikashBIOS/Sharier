package com.example.shareer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareer.ImageHandlerPages.ImageAdapterUser;
import com.example.shareer.ImageHandlerPages.ImageUploadHandler;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MultipleImagesAdapter extends RecyclerView.Adapter<MultipleImagesAdapter.ImageViewerHolder> {

    private Context mIContext;
    private List<MultipleImagesHandler> mIUpload;

    public MultipleImagesAdapter(Context context,List<MultipleImagesHandler> uploads)
    {
        mIContext=context;
        mIUpload=uploads;
    }

    @NonNull
    @Override
    public MultipleImagesAdapter.ImageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mIContext).inflate(R.layout.item_multipleimages,parent,false);
        return new MultipleImagesAdapter.ImageViewerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleImagesAdapter.ImageViewerHolder holder, int position) {
        final MultipleImagesHandler uploadCurrent=mIUpload.get(position);
        holder.textLink.setText(uploadCurrent.getImgLink());
        Picasso.get().load(uploadCurrent.getImgLink()).fit().centerCrop().into(holder.multipleImage);

    }

    @Override
    public int getItemCount() {
        return mIUpload.size();
    }

    public class ImageViewerHolder extends RecyclerView.ViewHolder
    {
        public TextView textLink;
        public ImageView multipleImage;
        public ImageViewerHolder(@NonNull View itemView) {
            super(itemView);

            textLink=itemView.findViewById(R.id.text_multiple_link);
            multipleImage=itemView.findViewById(R.id.image_view_multiple);
        }
    }
}
