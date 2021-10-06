package com.android.guicelebrini.instagram.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseConfig {

    private static FirebaseAuth auth;
    private static FirebaseFirestore firestore;
    private static StorageReference storage;

    public static FirebaseAuth getFirebaseAuthInstance(){
        if (auth == null) {
            auth = FirebaseAuth.getInstance();
        }

        return auth;
    }

    public static FirebaseFirestore getFirestoreInstance(){
        if (firestore == null) {
            firestore = FirebaseFirestore.getInstance();
        }

        return firestore;
    }

    public static StorageReference getStorageInstance(){
        if (storage == null){
            storage = FirebaseStorage.getInstance().getReference();
        }

        return storage;
    }

}
