package com.example.android.watersupply;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity implements View.OnClickListener {

    private EditText uname, uemail, uphone_no, upassword;
    private Button btnreg;
    private ProgressDialog progressDialog;
    private TextView textviewlogin;
    private RadioButton sup, cus;


    // private Button btnlogin;

    //  private ProgressDialog pDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_signup );


        final RadioGroup radio = (RadioGroup)findViewById(R.id.radio);
        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // first button
                        Intent intent=new Intent( signup.this,signup.class);
                        startActivity( intent );
                        //  Toast.makeText(getApplicationContext(), "Selected button number " + index, 500).show();
                        break;
                    case 1: // secondbutton
                        Intent sup=new Intent(signup.this,supregistration.class);
                        startActivity( sup );
                        // Toast.makeText(getApplicationContext(), "Selected button number " + index, 500).show();
                        break;
                }
            }
        });


        //add back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            getSupportActionBar().setDisplayShowHomeEnabled( true );
        }


        uname = (EditText) findViewById( R.id.uname );
        uemail = (EditText) findViewById( R.id.email );
        upassword = (EditText) findViewById( R.id.pass );
        uphone_no = (EditText) findViewById( R.id.phone_no );
        sup = (RadioButton) findViewById( R.id.sup );
        cus = (RadioButton) findViewById( R.id.cus );



        btnreg = (Button) findViewById( R.id.reg );

        progressDialog = new ProgressDialog( this );
        textviewlogin = (TextView) findViewById( R.id.textviewlogin );

        btnreg.setOnClickListener( this );
        //  getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        textviewlogin.setOnClickListener( this );
    }

    private void registeruser() {
        final String username = uname.getText().toString().trim();
        final String email = uemail.getText().toString().trim();
        final String phone_no = uphone_no.getText().toString().trim();
        final String password = upassword.getText().toString().trim();


        if (TextUtils.isEmpty( username )) {
            uname.setError( "Enter username" );
            uname.requestFocus();
            return;
        }

        if (TextUtils.isEmpty( email )) {
            uemail.setError( "Enter email" );
            uemail.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher( email ).matches()) {
            uemail.setError( "Enter a valid email" );
            uemail.requestFocus();
            return;
        }
        if (TextUtils.isEmpty( phone_no )) {
            uphone_no.setError( "Enter phone no" );
            uphone_no.requestFocus();
            return;
        }
        if (TextUtils.isEmpty( password )) {
            upassword.setError( "Enter password" );
            upassword.requestFocus();
            return;
        } else {
            Intent intent = new Intent( signup.this, Login.class );
            startActivity( intent );
        }


        progressDialog.setMessage( "Regestring user..." );
        progressDialog.show();


        StringRequest stringRequest = new StringRequest( Request.Method.POST, Constants.REGISTER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d( "res",response );
                progressDialog.dismiss();
                try {
                    JSONObject jsonObject = new JSONObject( response );
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.hide();
                Toast.makeText( getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG ).show();

            }
        } ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put( "username", username );
                params.put( "email", email );
                params.put( "phone_no", phone_no );
                params.put( "password", password );
              //  params.put( "usetype",usertype );
                return params;

            }
        };

        Appcontroller.getInstance( this ).addToRequestQueue( stringRequest );
        // RequestQueue requestQueue=Volley.newRequestQueue( this );
        // requestQueue.add(stringRequest);

    }


    @Override
    public void onClick(View v) {
        if (v == btnreg)
            registeruser();
        //startActivity( new Intent( this, Login.class ) );

        if (v == textviewlogin)
            startActivity( new Intent( this, Login.class ) );
    }


    }








