package com.example.dotinternshiptest.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dotinternshiptest.data.remote.RemoteDataSource;
import com.example.dotinternshiptest.data.remote.models.Gallery;
import com.example.dotinternshiptest.data.remote.models.Profile;
import com.example.dotinternshiptest.data.remote.response.PlaceResponse;

import java.util.List;

public class Repository {
    public static volatile Repository INSTANCE;
    private final RemoteDataSource remoteDataSource;

    public Repository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static Repository getINSTANCE(RemoteDataSource remoteDataSource) {
        if (INSTANCE == null){
            synchronized (Repository.class){
                if (INSTANCE==null){
                    INSTANCE = new Repository(remoteDataSource);
                }
            }
        }
        return INSTANCE;
    }

    public LiveData<PlaceResponse> getPlace(){
        MutableLiveData<PlaceResponse> placeResponseMutableLiveData = new MutableLiveData<>();
        remoteDataSource.getPlaceResponse(placeResponseMutableLiveData::postValue);
        return placeResponseMutableLiveData;
    }

    public LiveData<List<Gallery>> getGallery(){
        MutableLiveData<List<Gallery>> galleryListMutableLiveData = new MutableLiveData<>();
        remoteDataSource.getGalleryResponse(galleryListMutableLiveData::postValue);
        return galleryListMutableLiveData;
    }

    public LiveData<List<Profile>> getProfile(){
        MutableLiveData<List<Profile>> profileListMutableLiveData = new MutableLiveData<>();
        remoteDataSource.getProfileResponse(profileListMutableLiveData::postValue);
        return profileListMutableLiveData;
    }

}
