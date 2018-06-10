package com.example.android.watersupply;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

public class Suplogin extends AppCompatActivity implements View.OnClickListener{
    private EditText email,password;
    private Button login;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_suplogin );

        //add back button
        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled( true );
            getSupportActionBar().setDisplayShowHomeEnabled( true );
        }

        email=(EditText)findViewById( R.id.semail);
        password=(EditText)findViewById( R.id.spass );

        login=(Button)findViewById( R.id.slogin );

        progressDialog=new ProgressDialog( this );
        progressDialog.setMessage( "Please Wait..." );
        login.setOnClickListener( this );
    }

    private void supLogin(){
        final String uemail=email.getText().toString().trim();
        final String pass=password.getText().toString().trim();


        if(TextUtils.isEmpty( uemail )){
            email.setError("Enter email");
            email.requestFocus();
            return;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(uemail).matches()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            password.setError("Enter password");
            password.requestFocus();
            return;
        }

        StringRequest stringRequest=new StringRequest( Request.Method.POST, Constants.LOGIN_SUP_URL,new
                Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("res",response);
                        try{
                            JSONObject jsonObject=new JSONObject(response);
                            if(!jsonObject.getBoolean("error")){
                                Sharedpref.getInstance(Suplogin.this).supLogin(
                                        jsonObject.getString( "sup_id" ),
                                        jsonObject.getString( "usertype" ),
                                        jsonObject.getString( "sup_name" ),
                                        jsonObject.getString( "sup_email" ));
                                     //   jsonObject.getString( "sup_firm_name"));
                                        if(jsonObject.getString( "approve").equals( "on" )){
                                            Intent intent=new Intent(Suplogin.this,Supnavigation.class);
                                            startActivity( intent );
                                }
                                else
                                        {
                                            Intent intent=new Intent( Suplogin.this,Supsplash.class);
                                            startActivity( intent );
                                        }

                            }else{
                                Toast.makeText(Suplogin.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();                      }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Suplogin.this,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        } ){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError {
                Map<String,String>params=new HashMap<>();
                params.put("sup_email", uemail);
                params.put("sup_password", pass);
                return params;
            }
        };
        Appcontroller.getInstance(this).addToRequestQueue( stringRequest );

    }

    @Override
    public void onClick(View v) {
        if(v==login){
            supLogin();
        }
    }
    }

