package com.example.whatsapp.config;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConfig {
    private static DatabaseReference database;
    private static FirebaseAuth auth;

    //return firebaseDB instance
    public static DatabaseReference getFirebaseDatabase(){
        if(database == null){
            database  = FirebaseDatabase.getInstance().getReference();
        }
        return database;
    }

    //return firebaseAuth instance
    public static FirebaseAuth getFirebaseAuthentication(){
        if(auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
}
