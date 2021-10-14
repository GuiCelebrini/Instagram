package com.android.guicelebrini.instagram.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.android.guicelebrini.instagram.R;

public class FeedActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        findViewsById();
        configureToolbar();
    }

    private void findViewsById(){
        toolbar = findViewById(R.id.toolbar_feed);
    }

    private void configureToolbar(){
        toolbar.setLogo(R.drawable.logo_toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
    }
}