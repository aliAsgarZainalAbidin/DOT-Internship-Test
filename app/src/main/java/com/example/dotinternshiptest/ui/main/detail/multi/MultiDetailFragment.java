package com.example.dotinternshiptest.ui.main.detail.multi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dotinternshiptest.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;

public class MultiDetailFragment extends Fragment {

    private ViewPager2 viewPager2;
    private DotsIndicator dotsIndicator;
    private TextView tvTitle;
    private TextView tvSubtitle;
    public static final String MULTI_IMAGE = "MULTI_IMAGE";
    public static final String TITLE = "TITLE";
    public static final String SUBTITLE = "SUBTITLE";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_multi_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewPager2 = view.findViewById(R.id.vp_multiimage);
        dotsIndicator = view.findViewById(R.id.dotsindicator_multiimage);
        tvTitle = view.findViewById(R.id.tv_multiimage_title);
        tvSubtitle = view.findViewById(R.id.tv_multiimage_subtitle);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            if (getArguments() != null) {
                ArrayList<String> imageUrl = getArguments().getStringArrayList(MULTI_IMAGE);
                String title = getArguments().getString(TITLE);
                String subtitle = getArguments().getString(SUBTITLE);

                Viewpager2Adapter viewpager2Adapter = new Viewpager2Adapter();
                viewpager2Adapter.setList(imageUrl);

                tvTitle.setText(title);
                tvSubtitle.setText(subtitle);

                viewPager2.setAdapter(viewpager2Adapter);
                dotsIndicator.setViewPager2(viewPager2);
                MaterialToolbar materialToolbar = getActivity().findViewById(R.id.mt_activity);
                materialToolbar.setTitle("Detail Multi");

                materialToolbar.setNavigationOnClickListener(v -> {
                    getActivity().onBackPressed();
                });
            }
        }
    }
}