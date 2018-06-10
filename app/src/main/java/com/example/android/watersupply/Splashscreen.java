package com.example.android.watersupply;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Splashscreen extends AppCompatActivity {
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private ProgressDialog progressDialog;

    private static int SPLASH_TIME_OUT=2000;
    //private static Context mCtx;
   // private static final String SHARED_PREF_NAME="mysharedpref12";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splashscreen );

        progressDialog=new ProgressDialog( this );
        //spash screen timer
        new Handler().postDelayed( new Runnable() {
            @Override
            public void run() {

                final SharedPreferences sharedPreferences = getSharedPreferences( SHARED_PREF_NAME, Context.MODE_PRIVATE );
                if (sharedPreferences.getString( "supid", "" ).equals( "" )) {
                     Intent intent=new Intent( Splashscreen.this,MainScreen.class );
                     startActivity( intent );
                     }
                      else{
                    if(sharedPreferences.getString( "userid","" ).equals( "" )){
                        Intent intent=new Intent(Splashscreen.this,MainScreen.class);
                    }
                }
                {
                    if (sharedPreferences.getString( "usertype", "0" ).equals( "0" )) {
                        if (!SharePrefManager.getInstance(Splashscreen.this ).isLoggedIn()) {
                            finish();
                            startActivity( new Intent( Splashscreen.this,MainScreen.class ) );
                            return;
                        } else {
                            Intent intent = new Intent( Splashscreen.this, DrawerActivity.class );
                            startActivity( intent );
                        }
                    }else {
                         StringRequest stringRequest = new StringRequest( Request.Method.POST, Constants.SUPSTATUS, new
                                Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.e( "res", response );
                                        try {
                                            JSONObject jsonObject = new JSONObject( response );
                                            if (!jsonObject.getBoolean( "error" )) {

                                                if (jsonObject.getString( "approve" ).equals( "on" )) {
                                                    Intent intent = new Intent( Splashscreen.this, Supnavigation.class );
                                                    startActivity( intent );
                                                } else {
                                                    Intent intent = new Intent( Splashscreen.this, Supsplash.class );
                                                    startActivity( intent );
                                                }

                                            } else {
                                                Toast.makeText( getApplicationContext(), jsonObject.getString( "message" ), Toast.LENGTH_LONG ).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progressDialog.dismiss();
                                Toast.makeText( getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG ).show();
                            }
                        } ) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put( "sup_id", sharedPreferences.getString( "supid", "" ) );
                                return params;
                            }
                        };
                        Appcontroller.getInstance( Splashscreen.this ).addToRequestQueue( stringRequest );
                    }
                }
            }
        },SPLASH_TIME_OUT);
    }
}
