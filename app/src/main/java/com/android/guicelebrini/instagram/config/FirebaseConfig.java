package com.android.guicelebrini.instagram.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirebaseConfig {

    private static FirebaseAuth auth;
    private static FirebaseFirestore firestore;

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

}
