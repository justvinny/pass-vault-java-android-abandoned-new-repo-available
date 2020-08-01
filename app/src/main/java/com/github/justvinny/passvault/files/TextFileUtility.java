package com.github.justvinny.passvault.files;

import android.content.Context;

import com.github.justvinny.passvault.domain.PlatformAccount;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TextFileUtility {

    public static final String FILE_NAME = "platform_accounts.txt";

    public static void save(Context context, final ArrayList<PlatformAccount> platformAccounts) {
        FileOutputStream fileOutputStream = null;
        StringBuilder arrayToString = new StringBuilder();

        for (PlatformAccount platformAccount : platformAccounts) {
            arrayToString.append(platformAccount.toString()).append("\n");
        }

        String toSave = arrayToString.toString();

        try {
            fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fileOutputStream.write(toSave.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static ArrayList<PlatformAccount> load(Context context) {
        ArrayList<PlatformAccount> platformAccounts = new ArrayList<>();
        FileInputStream fileInputStream = null;

        try {
            fileInputStream = context.openFileInput(FILE_NAME);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line ;

            while ((line = bufferedReader.readLine()) != null) {
                String[] accountInformation = line.split(",");
                platformAccounts.add(new PlatformAccount(
                        accountInformation[0],
                        accountInformation[1],
                        accountInformation[2]));
            }
        } catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return platformAccounts;
    }
}
