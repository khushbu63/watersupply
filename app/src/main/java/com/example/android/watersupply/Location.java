package com.example.android.watersupply;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Location extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_location );
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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

        StringRequest stringRequest=new StringRequest( Request.Method.POST, Constants.FETCHSUPADD,new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res",response);
                        try{
                           // JSONObject jsonObject=new JSONObject(response);
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                Log.e("res",response);
                                JSONObject jsonObj = jsonArray.getJSONObject(i);
                                mMap.addMarker(new MarkerOptions()

                                        .title(jsonObj.getString("sup_name"))
                                        .position(new LatLng(
                                            Double.parseDouble( jsonObj.getString("lat"))  ,
                                            Double.parseDouble( jsonObj.getString("longetude"))
                                        ))
                                );
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        } ){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
               return  params;
            }
        };
        Appcontroller.getInstance( this ).addToRequestQueue( stringRequest );

        // Add a marker in Sydney and move the camera
       // LatLng sydney = new LatLng( -34, 151 );
      //  mMap.addMarker( new MarkerOptions().position( sydney ).title( "Marker in Sydney" ) );
    //    mMap.moveCamera( CameraUpdateFactory.newLatLng( sydney ) );
    }
}
