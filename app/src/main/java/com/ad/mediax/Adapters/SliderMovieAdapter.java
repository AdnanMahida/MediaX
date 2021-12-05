package com.ad.mediax.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.ad.mediax.Activities.MovieDetailsActivity;
import com.ad.mediax.Activities.MoviePlayerActivity;
import com.ad.mediax.Model.SliderMovie;
import com.ad.mediax.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SliderMovieAdapter extends PagerAdapter {
    private Context mContext;
    private List<SliderMovie> mList;

    public SliderMovieAdapter(Context mContext, List<SliderMovie> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout = inflater.inflate(R.layout.slider_item, null);
        ImageView sliderimg = sliderLayout.findViewById(R.id.slider_img);
        FloatingActionButton floatingbtnslider = sliderLayout.findViewById(R.id.floatingbtn_slider);

        Picasso.get().load(mList.get(position).getImageurl()).into(sliderimg);
        container.addView(sliderLayout);

        floatingbtnslider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("movie_title", mList.get(position).getTitle());
                intent.putExtra("movie_description", mList.get(position).getDescription());
                intent.putExtra("movie_starcast", mList.get(position).getStarCast());
                intent.putExtra("movie_url", mList.get(position).getMovieurl());
                intent.putExtra("movie_imgurl", mList.get(position).getImageurl());
                intent.putExtra("isYoutube", "0");
                mContext.startActivity(intent);
            }
        });

        sliderimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, MovieDetailsActivity.class);
                intent.putExtra("movie_title", mList.get(position).getTitle());
                intent.putExtra("movie_description", mList.get(position).getDescription());
                intent.putExtra("movie_starcast", mList.get(position).getStarCast());
                intent.putExtra("movie_url", mList.get(position).getMovieurl());
                intent.putExtra("movie_imgurl", mList.get(position).getImageurl());
                mContext.startActivity(intent);
            }
        });

        return sliderLayout;


    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
