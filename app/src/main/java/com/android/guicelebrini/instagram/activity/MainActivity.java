package com.android.guicelebrini.instagram.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.config.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Button buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewsById();

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth auth = FirebaseConfig.getFirebaseAuthInstance();
                auth.signOut();
                goToLoginActivity();
                finish();
            }
        });

    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
    }

    private void findViewsById(){
        buttonLogout = findViewById(R.id.buttonLogout);
    }
}