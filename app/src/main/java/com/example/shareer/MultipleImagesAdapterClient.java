package com.example.shareer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MultipleImagesAdapterClient extends RecyclerView.Adapter<MultipleImagesAdapterClient.MultipleImageViewerHolder>{

    private Context mIContext;
    private List<MultipleImagesHandler> mIUpload;

    public MultipleImagesAdapterClient(Context context,List<MultipleImagesHandler> uploads)
    {
        mIContext=context;
        mIUpload=uploads;
    }

    @NonNull
    @Override
    public MultipleImagesAdapterClient.MultipleImageViewerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(mIContext).inflate(R.layout.item_multipleimages,parent,false);
        return new MultipleImagesAdapterClient.MultipleImageViewerHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MultipleImagesAdapterClient.MultipleImageViewerHolder holder, int position) {
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
