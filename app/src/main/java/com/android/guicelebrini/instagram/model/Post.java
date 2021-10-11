package com.android.guicelebrini.instagram.model;

public class Post {

    private String user;
    private String imageUrl;

    public Post(){

    }

    public Post(String user, String imageUrl) {
        this.user = user;
        this.imageUrl = imageUrl;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
