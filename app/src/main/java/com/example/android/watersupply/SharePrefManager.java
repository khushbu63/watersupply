package com.example.android.watersupply;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.RequestQueue;

/**
 * Created by Anonymous on 1/26/2018.
 */

public class SharePrefManager {
    private static SharePrefManager mInstance;
    private static Context mCtx;
    private static final String SHARED_PREF_NAME="mysharedpref12";
    private static final String KEY_USERNAME="username";
    private static final String KEY_USER_EMAIL="useremail";
    private static final String KEY_USER_ID="userid";
    private  static final  String KEY_USERTYPE="usertype";
   // private static final String KEY_PHONE_NO="phone_no";

    private SharePrefManager(Context context) {
        mCtx = context;
       }

    public static synchronized SharePrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharePrefManager(context);
        }
        return mInstance;
    }
    public boolean userLogin(String id, String username,String email,String usertype){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        editor.putString(KEY_USER_ID,id);
        editor.putString(KEY_USER_EMAIL,email);
        editor.putString(KEY_USERNAME,username);
        editor.putString( KEY_USERTYPE,usertype);
      //  editor.putString( KEY_PHONE_NO,phone_no);
        editor.apply();

        return true;

    }
    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE);
        if(sharedPreferences.getString(KEY_USERNAME,null)!=null){
        return true;
        }
        return false;
    }
    public boolean Logout(){
      SharedPreferences sharedPreferences=mCtx.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE );
      SharedPreferences.Editor editor=sharedPreferences.edit();
      editor.clear();
      editor.apply();
      return  true;
    }
    public String getUsername(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString( KEY_USERNAME,null);
    }
    public String getUseremail(){
        SharedPreferences sharedPreferences=mCtx.getSharedPreferences( SHARED_PREF_NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getString( KEY_USER_EMAIL,null);
    }

}
