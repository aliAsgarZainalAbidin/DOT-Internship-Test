package com.example.dotinternshiptest.ui.main.gallery;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dotinternshiptest.R;
import com.example.dotinternshiptest.data.remote.models.Gallery;
import com.example.dotinternshiptest.ui.main.detail.single.SingleDetailFragment;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder> {

    private List<Gallery> list = new ArrayList<>();
    private Context context;

    public GalleryAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<Gallery> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_gridlayout_item, parent, false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {
        Gallery gallery = list.get(position);
        Log.d(TAG, "onBindViewHolder: "+gallery.getThumbnail());
        holder.onBind(gallery);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder {

        LinearLayout linearLayout;
        AppCompatImageView imageView;

        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.iv_gridlayout_item);
            linearLayout = itemView.findViewById(R.id.linearlayout_gridlayout_container);
        }

        void onBind(Gallery gallery){
            Glide.with(itemView.getContext())
                    .load(gallery.getThumbnail())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imageView);

            linearLayout.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt(SingleDetailFragment.TYPE_FRAGMENT, SingleDetailFragment.FRAGMENT_GALLERY);
                bundle.putString(SingleDetailFragment.SINGLE_IMAGE, gallery.getImage());
                bundle.putString(SingleDetailFragment.CAPTION, gallery.getCaption());

                SingleDetailFragment singleDetailFragment = new SingleDetailFragment();
                singleDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_main_fragmentcontainer, singleDetailFragment)
                        .addToBackStack(null)
                        .commit();

                MaterialToolbar materialToolbar = ((AppCompatActivity) context).findViewById(R.id.mt_activity);
                materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            });
        }

    }
}
