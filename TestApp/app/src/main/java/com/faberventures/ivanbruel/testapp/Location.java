package com.faberventures.ivanbruel.testapp;

import com.google.android.gms.maps.model.LatLng;

public class Location {

    private String mName;
    private LatLng mCoordinates;
    private String mDescription;
    private String mImageURL;

    public Location(String name, LatLng coordinates, String description, String imageURL) {
        mName = name;
        mCoordinates = coordinates;
        mDescription = description;
        mImageURL = imageURL;
    }

    public String getImageURL() {
        return mImageURL;
    }

    public void setImageURL(String imageURL) {
        mImageURL = imageURL;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public LatLng getCoordinates() {
        return mCoordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        mCoordinates = coordinates;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }
}
