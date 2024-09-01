package com.example.xogame;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Language {
    private Context context;
    public   String curlang;

    public Language(Context context) {
        this.context = context;
    }

    public void setLocale(String it) {
        curlang=it;
        Locale local = new Locale(it);
        Locale.setDefault(local);
        Configuration con = new Configuration();
        con.setLocale(local);
        context.getResources().updateConfiguration(con, context.getResources().getDisplayMetrics());

        // Save language preference in SharedPreferences
        SharedPreferences.Editor edit = context.getSharedPreferences("Settings", AppCompatActivity.MODE_PRIVATE).edit();
        edit.putString("lang", it);
        edit.apply();
    }

    public String GetLocal() {
        SharedPreferences sp = context.getSharedPreferences("Settings", AppCompatActivity.MODE_PRIVATE);
         curlang = sp.getString("lang", "en");
        setLocale(curlang);
        return curlang;
    }
}
