package com.zybooks.andresrodriguezinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.TextView;

import java.lang.reflect.Array;
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

    @Override
    protected void onResume() {
        super.onResume();
        updateGridView();
    }

    // Updates the grid with the passed in array list
    public void updateGrid(ArrayList<Item> arrayListItems) {
        // Get grid view by id
        gridViewItems = findViewById(R.id.gridViewItems);
        // Create new item adapter with an array list passed in
        ItemAdapter itemAdapter = new ItemAdapter(this, R.id.gridViewItemLayout, arrayListItems);
        gridViewItems.setAdapter(itemAdapter);
    }

    public void onTapLowInventoryCheckBox(View checkBoxView) {
        // Is the checkBox checked?
        boolean checked = ((CheckBox) checkBoxView).isChecked();
        if (checked){
            ItemDatabase itemDatabase = new ItemDatabase(this);
            ArrayList<Item> arrayLowInventoryItems;

            // Get Low Inventory items
            arrayLowInventoryItems = itemDatabase.getLowInventoryItems();

            // Update item grid
            updateGrid(arrayLowInventoryItems);
        } else {
            updateGridView();
        }
    }

    public void onTapAddItemFAB(View view) {
        // Go to Add Item Activity
        Intent goToAddItemActivity = new Intent(getApplicationContext(), AddItemActivity.class);
        startActivity(goToAddItemActivity);
    }

    public void updateGridView() {
        ItemDatabase itemDatabase = new ItemDatabase(this);
        arrayItem = itemDatabase.getAllItems();

        // Remove no items text
        if (arrayItem.size() > 0) {
            textViewNoItems = findViewById(R.id.textViewNoItems);
            textViewNoItems.setVisibility(View.INVISIBLE);
        }
        // Update items in grid view
        updateGrid(arrayItem);
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