package com.faberventures.ivanbruel.testapp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    ListView mListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mListView = (ListView)view.findViewById(R.id.listView);
        setupListView();
        return view;
    }

    private void setupListView() {
        final List<Location> locationList = new ArrayList<>();
        locationList.add(new Location("Qualquer coisa", new LatLng(34,-7), "Descrição", "http://upload.wikimedia.org/wikipedia/commons/a/a0/Google_favicon_2012.jpg"));
        locationList.add(new Location("Qualquer coisa 2", new LatLng(34,-7), "Descrição", "http://upload.wikimedia.org/wikipedia/commons/a/a0/Google_favicon_2012.jpg"));
        locationList.add(new Location("Qualquer coisa 3", new LatLng(34,-7), "Descrição", "http://upload.wikimedia.org/wikipedia/commons/a/a0/Google_favicon_2012.jpg"));
        locationList.add(new Location("Qualquer coisa 4", new LatLng(34,-7), "Descrição", "http://upload.wikimedia.org/wikipedia/commons/a/a0/Google_favicon_2012.jpg"));
        MainAdapter adapter = new MainAdapter(getActivity(), locationList);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Location clickedLocation = locationList.get(i);
                Toast.makeText(getActivity(), "Cliquei a location "+ clickedLocation.getName(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
