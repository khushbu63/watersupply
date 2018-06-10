package com.example.android.watersupply;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.HashMap;
import java.util.Map;

public class Query extends AppCompatActivity implements View.OnClickListener{

   private EditText edittitle,editdesc;
   private Button insert;
    private ProgressDialog progressDialog;
    private static final String SHARED_PREF_NAME="mysharedpref12";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_query );

        edittitle=(EditText)findViewById(R.id.edittitle);
        editdesc=(EditText)findViewById( R.id.editdesc);
        insert=(Button)findViewById( R.id.insert);

        insert.setOnClickListener( this );
        progressDialog = new ProgressDialog( this );


    }

    private void query(){
       final String topic=edittitle.getText().toString().trim();
       final String detail=editdesc.getText().toString().trim();

        Intent intent = new Intent( Query.this,DrawerActivity.class );
        startActivity( intent );

        progressDialog.setMessage( " query inserted..." );
        progressDialog.show();


        StringRequest stringRequest=new StringRequest( Request.Method.POST, Constants.QUERYSTATUS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        progressDialog.dismiss();
                        Log.e("res",response);
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
                Toast.makeText( Query.this, error.getMessage(), Toast.LENGTH_LONG ).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params=new HashMap<>();
               // params.put( "id",id );
                SharedPreferences sharedPreferences=getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
                params.put( "id", sharedPreferences.getString("userid","14" ) );
                params.put( "topic",topic);
                params.put( "detail",detail);
                return  params;
            }
        };

        Appcontroller.getInstance( this ).addToRequestQueue( stringRequest );
    }

    @Override
    public void onClick(View v) {
       if(v==insert){
           query();
       }
    }
}
