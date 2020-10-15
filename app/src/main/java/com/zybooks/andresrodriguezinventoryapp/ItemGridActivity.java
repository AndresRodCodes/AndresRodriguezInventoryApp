package com.zybooks.andresrodriguezinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

public class ItemGridActivity extends AppCompatActivity {

    GridView gridViewItems;

    String[] numberWord = {"One", "Two", "Three", "Four", "Five", "Six"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_grid);

        gridViewItems = findViewById(R.id.gridViewItems);
        ItemAdapter itemAdapter = new ItemAdapter(this, numberWord);
        gridViewItems.setAdapter(itemAdapter);

        ItemDatabase itemDatabase = new ItemDatabase(this);
        itemDatabase.getAllItems();
    }

    public void onTapAddItemFAB(View view) {
        // Go to Add Item Activity
        Intent goToAddItemActivty = new Intent(getApplicationContext(), AddItemActivity.class);
        startActivity(goToAddItemActivty);
    }
}