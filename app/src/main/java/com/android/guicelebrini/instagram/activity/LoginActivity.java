package com.android.guicelebrini.instagram.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.config.FirebaseConfig;
import com.android.guicelebrini.instagram.helper.Base64Custom;
import com.android.guicelebrini.instagram.helper.Preferences;
import com.android.guicelebrini.instagram.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPassword;
    private Button buttonLogin;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViewsById();

        auth = FirebaseConfig.getFirebaseAuthInstance();

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        verifyLoggedUser();
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
                        saveInPreferences(email);
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

    private void saveInPreferences(String email){
        db = FirebaseFirestore.getInstance();

        String loggedUserId = Base64Custom.encode(email);
        Preferences preferences = new Preferences(getApplicationContext());

        db.collection("users").document(loggedUserId).get()
                .addOnSuccessListener(documentSnapshot -> {
                   User user = documentSnapshot.toObject(User.class);

                   preferences.saveData(loggedUserId, user.getName());
                })
                .addOnFailureListener(e -> {
                    Log.i("Resultado", "Your informations couldn't be saved in preferences");
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
    }
}