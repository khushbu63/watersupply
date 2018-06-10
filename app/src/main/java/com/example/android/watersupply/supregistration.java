package com.example.android.watersupply;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class supregistration extends AppCompatActivity implements View.OnClickListener, OnMapReadyCallback {

    private EditText uname,firmname,email,phone_no,pass;
    private String strlat,strlng;
    Button reg;
    ProgressDialog progressDialog;
    private TextView textviewsuplogin;
    private GoogleMap mMap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_supregistration );

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById( R.id.map );
        mapFragment.getMapAsync( this );



        uname=(EditText)findViewById(R.id.sname);
        firmname=(EditText)findViewById(R.id.firmname);
        email=(EditText)findViewById(R.id.email);
        phone_no=(EditText)findViewById(R.id.phone_no);
        pass=(EditText)findViewById( R.id.pass);

        reg=(Button)findViewById(R.id.reg);

        progressDialog=new ProgressDialog(this);
        textviewsuplogin = (TextView) findViewById( R.id.textviewsuplogin );

        textviewsuplogin.setOnClickListener( this );
        reg.setOnClickListener(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng point) {
                // TODO Auto-generated method stub
              //  lstLatLngs.add(point);
                //point.latitude
                strlat="";
                strlng="";
                strlat=point.latitude+"";
                strlng=point.longitude+"";
                mMap.clear();
                mMap.addMarker(new MarkerOptions().position(point));
            }
        });
        mMap.setOnMarkerClickListener( new GoogleMap.OnMarkerClickListener() {

            @Override
            public boolean onMarkerClick(Marker marker) {
                return false;
            }
        } );
        //ArrayList<Marker> markerArrayList=new ArrayList<Marker>();
      //  for(int i=0;i<markerArrayList.size();i++) {
      //      createMarker = markerArrayList.get( i ).getLatitude(),

     //   }
        // Add a marker in Sydney and move the camera




      //mMap.setMyLocationEnabled( true );
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mMap.setMyLocationEnabled(true);
    }



    private void registersup(){
        final String sname=uname.getText().toString().trim();
        final String fname=firmname.getText().toString().trim();
        final String semail=email.getText().toString().trim();
        final String sphone=phone_no.getText().toString().trim();
        final String spass=pass.getText().toString().trim();



        if (TextUtils.isEmpty( sname )) {
            uname.setError( "Enter username" );
            uname.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(fname)) {
            firmname.setError( "Enter username" );
            firmname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty( semail)) {
            email.setError( "Enter email" );
            email.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher( semail ).matches()) {
            email.setError( "Enter a valid email" );
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty( sphone )) {
            phone_no.setError( "Enter phone no" );
            phone_no.requestFocus();
            return;
        }
        if (TextUtils.isEmpty( spass )) {
            pass.setError( "Enter password" );
            pass.requestFocus();
            return;
        } else {
            Intent intent = new Intent(supregistration.this, Suplogin.class );
            startActivity( intent );
        }



        progressDialog.setMessage("Register supplier...");
        progressDialog.show();

        StringRequest stringRequest=new StringRequest( Request.Method.POST, Constants.REGISTER_SUP_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("res",response);
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject( response );
                    Toast.makeText( getApplicationContext(), jsonObject.getString( "message" ), Toast.LENGTH_LONG ).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        } ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put( "sup_name", sname );
                params.put( "sup_firm_name", fname );
                params.put( "sup_email",semail);
                params.put( "sup_mob_no", sphone );
                params.put("sup_password",spass);
                params.put( "lat",strlat );
                params.put( "longetude",strlng );
                return params;

            }
        };
        Appcontroller.getInstance( this ).addToRequestQueue(stringRequest);
    }

    @Override
    public void onClick(View v) {

        if (v == reg)
            registersup();

        //startActivity( new Intent( this, Login.class ) );

        if (v == textviewsuplogin)
            startActivity( new Intent( this, Suplogin.class ) );
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

