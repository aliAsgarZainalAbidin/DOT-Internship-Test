package com.example.dotinternshiptest.ui.main.detail.multi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dotinternshiptest.R;

import java.util.ArrayList;
import java.util.Arrays;

public class Viewpager2Adapter extends RecyclerView.Adapter<Viewpager2Adapter.ImageViewHolder> {

    private ArrayList<String> list = new ArrayList<>();

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewpager_slideimage, parent, false);
        return new ImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String imageUrl = list.get(position);
        holder.onBind(imageUrl);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_slideimage);
        }

        void onBind(String data){
            Glide.with(itemView.getContext())
                    .load(data)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imageView);
        }
    }
}
