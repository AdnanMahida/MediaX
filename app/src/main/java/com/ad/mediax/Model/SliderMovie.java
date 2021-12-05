package com.ad.mediax.Model;

public class SliderMovie {
    private String Title;
    private String Description;
    private String Movieurl;
    private  String StarCast;
    private String Imageurl;

    public SliderMovie(String title, String description, String starCast,String movieurl, String imageurl) {
        Title = title;
        Description = description;
        StarCast = starCast;
        Movieurl = movieurl;
        Imageurl = imageurl;

    }

    public String getStarCast() {
        return StarCast;
    }

    public void setStarCast(String starCast) {
        StarCast = starCast;
    }

    public String getTitle() {
        return Title;
    }

    public String getDescription() {
        return Description;
    }

    public String getMovieurl() {
        return Movieurl;
    }

    public String getImageurl() {
        return Imageurl;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setMovieurl(String movieurl) {
        Movieurl = movieurl;
    }

    public void setImageurl(String imageurl) {
        Imageurl = imageurl;
    }
}
