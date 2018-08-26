package com.firebase.galateros.drawables;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import java.util.HashMap;
import java.util.Map;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentChange.Type;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.MetadataChanges;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.ServerTimestamp;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.Transaction;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class UserProfile extends Activity {
    public DocumentReference charRef = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        String key =  ("5d6MJ8LcJNKXS5VCbn5w");// ACTUAL KEY

        charRef = db.collection("users").document(key);

        charRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override

            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        System.out.println("DocumentSnapshot data: " + document.getData());
                        //t.setText("DocumentSnapshot data: " + document.getData());
                        final String first = document.getString("first");
                        final String last = document.getString("last");
                        final String allergenic = document.getString("allergenic");
                        final long put = document.getLong("put");
                        final String blood = document.getString("blood");

                        System.out.println(allergenic);


                        TextView firstTextView, lastTextView, putTextView,bloodTextView, allergenicTextView;

                         firstTextView = (TextView) findViewById(R.id.firstName);
                        lastTextView = (TextView) findViewById(R.id.lastName);
                        putTextView = (TextView) findViewById(R.id.dateBirth);
                        bloodTextView = (TextView) findViewById(R.id.bloodType);
                        allergenicTextView = (TextView) findViewById(R.id.Allergies);
                        firstTextView.setText(first);
                        lastTextView.setText(last);
                        putTextView.setText(put+"");
                        bloodTextView.setText(blood);
                        allergenicTextView.setText(allergenic);



                    } else {
                        System.out.println( "No such document");
                    }
                } else {
                    System.out.println("get failed with "+ task.getException());
                }
            }
        });

    }
}
