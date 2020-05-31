package com.example.dotinternshiptest.ui.main.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dotinternshiptest.data.Repository;
import com.example.dotinternshiptest.data.remote.models.Gallery;

import java.util.List;

public class GalleryViewModel extends ViewModel {
    private Repository repository;

    public GalleryViewModel(Repository repository) {
        this.repository = repository;
    }

    public LiveData<List<Gallery>> getGallery(){
        return repository.getGallery();
    }
}
