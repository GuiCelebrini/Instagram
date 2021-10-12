package com.android.guicelebrini.instagram.model;

import com.google.firebase.firestore.FieldValue;

public class Post {

    private String user;
    private String imageUrl;
    public FieldValue createdAt;

    public Post(){

    }

    public Post(String user, String imageUrl) {
        this.user = user;
        this.imageUrl = imageUrl;
        this.createdAt = FieldValue.serverTimestamp();
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

    public FieldValue getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(FieldValue createdAt) {
        this.createdAt = createdAt;
    }
}
