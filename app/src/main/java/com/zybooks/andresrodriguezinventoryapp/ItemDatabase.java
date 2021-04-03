package com.zybooks.andresrodriguezinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ItemDatabase extends SQLiteOpenHelper {

    private final String TAG = "ItemDatabase";
    private static final String DATABASE_NAME = "items.db";
    private static final int VERSION = 1;

    public ItemDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // Construct the UserTable with columns
    private static final class ItemTable {
        private static final String TABLE = "items";
        private static final String COL_ID = "_id";
        private static final String COL_ITEM_NAME = "item_name";
        private static final String COL_ITEM_COUNT = "item_count";
    }
    // Create the ItemTable
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + ItemDatabase.ItemTable.TABLE + " (" +
                ItemDatabase.ItemTable.COL_ID + " integer primary key autoincrement, " +
                ItemDatabase.ItemTable.COL_ITEM_NAME + " text, " +
                ItemDatabase.ItemTable.COL_ITEM_COUNT + " integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + ItemDatabase.ItemTable.TABLE);
        onCreate(db);
    }

    // Adds item to the item database
    public long addItem(String itemName, int itemCount) {
        // Get the item table from database
        SQLiteDatabase dbItems = getWritableDatabase();

        // Place item details into a ContentValues object
        ContentValues values = new ContentValues();
        values.put(ItemTable.COL_ITEM_NAME, itemName);
        values.put(ItemTable.COL_ITEM_COUNT, itemCount);

        // Insert the item into the item table
        long itemId = dbItems.insert(ItemDatabase.ItemTable.TABLE, null, values);
        return itemId;
    }

    // Get all items in the item table
    public ArrayList<Item> getAllItems() {
        // ArrayList to hold all items
        ArrayList<Item> itemsArray = new ArrayList<>();
        SQLiteDatabase dbItems = getReadableDatabase();

        // Select all items in the ItemDatabase table
        String sql = "Select * from " + ItemDatabase.ItemTable.TABLE;
        Cursor cursor = dbItems.rawQuery(sql, new String[] {});
        // Place items in Item Database Table into the itemsArray
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String dbItemName = cursor.getString(1);
                int dbCount = cursor.getInt(2);
                Log.d(TAG, "Item ID: " + id + ", Item Name: " + dbItemName + ", Item count: " + dbCount);

                // Add items to Array List of type Item
                Item item = new Item();
                item.itemId = id;
                item.itemName = dbItemName;
                item.itemCount = dbCount;

                itemsArray.add(item);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemsArray;
    }

    // Get low inventory Items from item table
    public ArrayList<Item> getLowInventoryItems(){
        // ArrayList to hold low inventory items
        ArrayList<Item> lowInventoryItems = new ArrayList<>();
        SQLiteDatabase dbItems = getReadableDatabase();

        // Select all items in item database table where item count is less than or equal to 5
        String sql = "Select * from " + ItemDatabase.ItemTable.TABLE + " where " + ItemTable.COL_ITEM_COUNT + " <= 5";
        Cursor cursor = dbItems.rawQuery(sql, new String[] {});

        // Place items from ItemDatabase table into the loInventoryItems ArrayList
        if (cursor.moveToFirst()){
            do {
                long id = cursor.getLong(0);
                String dbItemName = cursor.getString(1);
                int dbCount = cursor.getInt(2);

                // Add items to ArrayList of Type Item
                Item item = new Item();
                item.itemId = id;
                item.itemName = dbItemName;
                item.itemCount = dbCount;

                lowInventoryItems.add(item);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return lowInventoryItems;
    }

    // Delete an item by its item ID
    public boolean deleteItem(long itemId) {
        SQLiteDatabase db = getWritableDatabase();
        int rowsDeleted = db.delete(ItemTable.TABLE, ItemTable.COL_ID + " = ?",
                new String[] { Long.toString(itemId) });
        return rowsDeleted > 0;
    }

    // Update an item
    public boolean updateItem (long itemId, String itemName, int itemCount) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemTable.COL_ITEM_NAME, itemName);
        values.put(ItemTable.COL_ITEM_COUNT, itemCount);

        int itemsUpdated = db.update(ItemTable.TABLE, values, "_id = ?", new String[]{ Float.toString(itemId) });
        return itemsUpdated > 0;
    }
}
