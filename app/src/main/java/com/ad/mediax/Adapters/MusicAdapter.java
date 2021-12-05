package com.ad.mediax.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ad.mediax.Model.Music;
import com.ad.mediax.Activities.MusicPlayerActivity;
import com.ad.mediax.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MusicAdapter extends ArrayAdapter<Music> {
    Context context;
    int resource;
    List<Music> musicList;

    public MusicAdapter(Context context, int resource, List<Music> musicList) {
        super(context, resource, musicList);
        this.context = context;
        this.resource = resource;
        this.musicList = musicList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(resource, null);
        TextView title = view.findViewById(R.id.music_title);
        TextView subTitle = view.findViewById(R.id.music_subtitle);
        ImageView imageView = view.findViewById(R.id.musicVideoImage);

        final Music music = musicList.get(position);
        title.setText(music.getTitle());
        subTitle.setText(music.getSubtitle());
        //imageView.setImageResource(music.getImage());
        Picasso.get().load(music.getImageUrl()).into(imageView);
       // imageView.setImageResource(music.getImage());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MusicPlayerActivity.class);
                intent.putExtra("url", music.getUrl());
                context.startActivity(intent);
            }
        });
        return view;

    }
}
