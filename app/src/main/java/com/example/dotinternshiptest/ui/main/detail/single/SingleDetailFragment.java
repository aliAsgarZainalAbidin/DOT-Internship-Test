package com.example.dotinternshiptest.ui.main.detail.single;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dotinternshiptest.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SingleDetailFragment extends Fragment {

    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvSubtitle;

    public static final int FRAGMENT_LIST = 0;
    public static final int FRAGMENT_GALLERY = 1;
    public static final String TYPE_FRAGMENT = "TYPE_FRAGMENT";
    public static final String SINGLE_IMAGE = "SINGLE_IMAGE";
    public static final String TITLE = "TITLE";
    public static final String CONTENT = "CONTENT";
    public static final String CAPTION = "CAPTION";
    public static final String DETAIL_FRAGMENT = "DETAIL_FRAGMENT";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.iv_singledetail_image);
        tvTitle = view.findViewById(R.id.tv_singledetail_title);
        tvSubtitle = view.findViewById(R.id.tv_singledetail_subtitle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            if (getArguments() != null) {
                int type = getArguments().getInt(TYPE_FRAGMENT);

                MaterialToolbar materialToolbar = (MaterialToolbar) getActivity().findViewById(R.id.mt_activity);

                if (type == FRAGMENT_LIST) {
                    String singleImage = getArguments().getString(SINGLE_IMAGE);
                    String title = getArguments().getString(TITLE);
                    String content = getArguments().getString(CONTENT);

                    Glide.with(this)
                            .load(singleImage)
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                            .into(imageView);

                    tvTitle.setText(title);
                    tvSubtitle.setText(content);
                    materialToolbar.setTitle("Detail Single");
                } else {
                    String singleImage = getArguments().getString(SINGLE_IMAGE);
                    String caption = getArguments().getString(CAPTION);

                    tvTitle.setVisibility(View.GONE);
                    tvSubtitle.setText(caption);

                    Glide.with(this)
                            .load(singleImage)
                            .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_image_24).error(R.drawable.ic_baseline_broken_image_24))
                            .into(imageView);
                    materialToolbar.setTitle("Detail Gallery");
                }

                materialToolbar.setNavigationOnClickListener(v -> {
                    getActivity().onBackPressed();
                });
            }
        }
    }
}