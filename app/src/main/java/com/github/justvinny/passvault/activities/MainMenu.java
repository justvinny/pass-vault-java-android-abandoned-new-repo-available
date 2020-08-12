package com.github.justvinny.passvault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.justvinny.passvault.R;

public class MainMenu extends AppCompatActivity {

    public static final int QUIT = 1;
    private static final String TAG = "MainMenu";
    private Button buttonStoreAccount, buttonViewAccounts, buttonDeleteAccount, buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonStoreAccount = (Button) findViewById(R.id.button_store_new_account);
        buttonStoreAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchStoreAccountActivity();
            }
        });

        buttonViewAccounts = (Button) findViewById(R.id.button_get_password);
        buttonViewAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchShowAccountsActivity();
            }
        });

        buttonQuit = (Button) findViewById(R.id.button_quit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitIntent = new Intent();
                setResult(QUIT, quitIntent);
                finishAndRemoveTask();
                System.exit(0);
            }
        });
    }

    private void launchStoreAccountActivity() {
        Intent intent = new Intent(getApplicationContext(), StoreAccount.class);
        startActivity(intent);
    }

    private void launchShowAccountsActivity() {
        Intent intent = new Intent(getApplicationContext(), ShowStoredAccounts.class);
        startActivity(intent);
    }
}