package com.github.justvinny.passvault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.justvinny.passvault.R;
import com.github.justvinny.passvault.domain.PlatformAccount;
import com.github.justvinny.passvault.files.TextFileUtility;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Iterator;

public class DeleteAccountActivity extends AppCompatActivity {

    private static final String TAG = "DeleteAccountActivity";
    private LinearLayout linearLayoutDeleteAccounts;
    private ArrayList<PlatformAccount> listOfAccounts;
    private ArrayList<PlatformAccount> listOfAccountsToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        linearLayoutDeleteAccounts = (LinearLayout) findViewById(R.id.linearLayout_deleteAccounts);
        listOfAccounts = TextFileUtility.load(getApplicationContext());
        listOfAccountsToDelete = new ArrayList<>();

        if (listOfAccounts != null) {
            dynamicallyCreateButton();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Iterator<PlatformAccount> listOfAccountsToSaveTemp = listOfAccounts.iterator();

        while (listOfAccountsToSaveTemp.hasNext()) {
            if (listOfAccountsToDelete.contains(listOfAccountsToSaveTemp.next())) {
                listOfAccountsToSaveTemp.remove();
            }
        }

        Iterator<PlatformAccount> listOfAccountsToSaveTemp2 = listOfAccounts.listIterator();
        ArrayList<PlatformAccount> listOfAccountsToSave = new ArrayList<>();

        while (listOfAccountsToSaveTemp2.hasNext()) {
            listOfAccountsToSave.add(listOfAccountsToSaveTemp2.next());
        }

        TextFileUtility.save(getApplicationContext(), listOfAccountsToSave);
    }

    private void dynamicallyCreateButton() {
        int count = 200;

        for (final PlatformAccount platformAccount : listOfAccounts) {
            String buttonText = String.format("%s (%S)", platformAccount.getPlatformUsername(),
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
                    deleteAccount(view, platformAccount);
                }
            });

            linearLayoutDeleteAccounts.addView(button);
            count++;
        }
    }

    private void deleteAccount(View view, PlatformAccount platformAccount) {
        String textToDelete = ((Button) view).getText().toString().toLowerCase();

        for (PlatformAccount eachAccount : listOfAccounts) {
            if (textToDelete.equals(String.format("%s (%s)", eachAccount.getPlatformUsername(),
                    eachAccount.getPlatformName()).toLowerCase())) {
                ((ViewGroup) view.getParent()).removeView(view);
                this.listOfAccountsToDelete.add(eachAccount);
                Toast.makeText(getApplicationContext(), "Account Successfully Removed!",
                        Toast.LENGTH_LONG).show();
            }
        }
    }
}