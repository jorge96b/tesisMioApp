package com.example.gf_daniel.mioapp.classes;

import android.content.Context;

import com.example.gf_daniel.mioapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

/**
 * Created by GF_Daniel on 12/4/2017.
 */

public class StoreItem extends DefaultClusterRenderer<MyItem> {
    public  BitmapDescriptor icon;
    public StoreItem(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);
    }

    public void setIcon(BitmapDescriptor ic){
        this.icon=ic;
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem item, MarkerOptions markerOptions ) {
        //BitmapDescriptor icon = BitmapDescriptorFactory.fromResource(R.drawable.stop_bus);
        markerOptions.icon(icon);
        markerOptions.title("parada");
        super.onBeforeClusterItemRendered(item, markerOptions);
    }
}
