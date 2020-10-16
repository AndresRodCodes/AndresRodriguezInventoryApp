package com.zybooks.andresrodriguezinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
    }

    public void tapLogin(View view) {
        System.out.println("Login button has been tapped!");

        // Get username and password from EditTexts
        String username = editTextUsername.getText().toString();
        String userPassword = editTextPassword.getText().toString();

        System.out.println("Username: " + username + "\nPassword: " + userPassword);

        UserDatabase userDatabase = new UserDatabase(this);

        System.out.println("User exists: " + userDatabase.isExistingUser(username, userPassword));
        if (userDatabase.isExistingUser(username, userPassword)) {
            // User exists in the database
            // Switch to ItemGridActivity
            Intent goToItemGridActivty = new Intent(getApplicationContext(), ItemGridActivity.class);
            startActivity(goToItemGridActivty);
        } else {
            // User does not exist in the database
            System.out.println("No account found. Please check username and password");
            // Give user feedback
            Toast invalidAccountToast = Toast.makeText(getApplicationContext(), "Account not found", Toast.LENGTH_LONG);
            invalidAccountToast.show();
        }
    }

    public void tapCreateAccount(View view) {
        System.out.println("Create Account button has been tapped!");

        // Get username and password from EditTexts
        String username = editTextUsername.getText().toString();
        String userPassword = editTextPassword.getText().toString();

        System.out.println("Username: " + username + "\nPassword: " + userPassword);

        // Initialize SQLite user database.
        UserDatabase userDatabase = new UserDatabase(this);
        long userAddedId = userDatabase.addUser(username, userPassword);

        if (userAddedId == -1) {
            System.out.println("User not added");
            // Give user feedback
            Toast accountNotCreatedToast = Toast.makeText(getApplicationContext(), "Account not created", Toast.LENGTH_SHORT);
            accountNotCreatedToast.show();
        } else {
            System.out.println("User Added!\n" + "Id: " + userAddedId + "\n" + "Username: " + username);
            // Give user feedback
            Toast accountCreatedToast = Toast.makeText(getApplicationContext(), "Account created!", Toast.LENGTH_SHORT);
            accountCreatedToast.show();
            // Switch to ItemGridActivity
            Intent goToItemGridActivity = new Intent(getApplicationContext(), ItemGridActivity.class);
            startActivity(goToItemGridActivity);
        }
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