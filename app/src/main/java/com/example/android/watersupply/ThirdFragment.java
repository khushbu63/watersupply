package com.example.android.watersupply;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ThirdFragment extends Fragment {
    TextView uname,email;


    public ThirdFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(!SharePrefManager.getInstance(getContext()).isLoggedIn()) {
            startActivity(new Intent(getContext(), Login.class));
        }
        // Inflate the layout for this fragment
        View myview=inflater.inflate( R.layout.fragment_third, container, false );
        uname= (TextView)myview.findViewById(R.id.textviewuname);
        email=(TextView)myview.findViewById( R.id.textviewemail);
       // phone_no=(TextView)myview.findViewById( R.id.textviewphone);


        email.setText( SharePrefManager.getInstance( getActivity()).getUseremail());
        uname.setText( SharePrefManager.getInstance( getActivity()).getUsername());
      //  phone_no.setText( SharePrefManager.getInstance( getActivity().getPho ) );
     //   phone_no.setText( SharePrefManager.getInstance( getActivity().);
        //   uname.setText(SharePrefManager.getInstance(this)getUsername());
        //   email.setText( SharePrefManager.getInstance(this)getUserEmail());

        return myview;
    }
    }


