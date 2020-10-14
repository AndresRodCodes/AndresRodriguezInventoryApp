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

    public long addItem(String itemName, int itemCount) {
        // Add item to the database
        SQLiteDatabase dbItems = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ItemTable.COL_ITEM_NAME, itemName);
        values.put(ItemTable.COL_ITEM_COUNT, itemCount);

        long itemId = dbItems.insert(ItemDatabase.ItemTable.TABLE, null, values);
        return itemId;
    }

    public ArrayList<Item> getAllItems() {
        ArrayList<Item> itemsArray = new ArrayList<>();
        SQLiteDatabase dbItems = getReadableDatabase();

        String sql = "Select * from " + ItemDatabase.ItemTable.TABLE;
        Cursor cursor = dbItems.rawQuery(sql, new String[] {});
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String dbItemName = cursor.getString(1);
                int dbCount = cursor.getInt(2);
                Log.d(TAG, "Item ID: " + id + ", Item Name: " + dbItemName + ", Item count: " + dbCount);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return itemsArray;
    }
}
