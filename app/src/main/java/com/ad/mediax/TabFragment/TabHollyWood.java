package com.ad.mediax.TabFragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ad.mediax.Adapters.TabMovieAdapter;
import com.ad.mediax.Model.Movie;
import com.ad.mediax.R;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TabHollyWood extends Fragment {
    @Nullable
    View rootView;
    private RecyclerView movieRecyclerView;
    private TabMovieAdapter tabMovieAdapter;
    private ProgressBar progressBar;
    private List<Movie> movieList;
    private static final String MOVIE_URL = "http://mediax-ad.000webhostapp.com/mediax/select-movie-hollywood.php";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.tab_fragment_hollywood, container, false);

        try {
            init();
            initMovie();
        }catch (Exception e)
        {
            Log.d("",e.getMessage());
        }
        return rootView;
    }
    private void initMovie() {
        movieList = new ArrayList<>();

        //add movies
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(MOVIE_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (getActivity() != null) {
                    tabMovieAdapter = new TabMovieAdapter(getContext(), movieList);
                }
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);

                        movieList.add(new com.ad.mediax.Model.Movie(jsonObject.getString("movie_title").toString(),
                                jsonObject.getString("movie_description").toString(),
                                jsonObject.getString("movie_imgurl").toString(),
                                jsonObject.getString("movie_starcast").toString(),jsonObject.getString("movie_url").toString(),
                                jsonObject.getString("isyoutube").toString()));


                        Log.d("myjosn", jsonObject.getString("isyoutube"));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                movieRecyclerView.setAdapter(tabMovieAdapter);
                movieRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
        requestQueue.addRequestFinishedListener(new RequestQueue.RequestFinishedListener<Object>() {

            @Override
            public void onRequestFinished(Request<Object> request) {
                if (progressBar != null)
                    progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }
    private void init() {
        movieRecyclerView = rootView.findViewById(R.id.tabm_recyclerview_id);
        progressBar = rootView.findViewById(R.id.tabm_progressbar_id);
    }
}
