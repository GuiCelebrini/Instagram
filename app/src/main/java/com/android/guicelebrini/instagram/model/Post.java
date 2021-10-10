package com.android.guicelebrini.instagram.model;

public class Post {

    private String user;
    private String image;

    public Post(){

    }

    public Post(String user, String image) {
        this.user = user;
        this.image = image;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
