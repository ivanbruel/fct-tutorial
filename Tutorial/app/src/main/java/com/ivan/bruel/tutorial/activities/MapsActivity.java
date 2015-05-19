package com.ivan.bruel.tutorial.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ivan.bruel.tutorial.R;

public class MapsActivity extends AppCompatActivity {

    private static final LatLng FCT = new LatLng(38.660291,-9.2048857);
    private static final LatLng COSTA = new LatLng(38.6445049,-9.2340321);
    private static final LatLng PONTE = new LatLng(38.6822629,-9.1755635);

    private GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                .getMap();
        addMarkers();
        moveCamera();
    }

    private void addMarkers() {
        Marker fctMarker = map.addMarker(new MarkerOptions().position(FCT).title("FCT").snippet("FCT IS COOL"));
        Marker costaMarker = map.addMarker(new MarkerOptions().position(COSTA).title("Costa da Caparica").snippet("Let's go to the beach?"));
        Marker ponteMarker = map.addMarker(new MarkerOptions().position(PONTE).title("Ponte 25 de Abril").snippet("I can see the traffic from here!"));
    }

    private void moveCamera() {
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(FCT, 13));
    }
}
