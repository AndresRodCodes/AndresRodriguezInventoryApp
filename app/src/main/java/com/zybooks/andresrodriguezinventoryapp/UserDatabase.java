package com.zybooks.andresrodriguezinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class UserDatabase extends SQLiteOpenHelper {

    private final String TAG = "UserDatabase";
    private static final String DATABASE_NAME = "users.db";
    private static final int VERSION = 1;

    public UserDatabase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    // Construct the UserTable
    private static final class UserTable {
        private static final String TABLE = "users";
        private static final String COL_ID = "_id";
        private static final String COL_USERNAME = "username";
        private static final String COL_PASSWORD = "password";
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserTable.TABLE + " (" +
                UserTable.COL_ID + " integer primary key autoincrement, " +
                UserTable.COL_USERNAME + " text, " +
                UserTable.COL_PASSWORD + " text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UserTable.TABLE);
        onCreate(db);
    }

    public long addUser(String username, String password) {
        boolean userExists = checkForExistingUsername(username);

        // Check if user exists in the database
        if (userExists) {
            System.out.println("User already exists");
            return -1;
        } else {
            // Validate username and password
            if (username.length() < 3 || password.length() < 3) {
                System.out.println("username and password must be greater than 3 in length");
                return -1;
            } else {
                // Add user to the database
                SQLiteDatabase db = getWritableDatabase();

                ContentValues values = new ContentValues();
                values.put(UserTable.COL_USERNAME, username);
                values.put(UserTable.COL_PASSWORD, password);

                long userId = db.insert(UserTable.TABLE, null, values);
                return userId;
            }
        }
    }

    public boolean checkForExistingUsername(String username) {
        boolean userExists = false;

        SQLiteDatabase db = getReadableDatabase();

        String sql = "Select * from " + UserTable.TABLE + " where username = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { username });
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String dbUsername = cursor.getString(1);
                String dbPassword = cursor.getString(2);
                Log.d(TAG, "User = " + id + ", " + dbUsername + ", " + dbPassword);
                userExists = true;
            } while (cursor.moveToNext());
        }
        cursor.close();
        return userExists;
    }

    public boolean isExistingUser (String username, String password) {
        boolean userExists = false;

        SQLiteDatabase db = getReadableDatabase();

        String sql = "Select * from " + UserTable.TABLE + " where username = ? AND " +
                "password = ?";
        Cursor cursor = db.rawQuery(sql, new String[] { username, password });
        if (cursor.moveToFirst()) {
            do {
                long id = cursor.getLong(0);
                String dbUsername = cursor.getString(1);
                String dbPassword = cursor.getString(2);
                Log.d(TAG, "User = " + id + ", " + dbUsername + ", " + dbPassword);
                userExists = true;
            } while (cursor.moveToNext());
        }
        cursor.close();

        return userExists;
    }
}
