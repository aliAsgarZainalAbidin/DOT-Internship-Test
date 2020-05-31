package com.example.dotinternshiptest.data.remote;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.dotinternshiptest.data.DataSource;
import com.example.dotinternshiptest.data.remote.models.Gallery;
import com.example.dotinternshiptest.data.remote.models.Profile;
import com.example.dotinternshiptest.data.remote.response.PlaceResponse;
import com.example.dotinternshiptest.util.JsonHelper;

import java.util.List;

public class RemoteDataSource {

    private static RemoteDataSource INSTANCE;
    private final JsonHelper jsonHelper;

    public RemoteDataSource(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteDataSource getINSTANCE(JsonHelper jsonHelper) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteDataSource(jsonHelper);
        }
        return INSTANCE;
    }

    public LiveData<PlaceResponse> getPlaceResponse(LoadPlaceCallback callback) {
        MutableLiveData<PlaceResponse> placeResponseMutableLiveData = new MutableLiveData<>();
        placeResponseMutableLiveData.setValue(jsonHelper.loadPlace(callback));
        return placeResponseMutableLiveData;
    }

    public LiveData<List<Gallery>> getGalleryResponse(LoadGalleryCallback callback){
        MutableLiveData<List<Gallery>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(jsonHelper.loadGallery(callback));
        return listMutableLiveData;
    }

    public LiveData<List<Profile>> getProfileResponse(LoadProfileCallback callback){
        MutableLiveData<List<Profile>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(jsonHelper.loadProfile(callback));
        return listMutableLiveData;
    }

    public interface LoadPlaceCallback {
        void onPlaceReceived(PlaceResponse response);
    }

    public interface LoadGalleryCallback {
        void onGalleryCallback(List<Gallery> gallery);
    }

    public interface LoadProfileCallback {
        void onProfileReceived(List<Profile> profile);
    }
}
