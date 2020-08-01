package com.github.justvinny.passvault.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.justvinny.passvault.R;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "SHARED_PREFS";
    private static final String TAG = "MainActivity";

    private ConstraintLayout mainLayout;
    private TextView textViewFirstRun;
    private EditText editTextPassword;
    private Button buttonLogin;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewFirstRun = (TextView) findViewById(R.id.textViewFirstRunMsg);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.button_login);
        sharedPreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if (checkIfFirstRun()) {
            firstRunMessage();
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = editTextPassword.getText().toString();

                if (checkIfFirstRun()) {
                    if (!password.isEmpty()) {
                        passwordSuccessfullyStored(password);
                    } else {
                        passwordEmptyMessage();
                    }
                }

                if (!password.isEmpty()) {
                    if (passwordEnteredMatched()) {
                        launchMainMenu();
                    } else {
                        passwordIncorrectMessage();
                    }
                } else {
                    passwordEmptyMessage();
                }
            }
        });
    }

    private boolean checkIfFirstRun() {
        return sharedPreferences.getBoolean("first_run", true);
    }

    private void firstRunMessage() {
        String message = "First login detected.\nEnter password to be stored and press login.";
        textViewFirstRun.setText(message);
        textViewFirstRun.setVisibility(View.VISIBLE);
    }

    private void passwordSuccessfullyStored(String password) {
        sharedPreferencesEditor.putBoolean("first_run", false);
        sharedPreferencesEditor.putString("password", password);
        sharedPreferencesEditor.apply();
    }

    private boolean passwordEnteredMatched() {
        String passwordEntered = editTextPassword.getText().toString();
        String passwordStored = sharedPreferences.getString("password", "");

        return passwordEntered.equals(passwordStored);
    }

    private void launchMainMenu() {
        Intent loginIntent = new Intent(MainActivity.this, MainMenu.class);
        loginIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(loginIntent);
        MainActivity.this.finish();
    }

    private void passwordIncorrectMessage() {
        Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
    }

    private void passwordEmptyMessage() {
        Toast.makeText(getApplicationContext(), "Password can't be empty!", Toast.LENGTH_LONG)
                .show();
    }
}