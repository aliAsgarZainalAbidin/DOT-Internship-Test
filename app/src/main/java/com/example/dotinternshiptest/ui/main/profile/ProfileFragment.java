package com.example.dotinternshiptest.ui.main.profile;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.dotinternshiptest.R;
import com.example.dotinternshiptest.data.remote.models.Profile;
import com.example.dotinternshiptest.viewmodel.ViewModelFactory;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView circleImageView;
    TextView tvUsername;
    TextView tvFullname;
    TextView tvPhone;
    TextView tvEmail;
    MaterialToolbar materialToolbar;

    public ProfileFragment() {
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        circleImageView = view.findViewById(R.id.civ_profile);
        tvUsername = view.findViewById(R.id.tv_profile_username);
        tvFullname = view.findViewById(R.id.tv_profile_fullname);
        tvPhone = view.findViewById(R.id.tv_profile_phone);
        tvEmail = view.findViewById(R.id.tv_profile_email);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity()!=null){
            ViewModelFactory viewModelFactory = ViewModelFactory.getINSTANCE();
            ProfileViewModel profileViewModel = new ViewModelProvider(this,viewModelFactory).get(ProfileViewModel.class);
            profileViewModel.getProfile().observe(this,profiles -> {
                Profile profile = profiles.get(0);
                Glide.with(getActivity())
                        .load(profile.getAvatar())
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_baseline_account_circle_24).error(R.drawable.ic_baseline_account_circle_24))
                        .into(circleImageView);
                tvUsername.setText(profile.getUsername());
                tvFullname.setText(profile.getFullname());
                tvPhone.setText(profile.getPhone());
                tvEmail.setText(profile.getEmail());
            } );

            MaterialToolbar materialToolbar = getActivity().findViewById(R.id.mt_activity);
            materialToolbar.setNavigationOnClickListener(v -> {
                BottomNavigationView bottomNavigationView = getActivity().findViewById(R.id.bottom_navbar);
                bottomNavigationView.setSelectedItemId(R.id.list_bottomnavbar_menu);
            });
        }
    }

}