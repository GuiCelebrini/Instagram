package com.android.guicelebrini.instagram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.config.FirebaseConfig;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonLogin;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewsById();

        auth = FirebaseConfig.getFirebaseAuthInstance();
        verifyLoggedUser();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    private void verifyLoggedUser(){
        if (auth.getCurrentUser() != null){
            goToMainActivity();
        }
    }

    private void login(){
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getApplicationContext(), "Usuário logado com sucesso", Toast.LENGTH_SHORT).show();
                        goToMainActivity();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(getApplicationContext(), "Erro ao logar usuário", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void findViewsById(){
        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
    }

    private void goToMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void goToRegisterActivity(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}