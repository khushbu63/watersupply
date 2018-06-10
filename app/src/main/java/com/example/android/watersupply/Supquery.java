package com.example.android.watersupply;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Supquery extends AppCompatActivity {



    List<Modal> dataList;

    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sup_query );

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        dataList = new ArrayList<>();

        loadData();

    }
   public void loadData() {
       StringRequest stringRequest = new StringRequest( Request.Method.POST,Constants.FETCHQUERY,
               new Response.Listener<String>() {

                   @Override
                   public void onResponse(String response) {
                       Log.e("res",response.toString());
                       try {

                           JSONArray array = new JSONArray( response );
                           for (int i = 0; i < array.length(); i++) {
                               JSONObject data = array.getJSONObject( i );

                               dataList.add(new Modal(
                                       data.getString("username"),
                                       data.getString( "topic" ),
                                       data.getString( "detail")
                               ));

                           }
                           DataAdapter adapter = new DataAdapter(Supquery.this,dataList);
                           RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                           recyclerView.setLayoutManager(mLayoutManager);
                           recyclerView.setItemAnimator(new DefaultItemAnimator());
                           recyclerView.setAdapter(adapter);
                       } catch (JSONException e) {
                           e.printStackTrace();
                       }
                   }

               }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

           }

       } );


       Appcontroller.getInstance(this).addToRequestQueue( stringRequest );
   }
   }



