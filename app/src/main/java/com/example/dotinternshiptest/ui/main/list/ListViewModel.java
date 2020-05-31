package com.example.dotinternshiptest.ui.main.list;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dotinternshiptest.data.Repository;
import com.example.dotinternshiptest.data.remote.response.PlaceResponse;

import static android.content.ContentValues.TAG;

public class ListViewModel extends ViewModel {
    private Repository repository;

    public ListViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<PlaceResponse> getPlace(){
        return repository.getPlace();
    }

}
