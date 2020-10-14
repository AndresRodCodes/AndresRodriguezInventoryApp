package com.zybooks.andresrodriguezinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        String userItemName = editTextItemName.getText().toString();
        String userItemCount = editTextItemCount.getText().toString();
        // Convert userItemCount to integer
        int userItemCountInt = Integer.parseInt(userItemCount);

        System.out.println("Item name: " + userItemName + ", Item count: " + userItemCountInt);

    }
}