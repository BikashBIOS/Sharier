package com.example.shareer.MultipleImagesPackage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shareer.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MultipleImagesAdapter extends RecyclerView.Adapter<MultipleImagesAdapter.MultipleImageViewerHolder> {

    private Context mIContext;
    private List<MultipleImagesHandler> mIUpload;

    public MultipleImagesAdapter(Context context,List<MultipleImagesHandler> uploads)
    {
        mIContext=context;
        mIUpload=uploads;
    }

    @NonNull
    @Override
    public MultipleImagesAdapter.MultipleImageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mIContext).inflate(R.layout.item_multipleimages,parent,false);
        return new MultipleImagesAdapter.MultipleImageViewerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleImagesAdapter.MultipleImageViewerHolder holder, int position) {
        final MultipleImagesHandler uploadCurrent=mIUpload.get(position);
        holder.textLink.setText(uploadCurrent.getImgLink());
        Picasso.get().load(uploadCurrent.getImgLink()).fit().centerCrop().into(holder.multipleImage);

    }

    @Override
    public int getItemCount() {
        return mIUpload.size();
    }

    public class MultipleImageViewerHolder extends RecyclerView.ViewHolder
    {
        public TextView textLink;
        public ImageView multipleImage;
        public MultipleImageViewerHolder(@NonNull View itemView) {
            super(itemView);

            textLink=itemView.findViewById(R.id.text_multiple_link);
            multipleImage=itemView.findViewById(R.id.image_view_multiple);
        }
    }
}
