package com.example.buygo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.buygo.activtity.FeedBuyActivity;
import com.example.buygo.adapter.BaseItemRecylerViewAdapter;
import com.example.buygo.models.BaseItemModel;
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
    private ArrayList<BaseItemModel> baseItemModelArrayList;
    private BaseItemRecylerViewAdapter baseItemRecylerViewAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_hompage, container, false);

        baseItemModelArrayList = new ArrayList<>();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataFromFireStore();
        RecyclerView recyclerView = viewGroup.findViewById(R.id.recyclerView);
        searchView = viewGroup.findViewById(R.id.searchView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        baseItemRecylerViewAdapter = new BaseItemRecylerViewAdapter(baseItemModelArrayList);
        recyclerView.setAdapter(baseItemRecylerViewAdapter);
        return viewGroup;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listenSearchBar();
    }

    public void getDataFromFireStore() {
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");
        collectionReference.orderBy("date", Query.Direction.DESCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        baseItemRecylerViewAdapter.addItem(baseItemModel);


                    }
                }

            }
        });

    }

    private void listenSearchBar() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                //  getDataFromFirebase(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                baseItemRecylerViewAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    public void carClicked(View view) {
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name", "Araba").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Toast.makeText(requireContext(), e.getLocalizedMessage().toString(),
                            Toast.LENGTH_LONG).show();

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
                        baseItemRecylerViewAdapter.addItem(baseItemModel);

                    }
                }

            }
        });
    }
    public void babyClicked(View view) {
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name",
                "Bebek").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Toast.makeText(requireContext(), e.getLocalizedMessage().toString(),
                            Toast.LENGTH_LONG).show();

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
                        baseItemRecylerViewAdapter.addItem(baseItemModel);

                    }
                }

            }
        });
    }
    public void dersClicked(View view) {
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name",
                "Giyim").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Toast.makeText(requireContext(), e.getLocalizedMessage().toString(),
                            Toast.LENGTH_LONG).show();

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
                        baseItemRecylerViewAdapter.addItem(baseItemModel);

                    }
                }

            }
        });
    }
    public void homeClicked(View view){
        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name",
                "Ev Aletleri").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Toast.makeText(requireContext(), e.getLocalizedMessage().toString(),
                            Toast.LENGTH_LONG).show();

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
                        baseItemRecylerViewAdapter.addItem(baseItemModel);

                    }
                }

            }
        });
    }
}
