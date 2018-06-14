package com.example.hhj.hello.utils;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static android.content.Context.MODE_PRIVATE;

public class SharePUtil {
    public static void Put(int id){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor=pref.edit();
        editor.putInt("user_id",id);
        editor.apply();
    }

    public static void Remove(){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor=pref.edit();
        editor.remove("user_id");
        editor.apply();
    }

    public static int Get(){
        SharedPreferences pref= PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return pref.getInt("user_id",-1);
    }
}
