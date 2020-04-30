package com.example.e_go_driver;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.firebase.geofire.GeoFire;
import com.github.kmenager.materialanimatedswitch.MaterialAnimatedSwitch;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;

import java.util.Objects;

public class welcome extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    private static final int MY_PERMISSION_REQUEST_CODE = 1000;
    private static final int PLAY_SERVICE_RES_REQUEST = 1001;

    private LocationRequest mLocationRequest;
    private GoogleApi<com.google.android.gms.common.api.Api.ApiOptions> mGoogleApiClient;
    private Location mLastLocation;

    private static int UPDATE_INTERVAL = 5000;
    private static int FASTEST_INTERVAL = 3000;
    private static int DISPLACEMENT = 10;

    DatabaseReference drivers;
    GeoFire geoFire;

    MaterialAnimatedSwitch location_switch;
    SupportMapFragment mapFragment;

    Marker mCurrent;
    //TODO enter my local
    private boolean mLocationPermissionGranted;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private Location mLastKnownLocation;
//    private static final String TAG = welcome.class.getSimpleName();
//    private static final int DEFAULT_ZOOM = 15;
//    private final LatLng mDefaultLocation = new LatLng(9.816975, 80.249977);
    private Marker mCurrLocationMarker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        //TODO tisanthan

        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

        // Add a marker in Sydney and move the camera
        LatLng PointPedro = new LatLng(9.816975, 80.249977);
        mMap.addMarker(new MarkerOptions().position(PointPedro).title("Marker in PointPedro"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(PointPedro));

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}