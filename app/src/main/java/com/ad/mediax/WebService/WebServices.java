package com.ad.mediax.WebService;

import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WebServices {
    private static final String URL_SELECT_MOVIE_URL_SLIDER = "http://mediax-ad.000webhostapp.com/mediax/selectmovieslider.php";
    public JsonArrayRequest jsonArrayRequest;
    public JSONObject jsonObject;

    public JSONObject getSliderMovie() {
        jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_URL_SLIDER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {

                        jsonObject = response.getJSONObject(i);

                        Log.d("myjosn", jsonObject.getString("music_title"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
//        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
//        requestQueue.add(jsonArrayRequest);
        return jsonObject;
    }
//    webServices =new WebServices();
//        RequestQueue rueue = Volley.newRequestQueue(this.getContext());
//        requestQueue.add(webServices.jsonArrayRequest);
//        JSONObject myobject=webServices.getSliderMovie();
//        try { String a= myobject.getString("movie_title").toString();
//            Log.d("moviedd",a);} catch (JSONException e) {e.printStackTrace(); }

}
