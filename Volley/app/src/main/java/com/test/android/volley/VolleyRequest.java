package com.test.android.volley;

import android.app.DownloadManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by Android on 6/01/2018.
 */

public class VolleyRequest {
    private static VolleyRequest instance;
    private RequestQueue requestQueue;
    private static Context mContext;
    private ImageLoader mImageLoader;

    private VolleyRequest(Context context) {
        Log.d("TAG","Creating singleton");
        mContext = context;
        requestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(requestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public static synchronized VolleyRequest getInstance(Context context) {
        if (instance == null) {
            instance = new VolleyRequest(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if(requestQueue == null) {
            requestQueue = Volley.newRequestQueue(mContext);
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}
