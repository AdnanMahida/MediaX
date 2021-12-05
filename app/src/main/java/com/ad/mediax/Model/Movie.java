package com.ad.mediax.Model;

public class Movie {
    private  String Title;
    private  String Description;
    private  String ThumbnailUrl;
    private  String StarCast;
    private  String Movieurl;
    private  String isYoutube;

    public Movie(String title, String description, String thumbnailUrl, String starCast, String movieurl) {
        Title = title;
        Description = description;
        ThumbnailUrl = thumbnailUrl;
        StarCast = starCast;
        Movieurl = movieurl;
    }

    public Movie(String title, String description, String thumbnailUrl, String starCast, String movieurl, String isYoutube) {
        Title = title;
        Description = description;
        ThumbnailUrl = thumbnailUrl;
        StarCast = starCast;
        Movieurl = movieurl;
        this.isYoutube = isYoutube;
    }

    public String getIsYoutube() {
        return isYoutube;
    }

    public void setIsYoutube(String isYoutube) {
        this.isYoutube = isYoutube;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getThumbnailUrl() {
        return ThumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        ThumbnailUrl = thumbnailUrl;
    }

    public String getStarCast() {
        return StarCast;
    }

    public void setStarCast(String starCast) {
        StarCast = starCast;
    }

    public String getMovieurl() {
        return Movieurl;
    }

    public void setMovieurl(String movieurl) {
        Movieurl = movieurl;
    }
}
