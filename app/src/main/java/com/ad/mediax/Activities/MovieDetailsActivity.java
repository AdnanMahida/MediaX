package com.ad.mediax.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ad.mediax.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MovieDetailsActivity extends AppCompatActivity {
    private TextView title, description, starcast;
    private ImageView movieImage;
    private String s_title, s_description, s_starcast, s_movieimageurl, s_movieurl;
    private FloatingActionButton floatingActionButton;
    private String isYoutube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        getSupportActionBar().setTitle("Movie Details");
        init();
        fill();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (isYoutube.equals("1")) {
                        Intent intent = new Intent(MovieDetailsActivity.this, MusicPlayerActivity.class);
                        intent.putExtra("url", s_movieurl);
                        startActivity(intent);
                    } else if (isYoutube.equals("0")) {
                        Intent intent = new Intent(MovieDetailsActivity.this, MoviePlayerActivity.class);
                        intent.putExtra("movie_url", s_movieurl);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(MovieDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void fill() {
        try {
            Intent intent = getIntent();
            s_title = intent.getExtras().getString("movie_title");
            s_description = intent.getExtras().getString("movie_description");
            s_starcast = intent.getExtras().getString("movie_starcast");
            s_movieurl = intent.getExtras().getString("movie_url");
            s_movieimageurl = intent.getExtras().getString("movie_imgurl");

            Picasso.get().load(s_movieimageurl).into(movieImage);
            title.setText(s_title);
            description.setText(s_description);
            starcast.setText(s_starcast);
            isYoutube = intent.getExtras().getString("isYoutube");
            Log.d("isYoutube", isYoutube);
        } catch (Exception e) {
            Log.d("", e.getMessage());
        }
    }

    private void init() {
        movieImage = findViewById(R.id.d_movie_img);
        title = findViewById(R.id.d_txt_title);
        description = findViewById(R.id.d_txt_story);
        starcast = findViewById(R.id.d_txt_stars);
        floatingActionButton = findViewById(R.id.d_floatingActionButton);

    }
}
