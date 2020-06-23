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

import com.example.buygo.adapter.BaseItemRecylerViewAdapter;
import com.example.buygo.models.BaseItemModel;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class CarFragment extends Fragment {

    ArrayList<BaseItemModel> baseItemModelArrayList;
    BaseItemRecylerViewAdapter baseItemRecylerViewAdapter;
    private FirebaseFirestore firebaseFirestore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_car, container, false);
        baseItemModelArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataCarFromFirebase();

        RecyclerView recyclerView = viewGroup.findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        baseItemRecylerViewAdapter = new BaseItemRecylerViewAdapter(baseItemModelArrayList);

        recyclerView.setAdapter(baseItemRecylerViewAdapter);

        return viewGroup;
    }

    public void getDataCarFromFirebase() {

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name", "Araba").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Toast.makeText(getContext(), e.getLocalizedMessage().toString(), Toast.LENGTH_LONG).show();

                }

                if (queryDocumentSnapshots != null) {

                    for (DocumentSnapshot snapshot : queryDocumentSnapshots.getDocuments()) {

                        Map<String, Object> data = snapshot.getData();

                        String userMail = (String) data.get("usermail");
                        String name = (String) data.get("name");
                        String price = (String) data.get("price");
                        String stiation = (String) data.get("stiation");
                        String information = (String) data.get("information");
                        String imageUrl = (String) data.get("downloadurl");
                        BaseItemModel baseItemModel = new BaseItemModel();
                        baseItemModel.setProduktImage(imageUrl);
                        baseItemModel.setProduktInformation(information);
                        baseItemModel.setProduktName(name);
                        baseItemModel.setProduktPrice(price);
                        baseItemModel.setProduktStiation(stiation);
                        baseItemModel.setUserMail(userMail);
                        baseItemModelArrayList.add(baseItemModel);
                        baseItemRecylerViewAdapter.notifyDataSetChanged();

                    }
                }

            }
        });

    }
}
