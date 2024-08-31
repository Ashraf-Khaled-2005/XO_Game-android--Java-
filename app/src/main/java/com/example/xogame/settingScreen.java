package com.example.xogame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Switch;

public class settingScreen extends AppCompatActivity {
    Switch soundSwitch, music;
    MediaPlayer player1, player2;
    private Boolean soundon, musicon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_screen);
        soundSwitch = findViewById(R.id.sound_sw);
        music = findViewById(R.id.music_Sw);

        // Initialize SharedPreferences and MediaPlayers

        player1 = MediaPlayer.create(getBaseContext(), R.raw.music);
        player2 = MediaPlayer.create(getBaseContext(), R.raw.sound);



        player1.setOnCompletionListener(mediaPlayer -> mediaPlayer.start());

        // Handle music switch changes
        music.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                player1.start();
            } else {
                player1.pause();
            }
        });

        // Handle sound switch changes
        soundSwitch.setOnCheckedChangeListener((compoundButton, isChecked) -> {
            if (isChecked) {
                player2.start();
            } else {
                player2.pause();
            }
            // Save the state
        });

    }
}