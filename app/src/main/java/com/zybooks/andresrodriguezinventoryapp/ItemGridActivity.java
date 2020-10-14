package com.zybooks.andresrodriguezinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ItemGridActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_grid);
    }

    public void onTapAddItemFAB(View view) {
        // Go to Add Item Activity
        Intent goToAddItemActivty = new Intent(getApplicationContext(), AddItemActivity.class);
        startActivity(goToAddItemActivty);
    }
}