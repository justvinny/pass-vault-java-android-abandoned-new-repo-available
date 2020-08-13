package com.github.justvinny.passvault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.justvinny.passvault.R;
import com.github.justvinny.passvault.domain.PlatformAccount;
import com.github.justvinny.passvault.files.TextFileUtility;

import java.util.ArrayList;

public class ShowStoredAccounts extends AppCompatActivity {

    private static final String TAG = "ShowStoredAccounts";
    private LinearLayout linearLayoutStoredAccounts;
    private ArrayList<PlatformAccount> listOfStoredAccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_stored_accounts);

        linearLayoutStoredAccounts = (LinearLayout) findViewById(R.id.linearLayout_storedAccounts);

        listOfStoredAccounts = TextFileUtility.load(getApplicationContext());

        if (listOfStoredAccounts != null) {
            dynamicallyCreateButton();
        }
    }

    private void dynamicallyCreateButton() {
        int count = 100;

        for (final PlatformAccount platformAccount : listOfStoredAccounts) {
            String buttonText = String.format("%s (%s)", platformAccount.getPlatformUsername(),
                    platformAccount.getPlatformName());

            Button button = new Button(getApplicationContext());
            button.setLayoutParams(
                    new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setId(count);
            button.setText(buttonText);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   copyPassToClipboard(platformAccount);
                }
            });

            linearLayoutStoredAccounts.addView(button);
            count++;
        }
    }

    private void copyPassToClipboard(PlatformAccount platformAccount) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Password", platformAccount.getPlatformPassword());
        clipboardManager.setPrimaryClip(clipData);
        Toast.makeText(getApplicationContext(), "Password copied successfully!", Toast.LENGTH_LONG).show();
    }
}