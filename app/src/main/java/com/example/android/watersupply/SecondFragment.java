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


public class SecondFragment extends Fragment {

    TextView sup_name,sup_email,firm_name;

    public SecondFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View myview= inflater.inflate( R.layout.fragment_second, container, false );

        sup_name=(TextView)myview.findViewById(R.id.textviewsupname);
        sup_email=(TextView)myview.findViewById( R.id.textviewsupemail);
     //   firm_name=(TextView)myview.findViewById( R.id.textviewfirmname);
        // phone_no=(TextView)myview.findViewById( R.id.textviewphone);


        sup_email.setText(Sharedpref.getInstance( getActivity()).getSupname());
        sup_name.setText(Sharedpref.getInstance(getActivity()).getSupemail());
      //  firm_name.setText(Sharedpref.getInstance(getActivity()).getFirmname());

        //   phone_no.setText( SharePrefManager.getInstance( getActivity().);
        //   uname.setText(SharePrefManager.getInstance(this)getUsername());
        //   email.setText( SharePrefManager.getInstance(this)getUserEmail());

        return myview;
    }
}








