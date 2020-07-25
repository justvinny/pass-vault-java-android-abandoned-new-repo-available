package com.github.justvinny.passvault.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.justvinny.passvault.R;

public class MainMenu extends AppCompatActivity {

    public static final int QUIT = 1;
    private Button buttonQuit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        buttonQuit = (Button) findViewById(R.id.button_quit);
        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent quitIntent = new Intent();
                setResult(QUIT, quitIntent);
                finishAndRemoveTask();
            }
        });
    }
}