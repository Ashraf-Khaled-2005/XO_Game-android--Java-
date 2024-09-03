package com.example.xogame;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import java.util.Locale;

public class settingScreen extends AppCompatActivity {
    Shared_pref sharedPref;
    Switch soundSwitch, musicSwitch;
    MediaPlayer player1, player2;
    private Boolean soundon, musicon;
    private Button lang_btn;
  private   Language lang;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
       lang =new Language(getBaseContext());



        super.onCreate(savedInstanceState);
        sharedPref=new Shared_pref(this);

        try {
            musicon= sharedPref.GetBool("IsMusicOn");
            soundon=sharedPref.GetBool("IsSoundOn");
            lang.GetLocal();
        }catch (Exception e){
            lang.setLocale("en");
        }
        setContentView(R.layout.activity_setting_screen);

        soundSwitch = findViewById(R.id.sound_sw);
        musicSwitch = findViewById(R.id.music_Sw);
        lang_btn=findViewById(R.id.lang);


        player1 = MediaPlayer.create(getBaseContext(), R.raw.music);
        player2 = MediaPlayer.create(getBaseContext(), R.raw.sound);

        musicSwitch.setChecked(musicon);
        soundSwitch.setChecked(soundon);

        player1.setOnCompletionListener(mediaPlayer -> mediaPlayer.start());

        // Handle music switch changes
        musicSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                startService(new Intent(this, MediaPlayerService.class));

            } else {

                stopService(new Intent(this, MediaPlayerService.class));


            }sharedPref.AddBool("IsMusicOn",isChecked);


        });

        soundSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                player2.start();
                sharedPref.AddBool("IsSoundOn",isChecked);


            } else {
                player2.pause();
                sharedPref.AddBool("IsSoundOn",isChecked);


            }
            // Save the state
        });

        lang_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showlanguageDialog();
            }
        });


    }
    private void showlanguageDialog() {
        final String[] langs = {"English", "Français", "Deutch", "Italiano","اللغه العربية"};
        AlertDialog.Builder mbuilder = new AlertDialog.Builder(this); // Use 'this' instead of 'getBaseContext()'
        mbuilder.setTitle("Choose Language");

        mbuilder.setSingleChoiceItems(langs, -1, (dialogInterface, i) -> {

            switch (i) {
                case 0: // English
                   lang.setLocale("en");
                    break;
                case 1: // Français
                    lang.setLocale("fr");

                    break;
                case 2: // langue allemande (German)
                    lang.setLocale("de");

                    break;
                case 3: // Italiano
                    lang.setLocale("it");


                    break;  case 4: // Italiano
                    lang.setLocale("ar");


                    break;
            }
            recreate();
            dialogInterface.dismiss(); // Dismiss the dialog after selection
        });

        mbuilder.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        AlertDialog mdialog = mbuilder.create();
        mdialog.show();
    }


}