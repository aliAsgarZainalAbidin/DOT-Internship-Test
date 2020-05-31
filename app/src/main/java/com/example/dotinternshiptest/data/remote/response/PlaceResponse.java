package com.example.dotinternshiptest.data.remote.response;

import com.example.dotinternshiptest.data.remote.models.Place;
import com.example.dotinternshiptest.data.remote.models.PlaceHeader;

import java.util.ArrayList;

public class PlaceResponse {
    private PlaceHeader placeHeader;
    private ArrayList<Place> places;

    public PlaceResponse() {
    }

    public PlaceHeader getPlaceHeader() {
        return placeHeader;
    }

    public void setPlaceHeader(PlaceHeader placeHeader) {
        this.placeHeader = placeHeader;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }
}
