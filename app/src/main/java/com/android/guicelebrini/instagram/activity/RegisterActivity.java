package com.android.guicelebrini.instagram.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.config.FirebaseConfig;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    private EditText editUsername;
    private EditText editEmail;
    private EditText editPassword;
    private Button buttonRegister;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViewsById();

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    private void createUser(){
        auth = FirebaseConfig.getFirebaseAuthInstance();
        db = FirebaseConfig.getFirestoreInstance();

        String username = editUsername.getText().toString();
        String email = editEmail.getText().toString();
        String password = editEmail.getText().toString();




    }

    private void findViewsById(){
        editUsername = findViewById(R.id.editRegisterName);
        editEmail = findViewById(R.id.editRegisterEmail);
        editPassword = findViewById(R.id.editRegisterPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
    }
}