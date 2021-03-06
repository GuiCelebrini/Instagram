package com.android.guicelebrini.instagram.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.guicelebrini.instagram.R;
import com.android.guicelebrini.instagram.config.FirebaseConfig;
import com.android.guicelebrini.instagram.helper.Preferences;
import com.android.guicelebrini.instagram.model.Post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarMain;
    private BottomNavigationView bottomNavigation;

    private static final int SHARE_PHOTO_REQUEST_CODE = 1;
    private StorageReference storage;
    private FirebaseFirestore db;

    private Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        storage = FirebaseConfig.getStorageInstance();
        db = FirebaseFirestore.getInstance();
        preferences = new Preferences(getApplicationContext());

        findViewsById();
        configureToolbar();
        configureBottomNavigation();
        
    }

    private void findViewsById(){
        toolbarMain = findViewById(R.id.toolbar_main);
        bottomNavigation = findViewById(R.id.bottomNavigationMain);
    }

    private void configureToolbar(){
        setSupportActionBar(toolbarMain);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbarMain.setLogo(R.drawable.logo_toolbar);
    }

    private void configureBottomNavigation(){
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerMain);

        NavController navController = navHostFragment.getNavController();

        NavigationUI.setupWithNavController(bottomNavigation, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.action_add_photo:
                sharePhoto();
                return true;
            case R.id.action_logout:
                logout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void sharePhoto() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SHARE_PHOTO_REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SHARE_PHOTO_REQUEST_CODE && resultCode == RESULT_OK && data != null){

            String loggedUserId = preferences.getUserId();
            String loggedUserName = preferences.getUserName();

            Uri imageAdress = data.getData();

            Post post = new Post(loggedUserName, "");
            db.collection("users").document(loggedUserId).collection("posts").add(post)
                    .addOnSuccessListener(documentReference -> {
                        String postId = documentReference.getId();
                        saveImageInStorage(postId, imageAdress);
                    });

        }
    }

    private void saveImageInStorage(String postId, Uri imageAdress){
        StorageReference reference = storage.child("images/" + postId + "/1");
        reference.putFile(imageAdress)
                .addOnSuccessListener(taskSnapshot -> {
                    getDownloadUrl(reference, downloadUrl -> {
                        addImageUrlInFirestore(postId ,downloadUrl);
                    });
                });
    }

    private interface GetDownloadUrlCallback{
        void complete(Uri downloadUrl);
    }

    private void getDownloadUrl(StorageReference reference, GetDownloadUrlCallback downloadUrlCallback){
        reference.getDownloadUrl().addOnSuccessListener(uri -> {
            downloadUrlCallback.complete(uri);
        });
    }

    private void addImageUrlInFirestore(String postId, Uri downloadUrl) {
        String imageDownloadUrl = downloadUrl.toString();
        String loggedUserId = preferences.getUserId();

        db.collection("users").document(loggedUserId).collection("posts").document(postId).update("imageUrl", imageDownloadUrl)
                .addOnSuccessListener(unused -> {
                    Toast.makeText(getApplicationContext(), "Seu post foi salvo com sucesso", Toast.LENGTH_SHORT).show();
                });

    }

    private void logout(){
        FirebaseAuth auth = FirebaseConfig.getFirebaseAuthInstance();
        auth.signOut();
        goToLoginActivity();
    }

    private void goToLoginActivity(){
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }
}