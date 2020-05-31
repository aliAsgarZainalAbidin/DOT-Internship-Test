package com.example.dotinternshiptest.ui.main.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.dotinternshiptest.R;
import com.example.dotinternshiptest.data.remote.models.Gallery;
import com.example.dotinternshiptest.viewmodel.ViewModelFactory;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class GalleryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.rv_gallery_item);
        progressBar = view.findViewById(R.id.progressbar_gallery);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory viewModelFactory = ViewModelFactory.getINSTANCE();
            GalleryViewModel galleryViewModel = new ViewModelProvider(this, viewModelFactory).get(GalleryViewModel.class);
            GalleryAdapter galleryAdapter = new GalleryAdapter(getActivity());
            galleryViewModel.getGallery().observe(this, galleries -> {
                if (galleries != null) {
                    galleryAdapter.setList(galleries);
                    recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                    recyclerView.setAdapter(galleryAdapter);
                    progressBar.setVisibility(View.GONE);
                }
            });

            MaterialToolbar materialToolbar = getActivity().findViewById(R.id.mt_activity);
            materialToolbar.setNavigationOnClickListener(v -> {
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navbar);
                bottomNavigationView.setSelectedItemId(R.id.list_bottomnavbar_menu);
            });
        }
    }
}