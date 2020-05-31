package com.example.dotinternshiptest.util;

import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.dotinternshiptest.data.remote.RemoteDataSource;
import com.example.dotinternshiptest.data.remote.models.Gallery;
import com.example.dotinternshiptest.data.remote.models.Place;
import com.example.dotinternshiptest.data.remote.models.PlaceHeader;
import com.example.dotinternshiptest.data.remote.models.Profile;
import com.example.dotinternshiptest.data.remote.response.PlaceResponse;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class JsonHelper {

    private AsyncHttpClient client = new AsyncHttpClient();

    public PlaceResponse loadPlace(RemoteDataSource.LoadPlaceCallback callback) {
        final PlaceResponse placeResponseModel = new PlaceResponse();
        String url = "https://dot-android-internship-test.web.app/place.json";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                ArrayList<Place> placeArrayList = new ArrayList<>();
                try {
                    JSONObject respons = new JSONObject(result);
                    JSONObject data = respons.getJSONObject("data");
                    JSONObject header = data.getJSONObject("header");
                    String titleHeader = header.getString("title");
                    String subtitleHeader = header.getString("subtitle");
                    PlaceHeader placeHeader = new PlaceHeader(titleHeader, subtitleHeader);

                    JSONArray content = data.getJSONArray("content");
                    for (int i = 0; i < content.length(); i++) {
                        JSONObject place = content.getJSONObject(i);
                        int id = place.getInt("id");
                        String title = place.getString("title");
                        String contentPlace = place.getString("content");
                        String type = place.getString("type");
                        String image = place.getString("image");
                        JSONArray mediaArray = place.getJSONArray("media");
                        ArrayList<String> media = new ArrayList<>();

                        for (int items = 0; items < mediaArray.length(); items++) {
                            media.add(mediaArray.getString(items));
                        }
                        Place placeModel = new Place(id, title, contentPlace, type, image, media);
                        placeArrayList.add(placeModel);
                    }
                    placeResponseModel.setPlaceHeader(placeHeader);
                    placeResponseModel.setPlaces(placeArrayList);
                    callback.onPlaceReceived(placeResponseModel);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
        return placeResponseModel;
    }

    public List<Gallery> loadGallery(RemoteDataSource.LoadGalleryCallback callback) {
        List<Gallery> galleryList = new ArrayList<>();
        String url = "https://dot-android-internship-test.web.app/gallery.json";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray data = jsonObject.getJSONArray("data");

                    for (int i = 0; i < data.length(); i++) {
                        JSONObject gallery = data.getJSONObject(i);
                        String caption = gallery.getString("caption");
                        String thumbnail = gallery.getString("thumbnail");
                        String image = gallery.getString("image");

                        Gallery galleryModel = new Gallery(caption, thumbnail, image);
                        galleryList.add(galleryModel);
                    }
                    callback.onGalleryCallback(galleryList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return galleryList;
    }

    public List<Profile> loadProfile(RemoteDataSource.LoadProfileCallback callback) {
        List<Profile> profileList = new ArrayList<>();
        String url = "https://dot-android-internship-test.web.app/user.json";
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = new String(responseBody);
                try {
                    JSONObject resultResponse = new JSONObject(result);
                    JSONObject dataResponse = resultResponse.getJSONObject("data");
                    int id = dataResponse.getInt("id");
                    String username = dataResponse.getString("username");
                    String fullname = dataResponse.getString("fullname");
                    String email = dataResponse.getString("email");
                    String phone = dataResponse.getString("phone");
                    String avatar = dataResponse.getString("avatar");

                    Profile profile = new Profile(id, username, fullname, email, phone, avatar);
                    profileList.add(profile);
                    callback.onProfileReceived(profileList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

        return profileList;
    }

}
