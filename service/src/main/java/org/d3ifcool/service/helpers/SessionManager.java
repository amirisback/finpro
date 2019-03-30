package org.d3ifcool.service.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.KoordinatorPa;
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

    private static final String DSN_NIP = "DSN_NIP";
    private static final String DSN_NAMA = "DSN_NAMA";
    private static final String DSN_FOTO = "DSN_FOTO";
    private static final String DSN_EMAIL = "DSN_EMAIL";
    private static final String DSN_KONTAK = "DSN_KONTAK";
    private static final String DSN_KODE = "DSN_KODE";
    private static final String DSN_STATUS = "DSN_STATUS";

    private static final String MHS_NIM = "MHS_NIM";
    private static final String MHS_NAMA = "MHS_NAMA";
    private static final String MHS_FOTO = "MHS_FOTO";
    private static final String MHS_EMAIL = "MHS_EMAIL";
    private static final String MHS_KONTAK = "MHS_KONTAK";
    private static final String MHS_STATUS = "STATUS";
    private static final String MHS_ANGKATAN = "ANGKATAN";
    private static final String MHS_ID_JUDUL = "MHS_ID_JUDUL";

    private static final String KOOR_NIP = "KOOR_NIM";
    private static final String KOOR_NAMA = "KOOR_NAMA";
    private static final String KOOR_KODE = "KOOR_KODE";
    private static final String KOOR_EMAIL = "KOOR_EMAIL";
    private static final String KOOR_KONTAK = "KOOR_KONTAK";
    private static final String KOOR_FOTO = "KOOR_FOTO";
    private static final String KOOR_USERNAME = "USERNAME_KOOR";


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
        editor.putString(MHS_NIM, mahasiswa.getMhs_nim());
        editor.putString(MHS_NAMA, mahasiswa.getMhs_nama());
        editor.putString(MHS_FOTO, mahasiswa.getMhs_foto());
        editor.putString(MHS_EMAIL, mahasiswa.getMhs_email());
        editor.putString(MHS_KONTAK, mahasiswa.getMhs_kontak());
        editor.putString(MHS_STATUS, mahasiswa.getStatus());
        editor.apply();
        editor.commit();
    }

    public void createSessionDataDosen(Dosen dosen){
        editor.putString(DSN_NIP, dosen.getDsn_nip());
        editor.putString(DSN_NAMA, dosen.getDsn_nama());
        editor.putString(DSN_FOTO, dosen.getDsn_foto());
        editor.putString(DSN_EMAIL, dosen.getDsn_email());
        editor.putString(DSN_KONTAK, dosen.getDsn_kontak());
        editor.putString(DSN_KODE, dosen.getDsn_kode());
        editor.apply();
        editor.commit();
    }

    public void createSessionDataKoor(KoordinatorPa koor){
        editor.putString(KOOR_NIP, koor.getKoor_nip());
        editor.putString(KOOR_NAMA, koor.getKoor_nama());
        editor.putString(KOOR_FOTO, koor.getKoor_foto());
        editor.putString(KOOR_EMAIL, koor.getKoor_email());
        editor.putString(KOOR_KONTAK, koor.getKoor_kontak());
        editor.putString(KOOR_KODE, koor.getKoor_kode());
        editor.putString(KOOR_USERNAME, koor.getUsername());
        editor.apply();
        editor.commit();
    }

    public String getSessionMahasiswaNim(){
        return sharedPreferences.getString(MHS_NIM, null);
    }

    public String getSessionMahasiswaNama(){
        return sharedPreferences.getString(MHS_NAMA, null);
    }

    public String getSessionMahasiswaFoto(){
        return sharedPreferences.getString(MHS_FOTO, null);
    }

    public String getSessionMahasiswaEmail(){
        return sharedPreferences.getString(MHS_EMAIL, null);
    }

    public String getSessionMahasiswaKontak(){
        return sharedPreferences.getString(MHS_KONTAK, null);
    }

    public String getSessionMahasiswaAngkatan(){
        return sharedPreferences.getString(MHS_ANGKATAN, null);
    }

    public String getSessionMahasiswaStatus(){
        return sharedPreferences.getString(MHS_STATUS, null);
    }

    public String getSessionMahasiswaIdJudul(){
        return sharedPreferences.getString(MHS_ID_JUDUL, null);
    }

    public String getSessionDosenNip(){
        return sharedPreferences.getString(DSN_NIP, null);
    }

    public String getSessionDosenNama(){
        return sharedPreferences.getString(DSN_NAMA, null);
    }

    public String getSessionDosenFoto(){
        return sharedPreferences.getString(DSN_FOTO, null);
    }

    public String getSessionDosenEmail(){
        return sharedPreferences.getString(DSN_EMAIL, null);
    }

    public String getSessionDosenKontak(){
        return sharedPreferences.getString(DSN_KONTAK, null);
    }

    public String getSessionDosenKode(){
        return sharedPreferences.getString(DSN_KODE, null);
    }

    public String getSessionDosenStatus() {
        return sharedPreferences.getString(DSN_STATUS, null);
    }

    public String getSessionKoorNip(){
        return sharedPreferences.getString(KOOR_NIP, null);
    }

    public String getSessionKoorNama(){
        return sharedPreferences.getString(KOOR_NAMA, null);
    }

    public String getSessionKoorKode(){
        return sharedPreferences.getString(KOOR_KODE, null);
    }

    public String getSessionKoorKontak(){
        return sharedPreferences.getString(KOOR_KONTAK, null);
    }

    public String getSessionKoorEmail(){
        return sharedPreferences.getString(KOOR_EMAIL, null);
    }

    public String getSessionKoorFoto(){
        return sharedPreferences.getString(KOOR_FOTO, null);
    }

    public String getSessionKoorUsername(){
        return sharedPreferences.getString(KOOR_USERNAME, null);
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