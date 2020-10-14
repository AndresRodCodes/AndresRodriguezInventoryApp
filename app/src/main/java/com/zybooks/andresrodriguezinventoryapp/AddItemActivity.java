package com.zybooks.andresrodriguezinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddItemActivity extends AppCompatActivity {

    private EditText editTextItemName;
    private EditText editTextItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        editTextItemName = findViewById(R.id.editTextItemName);
        editTextItemCount = findViewById(R.id.editTextItemCount);
    }

    public void onTapAddItemButton(View view) {
        // Get item name and count text
        String userItemName = editTextItemName.getText().toString();
        String userItemCount = editTextItemCount.getText().toString();

        // Verify item name and count are not empty
        if (userItemName.isEmpty() || userItemCount.isEmpty()) {
            // Item name or count is empty
            Toast.makeText(getApplicationContext(), "Enter an item name and count", Toast.LENGTH_LONG).show();
        } else {
            // Item name and count is provided
            // Convert userItemCount to integer
            int userItemCountInt = Integer.parseInt(userItemCount);
            // Initialize Item Database
            ItemDatabase itemDatabase = new ItemDatabase(this);
            long itemAddedId = itemDatabase.addItem(userItemName, userItemCountInt);

            if (itemAddedId == -1) {
                // Item not added
                Toast.makeText(getApplicationContext(), "Item not added", Toast.LENGTH_SHORT).show();

            } else {
                // Item Added
                Toast.makeText(getApplicationContext(), "Item Added", Toast.LENGTH_SHORT).show();
                editTextItemName.setText("");
                editTextItemCount.setText("");
            }
        }
    }
}