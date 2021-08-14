package com.android.guicelebrini.instagram.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.guicelebrini.instagram.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}