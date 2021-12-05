package com.ad.mediax.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ad.mediax.Model.Music;
import com.ad.mediax.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MusicPlayerActivity extends YouTubeBaseActivity {
    YouTubePlayerView playerView;
    FloatingActionButton play;
    ProgressBar proGress;
    String videourl;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_music_player);
        play =findViewById(R.id.btnplay);
        proGress =findViewById(R.id.progress_circular);


        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        try {
            Intent intent = getIntent();
            videourl = intent.getExtras().getString("url");
            playerView = findViewById(R.id.youtubeview);

            onInitializedListener = new YouTubePlayer.OnInitializedListener() {
                @SuppressLint("RestrictedApi")
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                    youTubePlayer.loadVideo(videourl); //for sigle video play
                    play.setVisibility(View.INVISIBLE);
                    proGress.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            };
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    playerView.initialize("AIzaSyARzSE30OxPSnr-dUX3UX60Yn2sGQYySx4", onInitializedListener);
                    proGress.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            Log.d("", "");
        }

    }
}
