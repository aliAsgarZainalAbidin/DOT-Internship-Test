package com.example.dotinternshiptest.data;

import androidx.lifecycle.LiveData;

import com.example.dotinternshiptest.data.remote.models.Gallery;
import com.example.dotinternshiptest.data.remote.models.Profile;
import com.example.dotinternshiptest.data.remote.response.PlaceResponse;

public interface DataSource {
    LiveData<PlaceResponse> getPlace();
    LiveData<Gallery> getGallery();
    LiveData<Profile> getProfile();
}
