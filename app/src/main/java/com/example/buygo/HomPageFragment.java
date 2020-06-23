package com.example.buygo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buygo.adapter.FeedRecycleAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class HomPageFragment extends Fragment {


    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    ArrayList<String> userMailFromDB;
    ArrayList<String> produktNameFromDB;
    ArrayList<String> produktPriceFromDB;
    ArrayList<String> produktStiationFromDB;
    ArrayList<String> produktInformationFromDB;
    ArrayList<String> produktImageFromDB;
    FeedRecycleAdapter feedRecycleAdapter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_hompage,container,false);

            userMailFromDB = new ArrayList<>();
            produktNameFromDB = new ArrayList<>();
            produktPriceFromDB = new ArrayList<>();
            produktStiationFromDB = new ArrayList<>();
            produktInformationFromDB = new ArrayList<>();
            produktImageFromDB = new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFromFireStore();

        RecyclerView recyclerView = viewGroup.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        feedRecycleAdapter = new FeedRecycleAdapter(userMailFromDB,produktNameFromDB,
                produktPriceFromDB,produktStiationFromDB,produktInformationFromDB,produktImageFromDB);

        recyclerView.setAdapter(feedRecycleAdapter);
        return viewGroup;
    }

    public void getDataFromFireStore(){

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null){

                    Toast.makeText(getContext(),e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();

                }

                if (queryDocumentSnapshots != null){

                     for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()){

                         Map<String, Object> data = snapshot.getData();

                         String userMail = (String) data.get("usermail");
                         String name = (String) data.get("name");
                         String price = (String) data.get("price");
                         String stiation = (String) data.get("stiation");
                         String information = (String) data.get("information");
                         String imageUrl = (String) data.get("downloadurl");

                         userMailFromDB.add(userMail);
                         produktNameFromDB.add(name);
                         produktPriceFromDB.add(price);
                         produktStiationFromDB.add(stiation);
                         produktInformationFromDB.add(information);
                         produktImageFromDB.add(imageUrl);


                         feedRecycleAdapter.notifyDataSetChanged();




                     }
                }

            }
        });

    }
}
