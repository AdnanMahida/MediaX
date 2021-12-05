package com.ad.mediax.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ad.mediax.Activities.MovieDetailsActivity;
import com.ad.mediax.Model.Movie;
import com.ad.mediax.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TabMovieAdapter extends RecyclerView.Adapter<TabMovieAdapter.MyViewHolder> {
    Context mContext;
    List<Movie> mData;

    public TabMovieAdapter(Context mContext, List<Movie> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tab_cardview_items, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mTitle.setText(mData.get(position).getTitle());
        Picasso.get().load(mData.get(position).getThumbnailUrl()).into(holder.mImg);
        holder.mImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("movie_title", mData.get(position).getTitle());
                intent.putExtra("movie_description", mData.get(position).getDescription());
                intent.putExtra("movie_starcast", mData.get(position).getStarCast());
                intent.putExtra("movie_url", mData.get(position).getMovieurl());
                intent.putExtra("movie_imgurl", mData.get(position).getThumbnailUrl());
                intent.putExtra("isYoutube", mData.get(position).getIsYoutube());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private ImageView mImg;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTitle = itemView.findViewById(R.id.movie_title_id);
            mImg = itemView.findViewById(R.id.movie_img_id);
        }
    }
}
