package org.d3ifcool.finpro.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import org.d3ifcool.finpro.views.activities.LoginActivity;
import org.d3ifcool.finpro.views.activities.MainActivity;

import java.util.HashMap;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/03/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class SessionManager {

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private final String USERNAME = "USERNAME";
    private final String PENGGUNA = "PENGGUNA";

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String username, String pengguna){
        editor.putBoolean(LOGIN, true);
        editor.putString(USERNAME, username);
        editor.putString(PENGGUNA, pengguna);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {
        if (!this.isLogin()) {
            Intent i = new Intent(context, LoginActivity.class);
            context.startActivity(i);
            ((MainActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail(){
        HashMap<String, String> user = new HashMap<>();
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(PENGGUNA, sharedPreferences.getString(PENGGUNA, null));
        return user;
    }

    public void logout(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
        ((MainActivity) context).finish();
    }

}
