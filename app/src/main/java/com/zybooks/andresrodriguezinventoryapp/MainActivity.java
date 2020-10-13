package com.zybooks.andresrodriguezinventoryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
            Intent goToItemGridActivty = new Intent(getApplicationContext(), ItemGridActivity.class);
            startActivity(goToItemGridActivty);
        } else {
            System.out.println("No account found. Please check username and password");
        }
    }

    public void tapCreateAccount(View view) {
        System.out.println("Create Account button has been tapped!");

        // Get username and password from EditTexts
        String username = editTextUsername.getText().toString();
        String userPassword = editTextPassword.getText().toString();

        System.out.println("Username: " + username + "\nPassword: " + userPassword);

        UserDatabase userDatabase = new UserDatabase(this);
        long userAddedId = userDatabase.addUser(username, userPassword);

        if (userAddedId == -1) {
            System.out.println("User not added");
        } else {
            System.out.println("User Added!\n" + "Id: " + userAddedId + "\n" + "Username: " + username);
            Intent goToItemGridActivty = new Intent(getApplicationContext(), ItemGridActivity.class);
            startActivity(goToItemGridActivty);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
}