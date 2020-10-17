package com.zybooks.andresrodriguezinventoryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NotificationPermissionActivity extends AppCompatActivity {
    private final int SMS_PERMISSION_CODE = 1;

    TextView textViewPermissionStatus;
    EditText editTextPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_permission);

        textViewPermissionStatus = findViewById(R.id.textViewPermissionStatus);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);

        // Check if permission has bee granted to display permission status when Activity is started
        if ( ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {
            textViewPermissionStatus.setText("Permission Status: GRANTED");
        } else {
            textViewPermissionStatus.setText("Permission Status: DENIED");
        }
    }

    // Show permission has been granted or request it if it hasn't
    public void onTapRequestPermission(View view) {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
            textViewPermissionStatus.setText("Permission Status: GRANTED");
            sendSMSText();
        } else {
            requestSendSMSPermission();
        }
    }

    // Ran if user has denied the permission once. Give description of why it is being requested
    private void requestSendSMSPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
            // Show custom alert dialog if user has denied it once
            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is need to send SMS text when inventory is low.")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Request SMS permission again
                            ActivityCompat.requestPermissions(NotificationPermissionActivity.this, new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        } else {
            // Request the permission again
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
        }
    }

    // Check if the permission was provided by the user
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == SMS_PERMISSION_CODE) {
            // Check that there are permission results and that the first one has been granted (SEND_SMS)
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();
                sendSMSText();
                textViewPermissionStatus.setText("Permission Status: GRANTED");
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Send a default message to the number provided by the user.
    public void sendSMSText() {
        String phoneNumber = editTextPhoneNumber.getText().toString();
        // Check if a phone number is provided
        if (phoneNumber.length() >= 10) {
            String messagePermissionGranted =
                    "Permission for SMS notifications with this inventory app has been granted.";
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, messagePermissionGranted, null, null);
        }
    }
}