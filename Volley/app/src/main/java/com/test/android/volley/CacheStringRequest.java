package com.test.android.volley;

import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Android on 6/01/2018.
 */

public class CacheStringRequest extends StringRequest {
    public CacheStringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(method, url, listener, errorListener);
        Log.d("CACHE", "Creating cache request");
    }

    @Override
    public void deliverError(VolleyError error) {
        Log.d("CACHE","Deliver error called");
        if (error instanceof NoConnectionError) {
            Cache.Entry entry = this.getCacheEntry();
            if(entry != null) {
                Log.d("CACHE", "Loading from cache");
                Response<String> response = parseNetworkResponse(new NetworkResponse(entry.data, entry.responseHeaders));
                deliverResponse(response.result);
                return;
            }
        }
        super.deliverError(error);
    }
}