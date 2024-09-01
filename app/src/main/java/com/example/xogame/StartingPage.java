package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;


public class StartingPage extends AppCompatActivity {
    Button start, exit, setteing;
    MediaPlayer player;
    SharedPreferences sp;
    private Boolean musicOn;
    private  Language lang;
    private String curLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lang =new Language(getBaseContext());
        try {
       curLang=     lang.GetLocal();

        }catch (Exception e){
            lang.setLocale("en");
            curLang=lang.curlang;

        }
        setContentView(R.layout.activity_starting_page);
        setteing = findViewById(R.id.setteing);
        sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        player = MediaPlayer.create(getBaseContext(), R.raw.music);
        start = findViewById(R.id.start_btn);
        exit = findViewById(R.id.exit_btn);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                player.pause();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        setteing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), settingScreen.class);
                startActivity(i );

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
       String newlang=  lang.GetLocal();
        if (!curLang.equals(newlang)) {
            recreate();  // Restart the activity to apply the new language
        }
    }
}