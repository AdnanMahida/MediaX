package com.ad.mediax.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.ad.mediax.Adapters.MusicAdapter;
import com.ad.mediax.Model.Music;
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

public class MusicFragment extends Fragment {
    ProgressBar progressBar;
    List<Music> musicList;
    MusicAdapter adapter;
    ListView musicListView;
    int image;
    View Rootview;
    private static final String URL_SELECT_MUSIC_DATA = "http://mediax-ad.000webhostapp.com/mediax/selectmusic.php";

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Rootview = inflater.inflate(R.layout.fragment_music, container, false);
        try {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Music");

            musicListView = (ListView) Rootview.findViewById(R.id.Listview_music);
            progressBar = Rootview.findViewById(R.id.progressbar);
//        progressBar.getProgressDrawable().setColorFilter(
//                Color.rgb(231, 39, 111), android.graphics.PorterDuff.Mode.SRC_IN);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL_SELECT_MUSIC_DATA, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                    musicList = new ArrayList<>();
                    if (getActivity() != null) {
                        adapter = new MusicAdapter(getContext(), R.layout.music_listview, musicList);
                    }
                    for (int i = 0; i < response.length(); i++) {
                        try {

                            JSONObject jsonObject = response.getJSONObject(i);

                            musicList.add(new Music(jsonObject.getString("music_imageurl").toString(), jsonObject.getString("music_title").toString(), jsonObject.getString("music_subtitle").toString(), jsonObject.getString("music_musicurl").toString())); //adding value


                            Log.d("myjosn", jsonObject.getString("music_title"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    musicListView.setAdapter(adapter);
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
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        return Rootview;


    }
}