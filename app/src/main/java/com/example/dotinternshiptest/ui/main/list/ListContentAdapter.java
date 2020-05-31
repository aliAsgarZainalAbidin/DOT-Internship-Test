package com.example.dotinternshiptest.ui.main.list;

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
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.RequestOptions;
import com.example.dotinternshiptest.R;
import com.example.dotinternshiptest.data.remote.models.Place;
import com.example.dotinternshiptest.ui.main.detail.multi.MultiDetailFragment;
import com.example.dotinternshiptest.ui.main.detail.single.SingleDetailFragment;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.List;

public class ListContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Place> placeList = new ArrayList<>();
    private Context context;

    public ListContentAdapter(Context context) {
        this.context = context;
    }

    public static final int SINGLE_TYPE = 0;
    public static final int MULTI_TYPE = 1;

    @Override
    public int getItemViewType(int position) {
        int TYPE_VIEW;
        if (placeList.get(position).getType().equals("image")) {
            TYPE_VIEW = SINGLE_TYPE;
        } else {
            TYPE_VIEW = MULTI_TYPE;
        }
        return TYPE_VIEW;
    }

    public void setPlaceList(ArrayList<Place> placeList) {
        this.placeList = placeList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        Log.d("ONCREATEVIEWHOLDER", "onCreateViewHolder: "+viewType);
        if (viewType == SINGLE_TYPE) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_singleimage_item, parent, false);
            Log.d("TYPE VIEW", "onCreateViewHolder: "+viewType);
            return new SingleViewHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_multiimage_item, parent, false);
            Log.d("TYPE VIEW", "onCreateViewHolder: "+viewType);
            return new MultiViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Place place = placeList.get(position);
        Log.d("ListContentAdapter", "onBindViewHolder: "+place.getImage());
        if (getItemViewType(position) == SINGLE_TYPE) {
            SingleViewHolder singleViewHolder = new SingleViewHolder(holder.itemView);
            singleViewHolder.onBind(place);
        } else {
            MultiViewHolder multiViewHolder = new MultiViewHolder(holder.itemView);
            multiViewHolder.onBind(place);
        }
    }

    @Override
    public int getItemCount() {
        return placeList.size();
    }

    public class SingleViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout linearLayout;
        private AppCompatImageView imageView;
        private MaterialTextView tvTitle;
        private MaterialTextView tvContent;

        public SingleViewHolder(@NonNull View itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearlayout_singleimage_container);
            imageView = itemView.findViewById(R.id.iv_singleimage_image);
            tvTitle = itemView.findViewById(R.id.tv_singleimage_title);
            tvContent = itemView.findViewById(R.id.tv_singleimage_content);
        }

        void onBind(Place data) {
            linearLayout.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putInt(SingleDetailFragment.TYPE_FRAGMENT, SingleDetailFragment.FRAGMENT_LIST);
                bundle.putString(SingleDetailFragment.SINGLE_IMAGE, data.getImage());
                bundle.putString(SingleDetailFragment.TITLE, data.getTitle());
                bundle.putString(SingleDetailFragment.CONTENT, data.getContent());

                SingleDetailFragment singleDetailFragment = new SingleDetailFragment();
                singleDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_main_fragmentcontainer, singleDetailFragment)
                        .addToBackStack(null)
                        .commit();

                MaterialToolbar materialToolbar = ((AppCompatActivity) context).findViewById(R.id.mt_activity);
                materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            });

            Glide.with(itemView.getContext())
                    .load(data.getImage())
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                    .into(imageView);

            tvTitle.setText(data.getTitle());
            tvContent.setText(data.getContent());
        }
    }

    public class MultiViewHolder extends RecyclerView.ViewHolder {

        private RecyclerView recyclerView;
        private MaterialTextView tvTitle;
        private MaterialTextView tvContent;
        private LinearLayout linearLayout;

        public MultiViewHolder(@NonNull View itemView) {
            super(itemView);

            recyclerView = itemView.findViewById(R.id.rv_multiimage_item);
            tvTitle = itemView.findViewById(R.id.tv_multiimage_title);
            tvContent = itemView.findViewById(R.id.tv_multiimage_content);
            linearLayout = itemView.findViewById(R.id.linearlayout_multiimage_container);
        }

        void onBind(Place data) {
            String title = data.getTitle();
            String content = data.getContent();

            tvTitle.setText(title);
            tvContent.setText(content);
            MultiImageAdapter multiImageAdapter = new MultiImageAdapter();
            multiImageAdapter.setList(data.getMedia());

            recyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(multiImageAdapter);

            linearLayout.setOnClickListener(v -> {
                Bundle bundle = new Bundle();
                bundle.putStringArrayList(MultiDetailFragment.MULTI_IMAGE,data.getMedia());
                bundle.putString(MultiDetailFragment.TITLE, data.getTitle());
                bundle.putString(MultiDetailFragment.SUBTITLE, data.getContent());

                MultiDetailFragment multiDetailFragment = new MultiDetailFragment();
                multiDetailFragment.setArguments(bundle);

                ((AppCompatActivity) context).getSupportFragmentManager().beginTransaction()
                        .replace(R.id.framelayout_main_fragmentcontainer, multiDetailFragment)
                        .addToBackStack(null)
                        .commit();

                MaterialToolbar materialToolbar = ((AppCompatActivity) context).findViewById(R.id.mt_activity);
                materialToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
            });
        }
    }
}
