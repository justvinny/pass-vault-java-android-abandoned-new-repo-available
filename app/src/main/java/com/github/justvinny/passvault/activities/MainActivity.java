package com.github.justvinny.passvault.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.github.justvinny.passvault.R;
import com.github.justvinny.passvault.files.ReadText;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_RESULT = 0;
    private static final String TAG = "MainActivity";

    private EditText editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String passwordEntered = editTextPassword.getText().toString();

                if (ReadText.readText(getApplicationContext(), passwordEntered)) {
                    launchMainMenu();
                } else {
                    loginFailedMessage();
                }
            }
        });
    }

    private void launchMainMenu() {
        Intent loginIntent = new Intent(MainActivity.this, MainMenu.class);
        startActivityForResult(loginIntent, MENU_RESULT);
    }

    private void loginFailedMessage() {
        Toast.makeText(getApplicationContext(), "Wrong password", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MENU_RESULT) {
            if (resultCode == MainMenu.QUIT) {
                finishAndRemoveTask();
                System.exit(0);
            }
        }
    }
}