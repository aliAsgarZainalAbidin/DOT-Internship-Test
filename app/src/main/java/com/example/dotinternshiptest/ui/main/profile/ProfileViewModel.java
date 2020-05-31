package com.example.dotinternshiptest.ui.main.profile;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dotinternshiptest.data.Repository;
import com.example.dotinternshiptest.data.remote.models.Profile;

import java.util.List;

public class ProfileViewModel extends ViewModel {
    private Repository repository;

    public ProfileViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Profile>> getProfile(){
        return repository.getProfile();
    }
}
