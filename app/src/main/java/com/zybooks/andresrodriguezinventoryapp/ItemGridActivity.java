package com.zybooks.andresrodriguezinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class ItemGridActivity extends AppCompatActivity {

    GridView gridViewItems;
    ArrayList<Item> arrayItem = new ArrayList<>();
    TextView textViewNoItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_grid);

        updateGridView();
    }

    public void onTapAddItemFAB(View view) {
        // Go to Add Item Activity
        Intent goToAddItemActivty = new Intent(getApplicationContext(), AddItemActivity.class);
        startActivity(goToAddItemActivty);
    }

    public void updateGridView() {
        ItemDatabase itemDatabase = new ItemDatabase(this);
        arrayItem = itemDatabase.getAllItems();

        System.out.println(arrayItem.size());
        // Remove no items text
        if (arrayItem.size() > 0) {
            textViewNoItems = findViewById(R.id.textViewNoItems);
            textViewNoItems.setVisibility(View.INVISIBLE);
        }

        gridViewItems = findViewById(R.id.gridViewItems);
        ItemAdapter itemAdapter = new ItemAdapter(this, R.id.gridViewItemLayout, arrayItem);
        gridViewItems.setAdapter(itemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Handle item selection
        switch (item.getItemId()){
            case R.id.permission:
                Intent goToPermissionActivity = new Intent(this, NotificationPermissionActivity.class);
                startActivity(goToPermissionActivity);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}