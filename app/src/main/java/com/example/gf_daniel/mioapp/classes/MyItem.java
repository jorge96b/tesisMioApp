package com.example.gf_daniel.mioapp.classes;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by GF_Daniel on 10/30/2017.
 */

public class MyItem implements ClusterItem {
    private final LatLng mPosition;


    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }
}
