package com.example.gf_daniel.mioapp.Fragments;

import android.Manifest;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;

import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.PopupMenu;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.example.gf_daniel.mioapp.R;
import com.example.gf_daniel.mioapp.TrayectoActivity;
import com.example.gf_daniel.mioapp.classes.MyItem;
import com.example.gf_daniel.mioapp.classes.StoreItem;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.clustering.ClusterManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import static android.R.attr.id;
import static com.example.gf_daniel.mioapp.R.string.error;


/**
 * Created by GF_Daniel on 10/27/2017.
 */

public class MapaFragment extends Fragment implements OnMapReadyCallback {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private ClusterManager<MyItem> mClusterManager;
    private ProgressDialog mMensaje;
    Button button1;
    CuboidButton button;
    CuboidButton busquedaBtn;
    String Ruta;
    String idRuta;
    EditText editTextRuta;
    int mItem = 0;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().openOptionsMenu();
        mMensaje = new ProgressDialog(getActivity());

    }

    /*@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);
    }*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Nullable
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        MapFragment fragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.mapa);
        fragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {

        //CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(3.4516, -76.5320)).zoom(15).bearing(0).tilt(45).build();
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));




        /*GoogleApiClient apiClient = new GoogleApiClient.Builder(getActivity()).enableAutoManage(getChildFragmentManager().findFragmentById(R.id.mapa), new GoogleApiClient.OnConnectionFailedListener() {
            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                // connection failed, should be handled
            }
        }).addConnectionCallbacks(this).addApi(LocationServices.API).build();*/
        //GoogleApiClient apiClient = new GoogleApiClient.Builder(getActivity()).addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks) this)
                //.addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) this).addApi(LocationServices.API).build();


        /*if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }*/
        //Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);
        //Log.d("onCreateView", Boolean.toString(apiClient.isConnected()));

        //CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude())).zoom(15).bearing(0).tilt(45).build();
        //googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


        // Add a marker in Paris and move the camera
        LatLng paris = new LatLng(3.348908, -76.5315698);
        //googleMap.addMarker(new MarkerOptions().position(paris).title(" "));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(new LatLng(3.348908, -76.5315698)).zoom(15).bearing(0).tilt(45).build();
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        //googleMap.moveCamera(CameraUpdateFactory.newLatLng(paris));


        mClusterManager = new ClusterManager<MyItem>(getActivity(), googleMap);


        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(mClusterManager);


        button = (CuboidButton) getActivity().findViewById(R.id.filtros);
        //editTextRuta = (EditText) getActivity().findViewById(R.id.inputBuscar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getActivity(),button);
                popupMenu.getMenuInflater().inflate(R.menu.filtro_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int id = item.getItemId();
                        if (id == R.id.estaciones){
                                mItem=1;
                                try {
                                    mClusterManager.clearItems();
                                    StoreItem storeItem = new StoreItem(getActivity(), googleMap, mClusterManager);
                                    storeItem.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.station_bus));
                                    mClusterManager.setRenderer(storeItem);
                                    addItems(mItem,googleMap);
                                    //MarkerOptions markerOptions = new MarkerOptions();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else if(id == R.id.paradas){
                                mItem=0;
                                try {
                                    mClusterManager.clearItems();
                                    StoreItem storeItem = new StoreItem(getActivity(), googleMap, mClusterManager);
                                    storeItem.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.stop_bus));
                                    mClusterManager.setRenderer(storeItem);
                                    addItems(mItem,googleMap);

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else if (id== R.id.puntos_recargas){
                                try {
                                    mClusterManager.clearItems();
                                    StoreItem storeItem = new StoreItem(getActivity(), googleMap, mClusterManager);
                                    storeItem.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.card1));
                                    mClusterManager.setRenderer(storeItem);
                                    addItems1();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }else if (id== R.id.buses){
                                //Ruta = editTextRuta.getText().toString();
                                mClusterManager.clearItems();
                                StoreItem storeItem = new StoreItem(getActivity(), googleMap, mClusterManager);
                                storeItem.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.bus1));
                                mClusterManager.setRenderer(storeItem);
                                addItems2();
                            }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });




    }
    private void addItems(final int filtro, final GoogleMap googleMap) throws IOException {

        final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MioApp");
        if(!path.exists())
        {
            path.mkdirs();
        }

        final File file = new File(path, "stops.txt");
        try
        {
            file.createNewFile();
            final FileOutputStream fOut = new FileOutputStream(file);
            final OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("stops");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String id,location_type,parent_station,platform_code,stop_lat,stop_lon,stop_name;
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        id = postSnapshot.getKey();
                        location_type = postSnapshot.child("location_type").getValue().toString();
                        parent_station = postSnapshot.child("parent_station").getValue().toString();
                        platform_code = postSnapshot.child("platform_code").getValue().toString();
                        stop_lat = postSnapshot.child("stop_lat").getValue().toString();
                        stop_lon = postSnapshot.child("stop_lon").getValue().toString();
                        stop_name = postSnapshot.child("stop_name").getValue().toString();

                        if (location_type== new Integer(filtro).toString()){
                            MyItem offsetItem = new MyItem(Double.valueOf(stop_lat), Double.valueOf(stop_lon));
                            mClusterManager.addItem(offsetItem);
                            //LatLng sydney = new LatLng(Double.valueOf(stop_lat), Double.valueOf(stop_lon));
                            //Marker marker = googleMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.drawable.stop_bus)));
                            //marker.remove();

                        }
                        try {
                            myOutWriter.append(id+","+location_type+","+parent_station+","+platform_code+","+stop_lat+","+stop_lon+","+stop_name+"\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {myOutWriter.close();} catch (IOException e) {e.printStackTrace();}
                    try {fOut.flush();} catch (IOException e) {e.printStackTrace();}
                    try {fOut.close();} catch (IOException e) {e.printStackTrace();}
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }



    private void addItems1() throws IOException {

        final File path = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/MioApp");
        if(!path.exists())
        {
            path.mkdirs();
        }

        final File file = new File(path, "puntos_recargas.txt");
        try
        {
            file.createNewFile();
            final FileOutputStream fOut = new FileOutputStream(file);
            final OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            database = FirebaseDatabase.getInstance();
            databaseReference = database.getReference().child("PuntosRecarga");
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String id,nombre_local,direccion,estado,lat,lon,observacion;
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        id = postSnapshot.getKey();
                        nombre_local = postSnapshot.child("NOMBRE_LOC").getValue().toString();
                        direccion = postSnapshot.child("DIRECCION").getValue().toString();
                        estado = postSnapshot.child("ESTADO").getValue().toString();
                        lat = postSnapshot.child("LATITUD").getValue().toString();
                        lon = postSnapshot.child("LONGITUD").getValue().toString();
                        observacion = postSnapshot.child("OBSERVACIO").getValue().toString();


                        if (1==1){
                            MyItem offsetItem = new MyItem(Double.valueOf(lat), Double.valueOf(lon));
                            mClusterManager.addItem(offsetItem);
                            //LatLng sydney = new LatLng(Double.valueOf(stop_lat), Double.valueOf(stop_lon));
                            //Marker marker = googleMap.addMarker(new MarkerOptions().position(sydney).icon(BitmapDescriptorFactory.fromResource(R.drawable.stop_bus)));
                            //marker.remove();

                        }
                        try {
                            myOutWriter.append(id+","+nombre_local+","+direccion+","+estado+","+lat+","+lon+","+observacion+"\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    try {myOutWriter.close();} catch (IOException e) {e.printStackTrace();}
                    try {fOut.flush();} catch (IOException e) {e.printStackTrace();}
                    try {fOut.close();} catch (IOException e) {e.printStackTrace();}
                }
                @Override
                public void onCancelled(DatabaseError error) {
                }
            });
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }


    private void addItems2(){
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("posBuses");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String id,lat,lon;
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    lat = postSnapshot.child("latitude").getValue().toString();
                    lon = postSnapshot.child("longitude").getValue().toString();
                    MyItem offsetItem = new MyItem(Double.valueOf(lat), Double.valueOf(lon));
                    mClusterManager.addItem(offsetItem);
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
    }



    public void buscarOnclick(){

    }

}


