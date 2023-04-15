package com.example.android.doctorishere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

//import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<request> requestArrayList;
    requestAdapter RequestAdapter;
    FirebaseFirestore db;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data... ");
        progressDialog.show();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        db = FirebaseFirestore.getInstance();
        requestArrayList = new ArrayList<>();

        RequestAdapter = new requestAdapter(MainActivity.this, requestArrayList);
        recyclerView.setAdapter(RequestAdapter);
        
        OnPatientRequested();

    }

    private void OnPatientRequested() {
        db.collection("Requests")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot value, FirebaseFirestoreException error) {
                        if(error != null){
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }

                            Log.e("Firestore error",error.getMessage());
                            return;
                        }

                        for(DocumentChange dc :  value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                requestArrayList.add(dc.getDocument().toObject(request.class));
                            }

                            RequestAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }

                        }

                    }
                });
    }
}