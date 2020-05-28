package com.example.buygo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class BuyOrGoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_or_go);
    }

    public void buyClicked (View view ){

        Intent intent = new Intent(this, FeedBuyActivity.class);
        startActivity(intent);
        finish();

    }

    public void goClicked (View view) {

    }
}
