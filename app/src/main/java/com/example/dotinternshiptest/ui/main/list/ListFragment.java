package com.example.dotinternshiptest.ui.main.list;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.RenderProcessGoneDetail;
import android.widget.ProgressBar;

import com.example.dotinternshiptest.R;
import com.example.dotinternshiptest.data.remote.models.PlaceHeader;
import com.example.dotinternshiptest.data.remote.response.PlaceResponse;
import com.example.dotinternshiptest.viewmodel.ViewModelFactory;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textview.MaterialTextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    private MaterialTextView tvTitle;
    private MaterialTextView tvSubtitle;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvTitle = view.findViewById(R.id.tv_listfragment_title);
        tvSubtitle = view.findViewById(R.id.tv_listfragment_subtitle);
        recyclerView = view.findViewById(R.id.rv_listfragment_item);
        progressBar = view.findViewById(R.id.progressbar_listfragment);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ViewModelFactory viewModelFactory = ViewModelFactory.getINSTANCE();
            ListViewModel listViewModel = new ViewModelProvider(this, viewModelFactory).get(ListViewModel.class);
            ListContentAdapter listContentAdapter = new ListContentAdapter(getActivity());
            listViewModel.getPlace().observe(this, placeResponse -> {
                if (placeResponse !=null){
                    PlaceHeader placeHeader = placeResponse.getPlaceHeader();
                    String title = placeHeader.getTitle();
                    String subtitle = placeHeader.getSubtitle();
                    tvTitle.setText(title);
                    tvSubtitle.setText(subtitle);

                    listContentAdapter.setPlaceList(placeResponse.getPlaces());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    recyclerView.setAdapter(listContentAdapter);
                    progressBar.setVisibility(View.GONE);
                }
            });

            MaterialToolbar materialToolbar = getActivity().findViewById(R.id.mt_activity);
            materialToolbar.setNavigationIcon(null);
            materialToolbar.setTitle("List");
        }
    }
}
