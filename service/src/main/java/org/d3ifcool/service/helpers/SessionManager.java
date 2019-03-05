package org.d3ifcool.service.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Mahasiswa;


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

    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    private static final String USERNAME = "USERNAME";
    private static final String PENGGUNA = "PENGGUNA";

    private static final String NIP_DOSEN = "NIP_DOSEN";
    private static final String NAMA_D = "NAMA_D";
    private static final String FOTO = "FOTO";
    private static final String EMAIL_D = "EMAIL_D";
    private static final String KONTAK_D = "KONTAK_D";
    private static final String LIMIT = "LIMIT";
    private static final String USERNAME_DOSEN = "USERNAME_DOSEN";

    private static final String NIM_MHS = "NIM_MHS";
    private static final String NAMA_M = "NAMA_M";
    private static final String FOTO_M = "FOTO_M";
    private static final String EMAIL_M = "EMAIL_M";
    private static final String KONTAK_M = "KONTAK_M";
    private static final String ANGKATAN = "ANGKATAN";
    private static final String STATUS = "STATUS";
    private static final String USERNAME_MHS = "USERNAME_MHS";


    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String username, String pengguna){
        editor.putBoolean(LOGIN, true);
        editor.putString(USERNAME, username);
        editor.putString(PENGGUNA, pengguna);
        editor.apply();
        editor.commit();
    }

    public void createSessionDataMahasiswa(Mahasiswa mahasiswa){
        editor.putString(NIM_MHS, mahasiswa.getNim_mhs());
        editor.putString(NAMA_M, mahasiswa.getNama_m());
        editor.putString(FOTO_M, mahasiswa.getFoto_m());
        editor.putString(EMAIL_M, mahasiswa.getEmail_m());
        editor.putString(KONTAK_M, mahasiswa.getKontak_m());
        editor.putString(ANGKATAN, mahasiswa.getAngkatan());
        editor.putString(STATUS, mahasiswa.getStatus());
        editor.putString(USERNAME_MHS, mahasiswa.getUsername_mhs());
        editor.apply();
        editor.commit();
    }

    public void createSessionDataDosen(Dosen dosen){
        editor.putString(NIP_DOSEN, dosen.getNip_dosen());
        editor.putString(NAMA_D, dosen.getNama_d());
        editor.putString(FOTO, dosen.getFoto());
        editor.putString(EMAIL_D, dosen.getEmail());
        editor.putString(KONTAK_D, dosen.getNo_telefon());
        editor.putInt(LIMIT, dosen.getLimit());
        editor.putString(USERNAME_DOSEN, dosen.getUsername());
        editor.apply();
        editor.commit();
    }

    public String getSessionDosenNip(){
        return sharedPreferences.getString(NIP_DOSEN, null);
    }

    public String getSessionDosenNamaD(){
        return sharedPreferences.getString(NAMA_D, null);
    }

    public String getSessionDosenFoto(){
        return sharedPreferences.getString(FOTO, null);
    }

    public String getSessionDosenEmail(){
        return sharedPreferences.getString(EMAIL_D, null);
    }

    public String getSessionDosenKontak(){
        return sharedPreferences.getString(KONTAK_D, null);
    }

    public String getSessionDosenLimit(){
        return sharedPreferences.getString(LIMIT, null);
    }

    public String getSessionDosenUserNameDosen(){
        return sharedPreferences.getString(USERNAME_DOSEN, null);
    }

    public String getSessionPengguna(){
        return sharedPreferences.getString(PENGGUNA, null);
    }

    public String getSessionUsername(){
        return sharedPreferences.getString(USERNAME, null);
    }

    public void removeSession(){
        editor.clear();
        editor.commit();
    }

}
