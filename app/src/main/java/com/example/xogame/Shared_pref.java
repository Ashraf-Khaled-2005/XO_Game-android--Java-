package com.example.xogame;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared_pref {
    private SharedPreferences sp;
    private  SharedPreferences.Editor  edit;
private  Context context;
    public Shared_pref(Context context) {
        sp= context.getSharedPreferences("Settings",Context.MODE_PRIVATE);
        edit=sp.edit();
        this.context=context;
    }


    public void AddBool(String key,Boolean value){
        edit.putBoolean(key,value);

    }
    public Boolean GetBool(String key){
      return  sp.getBoolean(key,false);

    }





}
