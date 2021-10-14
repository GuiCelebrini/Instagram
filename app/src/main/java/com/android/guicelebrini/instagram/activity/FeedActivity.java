package com.android.guicelebrini.instagram.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.fragment.HomeFragment;

public class FeedActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private String userFirestoreId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        findViewsById();
        configureToolbar();

        userFirestoreId = getIntent().getExtras().getString("firestoreId");

        setPostsList(userFirestoreId);
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

    private void setPostsList(String userFirestoreId) {
        HomeFragment fragment = new HomeFragment(userFirestoreId);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_posts_fragment, fragment);
        transaction.commit();
    }

}