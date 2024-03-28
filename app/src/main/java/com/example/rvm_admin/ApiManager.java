package com.example.rvm_admin;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ApiManager {

    // Define an interface for callback
    public interface ApiListener {
        void onSuccess(JSONArray response);
        void onError(VolleyError error);
    }

    // Method to fetch data from API
    public static void fetchData(RequestQueue requestQueue, final ApiListener listener) {
        String url = "http://production.msmfclasses.com:8080/hostel/allLogins";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Pass the response to the onSuccess callback
                        listener.onSuccess(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Pass the error to the onError callback
                        listener.onError(error);
                    }
                });

        // Add the request to the request queue
        requestQueue.add(jsonArrayRequest);
    }
}
