package com.android.guicelebrini.instagram.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class Preferences {

    private Context context;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private final String FILE_NAME = "instagram.preferences";
    private final int MODE = 0;

    private final String KEY_ID = "loggedUserId";
    private final String KEY_NAME = "loggedUserName";

    public Preferences(Context parameterContext){
        this.context = parameterContext;
        preferences = context.getSharedPreferences(FILE_NAME, MODE);
        editor = preferences.edit();
    }

    public void saveData(String userId, String userName){
        editor.putString(KEY_ID, userId);
        editor.putString(KEY_NAME, userName);
        editor.commit();
    }

    public String getUserId(){
        return preferences.getString(KEY_ID, null);
    }

    public String getUserName(){
        return preferences.getString(KEY_NAME, null);
    }
}