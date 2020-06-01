package com.example.buygo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;

public class FeedBuyActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.signOut){

            firebaseAuth.signOut();

            Intent intent = new Intent(FeedBuyActivity.this, SignUpActivity.class);
            startActivity(intent);
            finish();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_buy);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    public void homePageBtnClicked (View view ){

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomPageFragment homPageFragment = new HomPageFragment();
        fragmentTransaction.replace(R.id.frame_layout,homPageFragment).commit();

    }
    public void notificationBtnClicked (View view ){

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        NotificationFragment notificationFragment = new NotificationFragment();
        fragmentTransaction.replace(R.id.frame_layout,notificationFragment).commit();

    }
    public void uploadBtnClicked (View view ){

        setContentView(R.layout.activity_feed_buy2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        UploadFragment uploadFragment = new UploadFragment();
        fragmentTransaction.replace(R.id.frame_layout,uploadFragment).commit();

    }
    public void adsBtnClicked (View view ){

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        MyAdsFragment myAdsFragment = new MyAdsFragment();
        fragmentTransaction.replace(R.id.frame_layout,myAdsFragment).commit();

    }
    public void profileBtnClicked (View view ){

        setContentView(R.layout.activity_feed_buy2);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        ProfileFragment profileFragment = new ProfileFragment();
        fragmentTransaction.replace(R.id.frame_layout,profileFragment).commit();

    }

    public void carBtnClicked (View view){

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        CarFragment profileFragment = new CarFragment();
        fragmentTransaction.replace(R.id.frame_layout,profileFragment).commit();


    }

    public void babyBtnClicked (View view) {

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        BabyFragment profileFragment = new BabyFragment();
        fragmentTransaction.replace(R.id.frame_layout,profileFragment).commit();

    }

    public void dresBtnClicked (View view) {

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        DresFragment profileFragment = new DresFragment();
        fragmentTransaction.replace(R.id.frame_layout,profileFragment).commit();

    }

    public void homeKatBtnClicked (View view) {

        setContentView(R.layout.activity_feed_buy);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        HomeThingsFragment profileFragment = new HomeThingsFragment();
        fragmentTransaction.replace(R.id.frame_layout,profileFragment).commit();

    }




}
