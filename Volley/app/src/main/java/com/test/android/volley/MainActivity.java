package com.test.android.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mRequestQueue;
    private Button button;
    private TextView textView;
    private String baseURL = "https://jsonplaceholder.typicode.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setObjects();
    }

    private void setObjects() {
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //volleyStringRequestExample();
                //volleyCacheStringRequestExample();
                //volleyArrayRequestExample();
                ///volleyObjectRequestExample();
                volleyPostRequestExample();
            }
        });
    }

    private void volleyStringRequestExample() {
        String url = baseURL + "/posts";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    // Display the first 500 characters of the response string.
                    Log.d("TAG", "Response is: " + response);
                }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG","That didn't work!");
            }
        });
        stringRequest.setShouldCache(false);
        stringRequest.setTag("MyRequest");
        VolleyRequest.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void volleyCacheStringRequestExample() {
        String url = baseURL + "/posts/1";
        CacheStringRequest stringRequest = new CacheStringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Log.d("TAG", "Response is: " + response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("TAG","That didn't work!");
            }
        });
        stringRequest.setTag("MyRequest");
        VolleyRequest.getInstance(this.getApplicationContext()).addToRequestQueue(stringRequest);
    }

    private void volleyArrayRequestExample() {
        String url = baseURL + "/posts";
        JsonArrayRequest jArrayRequest = new JsonArrayRequest(Request.Method.GET, url,null ,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject post = response.getJSONObject(i);
                                String postId = post.getString("id");
                                String postTitle = post.getString("title");
                                String postBody = post.getString("body");
                                if (post.has("user")) {
                                    String userId = post.getString("user");
                                }
                                //textView.setText(postTitle);
                                Log.d("TITLE", postTitle);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                });
        jArrayRequest.setTag("MyRequest");
        VolleyRequest.getInstance(this.getApplicationContext()).addToRequestQueue(jArrayRequest);
    }

    private void volleyObjectRequestExample() {
        String url = baseURL + "/posts/1";
        JsonObjectRequest jObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String postTitle = response.getString("title");
                            textView.setText(postTitle);
                            Log.d("TITLE", postTitle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
        jObjectRequest.setTag("MyRequest");
        VolleyRequest.getInstance(this.getApplicationContext()).addToRequestQueue(jObjectRequest);
    }

    public void volleyPostRequestExample(){
        String url = baseURL + "/posts";
        JSONObject jsonRequest = new JSONObject();
        try {
            jsonRequest.put("title", "mytitle");
            jsonRequest.put("body", "mybody");
            jsonRequest.put("userId", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("OBJECT", jsonRequest.toString());
        JsonObjectRequest jObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonRequest,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String postId = response.getString("id");
                            String postTitle = response.getString("title");
                            Log.d("POSTID",postId);
                            textView.setText(postTitle);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("TAG",error.toString());
                    }
                }){
        }; ;
        jObjectRequest.setTag("MyRequest");
        VolleyRequest.getInstance(this.getApplicationContext()).addToRequestQueue(jObjectRequest);
    }

    @Override
    protected void onStop () {
        super.onStop();
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll("MyRequest");
        }
    }
}
