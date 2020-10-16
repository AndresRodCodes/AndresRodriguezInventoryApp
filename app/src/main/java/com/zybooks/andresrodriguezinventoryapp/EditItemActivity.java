package com.zybooks.andresrodriguezinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    private EditText editTextItemName;
    private EditText editTextItemCount;

    long itemId;
    String itemName;
    int itemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);

        editTextItemName = findViewById(R.id.editTextItemNameEA);
        editTextItemCount = findViewById(R.id.editTextItemCountEA);

        itemId = getIntent().getLongExtra("EXTRA_ITEM_ID", -1);
        itemName = getIntent().getStringExtra("EXTRA_ITEM_NAME");
        itemCount = getIntent().getIntExtra("EXTRA_ITEM_COUNT", -1);

        editTextItemName.setText(itemName);
        editTextItemCount.setText(Integer.toString(itemCount));

    }

    public void onTapEditItemButton(View view) {
        System.out.println("Test edit button");
        System.out.println("Item id: " + itemId);
        System.out.println("Item Name: " + itemName);
        System.out.println("Item Count: " + itemCount);

        String itemNameUpdated = editTextItemName.getText().toString();
        int itemCountUpdated = Integer.parseInt(editTextItemCount.getText().toString());

        ItemDatabase itemDatabase = new ItemDatabase(this);
        boolean itemUpdated = itemDatabase.updateItem(itemId, itemNameUpdated, itemCountUpdated);
        System.out.println("Item Updated: " + itemUpdated);
    }
}