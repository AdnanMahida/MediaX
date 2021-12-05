package com.ad.mediax.Model;



public class Music {
    //int image;
    String Title,subtitle,url,imageurl;

    public Music(String imageurl, String title, String subtitle,String url) {
        this.imageurl = imageurl;
        Title = title;
        this.subtitle = subtitle;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageurl;
    }

    public String getTitle() {
        return Title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setImageUrl(String image) {
        this.imageurl = image;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
