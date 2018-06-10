package com.example.android.watersupply;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Anonymous on 3/12/2018.
 */

public class Sharedpref {
    private static Sharedpref mInstance;
    private static Context context;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_SUP_ID="supid";
    private static final String KEY_SUP_NAME="sup_name";
    private static final String KEY_approve="approve";
    private static final String KEY_SUP_EMAIL="sup_email";
    private static final String USERTYPE="usertype";
    //private static final String KEY_SUP_FIRM_NAME="sup_firm_name";


   // Context
   // Context _context;


    private Sharedpref(Context context) {
       this.context= context;
    }
    public static synchronized Sharedpref getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new Sharedpref(context);
        }
        return mInstance;
    }
    public boolean supLogin(String sup_id, String usertype,String sup_name,String sup_email){
        SharedPreferences sharedPreferences=context.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(KEY_SUP_ID,sup_id);
       // editor.putString( KEY_approve,approve);
       editor.putString( KEY_SUP_NAME,sup_name);
        editor.putString( KEY_SUP_EMAIL,sup_email);
       // editor.putString( KEY_SUP_FIRM_NAME,sup_firm_name);
        editor.putString(USERTYPE,usertype);
        editor.apply();
        return true;
    }


    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=context.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_SUP_NAME,null)!=null) {
            return true;
        }
        return false;
  }
    public boolean Logout(){
        SharedPreferences sharedPreferences=context.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE );
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return  true;
    }
    public String getSupname(){
        SharedPreferences sharedPreferences=context.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SUP_NAME,null);
    }
    public String getSupemail(){
        SharedPreferences sharedPreferences=context.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_SUP_EMAIL,null);
    }

}
