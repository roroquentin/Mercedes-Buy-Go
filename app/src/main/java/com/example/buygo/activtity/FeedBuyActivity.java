package com.example.buygo.activtity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.buygo.DresFragment;
import com.example.buygo.HomPageFragment;
import com.example.buygo.HomeThingsFragment;
import com.example.buygo.MyAdsFragment;
import com.example.buygo.NotificationFragment;
import com.example.buygo.ProfileFragment;
import com.example.buygo.R;
import com.example.buygo.UploadFragment;
import com.example.buygo.adapter.BaseItemRecylerViewAdapter;
import com.example.buygo.databinding.ActivityFeedBuyBinding;
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

public class FeedBuyActivity extends AppCompatActivity {
    private ActivityFeedBuyBinding binding;
    private ArrayList<BaseItemModel> baseItemModelArrayList;
    private BaseItemRecylerViewAdapter baseItemRecylerViewAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFeedBuyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        carBtnClicked();
        firebaseAuth = FirebaseAuth.getInstance();
        listenSearchBar();


    }

    private void listenSearchBar() {
        binding.searchSrcText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                getDataFromFirebase(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                baseItemRecylerViewAdapter.getFilter().filter(s);
                return false;
            }
        });
    }

    public void homePageBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomPageFragment homPageFragment = new HomPageFragment();
         fragmentTransaction.replace(R.id.rcv_base, homPageFragment).commit();

    }

    public void notificationBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NotificationFragment notificationFragment = new NotificationFragment();
         fragmentTransaction.replace(R.id.rcv_base, notificationFragment).commit();

    }

    public void uploadBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        UploadFragment uploadFragment = new UploadFragment();
          fragmentTransaction.replace(R.id.rcv_base, uploadFragment).commit();

    }

    public void adsBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyAdsFragment myAdsFragment = new MyAdsFragment();
         fragmentTransaction.replace(R.id.rcv_base, myAdsFragment).commit();

    }

    public void profileBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProfileFragment profileFragment = new ProfileFragment();
          fragmentTransaction.replace(R.id.rcv_base, profileFragment).commit();

    }

    public void carBtnClicked() {

        baseItemModelArrayList = new ArrayList<>();
        firebaseFirestore = FirebaseFirestore.getInstance();
        getDataCarFromFirebase();
        binding.rcvBase.setLayoutManager(new LinearLayoutManager(this));
        baseItemRecylerViewAdapter = new BaseItemRecylerViewAdapter(baseItemModelArrayList);
        binding.rcvBase.setAdapter(baseItemRecylerViewAdapter);
    }

    public void getDataCarFromFirebase() {

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name", "Araba").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {

                    Toast.makeText(FeedBuyActivity.this, e.getLocalizedMessage().toString(),
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
                        baseItemModelArrayList.add(baseItemModel);
                        baseItemRecylerViewAdapter.notifyDataSetChanged();

                    }
                }

            }
        });

    }

    public void getDataFromFirebase(String searchText) {
        baseItemRecylerViewAdapter.getFilterList().clear();
        baseItemRecylerViewAdapter.getBaseItemModelArrayList().clear();
        baseItemRecylerViewAdapter.notifyDataSetChanged();

        CollectionReference collectionReference = firebaseFirestore.collection("Posts");

        collectionReference.orderBy("date", Query.Direction.DESCENDING).whereEqualTo("name", searchText).addSnapshotListener((queryDocumentSnapshots, e) -> {

            if (e != null) {

                Toast.makeText(FeedBuyActivity.this, e.getLocalizedMessage().toString(),
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
                    baseItemModelArrayList.add(baseItemModel);
                    baseItemRecylerViewAdapter.notifyDataSetChanged();

                }
            }

        });

    }

    public void babyBtnClicked(View view) {


    }

    public void dresBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DresFragment profileFragment = new DresFragment();
        // fragmentTransaction.replace(R.id.frame_layout, profileFragment).commit();

    }

    public void homeKatBtnClicked(View view) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeThingsFragment profileFragment = new HomeThingsFragment();
        // fragmentTransaction.replace(R.id.frame_layout, profileFragment).commit();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.signOut) {

            firebaseAuth.signOut();

            Intent intent = new Intent(FeedBuyActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }


}
