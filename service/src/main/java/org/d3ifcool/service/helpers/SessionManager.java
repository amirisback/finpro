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
    private static final String KODE = "KODE";
    private static final String DOSEN_STATUS = "STATUS";
    private static final String USERNAME_DOSEN = "USERNAME_DOSEN";

    private static final String NIM_MHS = "NIM_MHS";
    private static final String NAMA_M = "NAMA_M";
    private static final String FOTO_M = "FOTO_M";
    private static final String EMAIL_M = "EMAIL_M";
    private static final String KONTAK_M = "KONTAK_M";
    private static final String ANGKATAN = "ANGKATAN";
    private static final String MAHASISWA_STATUS = "STATUS";
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
        editor.putString(NIM_MHS, mahasiswa.getMhs_nim());
        editor.putString(NAMA_M, mahasiswa.getMhs_nama());
        editor.putString(FOTO_M, mahasiswa.getMhs_foto());
        editor.putString(EMAIL_M, mahasiswa.getMhs_email());
        editor.putString(KONTAK_M, mahasiswa.getMhs_kontak());
        editor.putString(MAHASISWA_STATUS, mahasiswa.getStatus());
        editor.apply();
        editor.commit();
    }

    public void createSessionDataDosen(Dosen dosen){
        editor.putString(NIP_DOSEN, dosen.getDsn_nip());
        editor.putString(NAMA_D, dosen.getDsn_nama());
        editor.putString(FOTO, dosen.getDsn_foto());
        editor.putString(EMAIL_D, dosen.getDsn_email());
        editor.putString(KONTAK_D, dosen.getDsn_kontak());
        editor.putString(KODE, dosen.getDsn_kode());
        editor.putString(DOSEN_STATUS, dosen.getDsn_status());
        editor.apply();
        editor.commit();
    }

    public String getSessionMahasiswaNim(){
        return sharedPreferences.getString(NIM_MHS, null);
    }

    public String getSessionMahasiswaNamaM(){
        return sharedPreferences.getString(NAMA_M, null);
    }

    public String getSessionMahasiswaFotoM(){
        return sharedPreferences.getString(FOTO_M, null);
    }

    public String getSessionMahasiswaEmailM(){
        return sharedPreferences.getString(EMAIL_M, null);
    }

    public String getSessionMahasiswaKontakM(){
        return sharedPreferences.getString(KONTAK_M, null);
    }

    public String getSessionMahasiswaAngkatanM(){
        return sharedPreferences.getString(ANGKATAN, null);
    }

    public String getSessionMahasiswaStatus(){
        return sharedPreferences.getString(MAHASISWA_STATUS, null);
    }

    public String getSessionMahasiswaUsernameM(){
        return sharedPreferences.getString(USERNAME_MHS, null);
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

    public String getSessionDosenKode(){
        return sharedPreferences.getString(KODE, null);
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
