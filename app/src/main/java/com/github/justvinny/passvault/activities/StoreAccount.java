package com.github.justvinny.passvault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.justvinny.passvault.R;
import com.github.justvinny.passvault.domain.PlatformAccount;
import com.github.justvinny.passvault.files.TextFileUtility;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class StoreAccount extends AppCompatActivity {

    private static final String TAG = "StoreAccount";

    private ArrayList<PlatformAccount> platformAccountArrayList;

    private EditText editTextPlatform, editTextUsername, editTextPassword;
    private Button buttonRegister;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_account);

        platformAccountArrayList = TextFileUtility.load(getApplicationContext());

        editTextPlatform = (EditText) findViewById(R.id.editText_platform_name);
        editTextUsername = (EditText) findViewById(R.id.editText_username);
        editTextPassword = (EditText) findViewById(R.id.editText_password);
        buttonRegister = (Button) findViewById(R.id.button_register);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storePlatformAccount();

                Log.d(TAG, "onClick: Register button clicked!");
            }
        });
    }

    private void storePlatformAccount() {
        String platformName = editTextPlatform.getText().toString();
        String platformUsername = editTextUsername.getText().toString();
        String platformPassword = editTextPassword.getText().toString();

        if (checkNotEmpty(platformName, platformUsername, platformPassword)) {
            platformAccountArrayList.add(
                    new PlatformAccount(platformName, platformUsername, platformPassword));

            TextFileUtility.save(getApplicationContext(), platformAccountArrayList);
            clearTextFields();
            showRegistrationSuccessMessage();
        } else {
            showRegistrationFailedMessage();
        }
    }

    private boolean checkNotEmpty(String platformName, String platformUsername, String platformPassword) {
        return !(platformName.isEmpty() && platformUsername.isEmpty() && platformPassword.isEmpty());
    }

    private void showRegistrationSuccessMessage() {
        Toast.makeText(getApplicationContext(), "Account successfully registered!",
                Toast.LENGTH_LONG).show();
    }

    private void showRegistrationFailedMessage() {
        Toast.makeText(getApplicationContext(), "Fields can't be blank!",
                Toast.LENGTH_LONG).show();
    }

    private void clearTextFields() {
        editTextPlatform.setText("");
        editTextUsername.setText("");
        editTextPassword.setText("");
    }
}