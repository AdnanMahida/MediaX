package com.ad.mediax.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.ad.mediax.Adapters.MovieAdapter;
import com.ad.mediax.Adapters.MusicAdapter;
import com.ad.mediax.Adapters.SliderMovieAdapter;
import com.ad.mediax.Model.Movie;
import com.ad.mediax.Model.Music;
import com.ad.mediax.Model.SliderMovie;
import com.ad.mediax.R;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private SliderMovieAdapter sliderMovieAdapter;
    private View Rootview;
    private ViewPager viewPager;
    private List<SliderMovie> movieList;
    private TabLayout indicator;
    private static final String URL_SELECT_MOVIE_URL_SLIDER = "http://mediax-ad.000webhostapp.com/mediax/selectmovieslider.php";
    private static final String URL_SELECT_MOVIE_ACTION = "https://mediax-ad.000webhostapp.com/mediax/select-movie-action.php";
    private static final String URL_SELECT_MOVIE_HORROR = "https://mediax-ad.000webhostapp.com/mediax/select-movie-horror.php";
    private static final String URL_SELECT_MOVIE_COMEDY = "https://mediax-ad.000webhostapp.com/mediax/select-movie-comedy.php";
    private static final String URL_SELECT_MOVIE_ROMANCE = "https://mediax-ad.000webhostapp.com/mediax/select-movie-romance.php";
    private static final String URL_SELECT_MOVIE_THRILLER = "https://mediax-ad.000webhostapp.com/mediax/select-movie-thriller.php";

    //for horizontal recycle view declare all here
    private List<Movie> horizontalActionList1, horizontalActionList2, horizontalActionList3, horizontalActionList4, horizontalActionList5;
    private RecyclerView movieRecyclerView1, movieRecyclerView2, movieRecyclerView3, movieRecyclerView4, movieRecyclerView5;
    private MovieAdapter movieAdapter1, movieAdapter2, movieAdapter3, movieAdapter4, movieAdapter5;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Rootview = inflater.inflate(R.layout.fragment_home, container, false);

        try{
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Home");
        init();
        initActionHoriRecycle();
        initComedyHoriRecycle();
        initHorrorHoriRecycle();
        initRomanceHoriRecycle();
        initThirillerHoriRecycle();
        initSlider();}
        catch (Exception e)
        {
            Log.d("",e.getMessage());
        }
        return Rootview;
    }

    private void initSlider() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_URL_SLIDER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                movieList = new ArrayList<>();
                if (getActivity() != null) {
                    sliderMovieAdapter = new SliderMovieAdapter(getContext(), movieList);
                }
                for (int i = 0; i < response.length(); i++) {
                    try {

                        JSONObject jsonObject = response.getJSONObject(i);
                        movieList.add(new SliderMovie(jsonObject.getString("movie_title"), jsonObject.getString("movie_description"), jsonObject.getString("movie_starcast"), jsonObject.getString("movieurl"), jsonObject.getString("imageurl")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                viewPager.setAdapter(sliderMovieAdapter);
                Timer timer = new Timer();
                timer.scheduleAtFixedRate(new HomeFragment.SliderTimer(), 1000, 2000);
                indicator.setupWithViewPager(viewPager, true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void initActionHoriRecycle() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_ACTION, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                horizontalActionList2 = new ArrayList<>();
                if (getActivity() != null) {
                    movieAdapter2 = new MovieAdapter(getContext(), horizontalActionList2);
                }
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        horizontalActionList2.add(new Movie(jsonObject.getString("movie_title"), jsonObject.getString("movie_description"), jsonObject.getString("movie_imgurl"), jsonObject.getString("movie_starcast"), jsonObject.getString("movie_url"), jsonObject.getString("isyoutube")));
                        Log.d("myjosn", jsonObject.getString("music_title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                movieRecyclerView2.setAdapter(movieAdapter2);
                movieRecyclerView2.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void initComedyHoriRecycle() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_COMEDY, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                horizontalActionList3 = new ArrayList<>();
                if (getActivity() != null) {
                    movieAdapter3 = new MovieAdapter(getContext(), horizontalActionList3);
                }
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        horizontalActionList3.add(new Movie(jsonObject.getString("movie_title"), jsonObject.getString("movie_description"), jsonObject.getString("movie_imgurl"), jsonObject.getString("movie_starcast"), jsonObject.getString("movie_url"), jsonObject.getString("isyoutube")));
                        Log.d("myjosn", jsonObject.getString("music_title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                movieRecyclerView3.setAdapter(movieAdapter3);
                movieRecyclerView3.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void initHorrorHoriRecycle() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_HORROR, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                horizontalActionList1 = new ArrayList<>();
                if (getActivity() != null) {
                    movieAdapter1 = new MovieAdapter(getContext(), horizontalActionList1);
                }
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        horizontalActionList1.add(new Movie(jsonObject.getString("movie_title"), jsonObject.getString("movie_description"), jsonObject.getString("movie_imgurl"), jsonObject.getString("movie_starcast"), jsonObject.getString("movie_url"), jsonObject.getString("isyoutube")));
                        Log.d("myjosn", jsonObject.getString("music_title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                movieRecyclerView1.setAdapter(movieAdapter1);
                movieRecyclerView1.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void initRomanceHoriRecycle() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_ROMANCE, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                horizontalActionList4 = new ArrayList<>();
                if (getActivity() != null) {
                    movieAdapter4 = new MovieAdapter(getContext(), horizontalActionList4);
                }
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        horizontalActionList4.add(new Movie(jsonObject.getString("movie_title"), jsonObject.getString("movie_description"), jsonObject.getString("movie_imgurl"), jsonObject.getString("movie_starcast"), jsonObject.getString("movie_url"), jsonObject.getString("isyoutube")));
                        Log.d("myjosn", jsonObject.getString("music_title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                movieRecyclerView4.setAdapter(movieAdapter4);
                movieRecyclerView4.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);
    }

    private void initThirillerHoriRecycle() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MOVIE_THRILLER, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                horizontalActionList5 = new ArrayList<>();
                if (getActivity() != null) {
                    movieAdapter5 = new MovieAdapter(getContext(), horizontalActionList5);
                }
                for (int i = 0; i < 6; i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        horizontalActionList5.add(new Movie(jsonObject.getString("movie_title"), jsonObject.getString("movie_description"), jsonObject.getString("movie_imgurl"), jsonObject.getString("movie_starcast"), jsonObject.getString("movie_url"), jsonObject.getString("isyoutube")));
                        Log.d("myjosn", jsonObject.getString("music_title"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                movieRecyclerView5.setAdapter(movieAdapter5);
                movieRecyclerView5.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());
        requestQueue.add(jsonArrayRequest);

    }


    private void init() {
        viewPager = Rootview.findViewById(R.id.view_slider);
        indicator = Rootview.findViewById(R.id.slider_indicator);
        movieRecyclerView1 = Rootview.findViewById(R.id.h_horiz_recycle1);
        movieRecyclerView2 = Rootview.findViewById(R.id.h_horiz_recycle2);
        movieRecyclerView3 = Rootview.findViewById(R.id.h_horiz_recycle3);
        movieRecyclerView4 = Rootview.findViewById(R.id.h_horiz_recycle4);
        movieRecyclerView5 = Rootview.findViewById(R.id.h_horiz_recycle5);
    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (viewPager.getCurrentItem() < movieList.size() - 1) {
                            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                        } else
                            viewPager.setCurrentItem(0);
                    }
                });
            }
        }
    }
}
