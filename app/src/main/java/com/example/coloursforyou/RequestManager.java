package com.example.coloursforyou;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

class RequestManager {
    private static RequestManager instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private RequestManager(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();

    }

    static synchronized RequestManager getInstance(Context context) {
        if (instance == null) {
            instance = new RequestManager(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
