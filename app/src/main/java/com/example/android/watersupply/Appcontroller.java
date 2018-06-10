package com.example.android.watersupply;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Created by Anonymous on 1/11/2018.
 */

public class Appcontroller {
        private static Appcontroller mInstance;
        private RequestQueue mRequestQueue;

        private static Context mCtx;

        private Appcontroller(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();

        }

        public static synchronized Appcontroller getInstance(Context context) {
            if (mInstance == null) {
                mInstance = new Appcontroller(context);
            }
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return mRequestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req) {
            getRequestQueue().add(req);
        }
    }





