package com.example.android.watersupply;

import android.nfc.Tag;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Customerloc extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    PlaceAutocompleteFragment placeAutocompleteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_customerloc );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                Log.d("Maps", "Place selected: " + place.getName());
                addMarker(place);
            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                Log.d("Maps", "An error occurred: " + status);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
               // Add a marker in Sydney and move the camera
    }
    public void addMarker(Place p){
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(p.getLatLng());
        markerOptions.title(p.getName()+"");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker( BitmapDescriptorFactory.HUE_AZURE));

        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(p.getLatLng()));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }
}
