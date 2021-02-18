package org.d3ifcool.finpro.core.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.KoordinatorPa;
import org.d3ifcool.finpro.core.models.Mahasiswa;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.*;


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

    @SuppressLint("CommitPrefEdits")
    public SessionManager(Context context) {
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
        editor.putString(MHS_ANGKATAN, mahasiswa.getAngkatan());
        editor.putString(MHS_JUDUL, mahasiswa.getJudul_nama());
        editor.putString(MHS_JUDUL_DESKRIPSI, mahasiswa.getJudul_deskripsi());
        editor.putString(MHS_JUDUL_STATUS, mahasiswa.getJudul_status());
        editor.putInt(MHS_ID_JUDUL, mahasiswa.getJudul_id());
        editor.apply();
        editor.commit();
    }

    public void createSessionJudulMahasiswa(int id){
        editor.putInt(MHS_ID_JUDUL, id);
        editor.apply();
        editor.commit();
    }

    public void createSessionJudulStatusMahasiswa(String status){
        editor.putString(MHS_JUDUL_STATUS, status);
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
        editor.putInt(DSN_BATAS_BIMBINGAN, dosen.getBatas_bimbingan());
        editor.putInt(DSN_BATAS_REVIEWER, dosen.getBatas_reviewer());
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

    public void updateSessionMahasiswa(String nama, String angkatan, String kontak, String email){
        editor.putString(MHS_NAMA, nama);
        editor.putString(MHS_EMAIL, email);
        editor.putString(MHS_KONTAK, kontak);
        editor.putString(MHS_ANGKATAN, angkatan);
        editor.apply();
        editor.commit();
    }

    public void updateSessionDosen(String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_email){
        editor.putString(DSN_NAMA, dsn_nama);
        editor.putString(DSN_EMAIL, dsn_email);
        editor.putString(DSN_KONTAK, dsn_kontak);
        editor.putString(DSN_KODE, dsn_kode);
        editor.apply();
        editor.commit();
    }

    public void updateSessionKoor(String koor_nip, String koor_nama, String koor_kode, String koor_kontak, String koor_email){
        editor.putString(KOOR_NIP, koor_nip);
        editor.putString(KOOR_NAMA, koor_nama);
        editor.putString(KOOR_EMAIL, koor_email);
        editor.putString(KOOR_KONTAK, koor_kontak);
        editor.putString(KOOR_KODE, koor_kode);
        editor.apply();
        editor.commit();
    }

    public void createSessionProyekAkhirMahasiswa(int id){
        editor.putInt(MHS_ID_PROYEK_AKHIR, id);
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

    public String getSessionMahasiswaJudul(){
        return sharedPreferences.getString(MHS_JUDUL, null);
    }

    public String getSessionMahasiswaJudulDeskripsi(){
        return sharedPreferences.getString(MHS_JUDUL_DESKRIPSI, null);
    }

    public String getSessionMahasiswaJudulStatus(){
        return sharedPreferences.getString(MHS_JUDUL_STATUS, null);
    }

    public int getSessionProyekAkhirId(){
        return sharedPreferences.getInt(MHS_ID_PROYEK_AKHIR, 0);
    }

    public String getSessionMahasiswaStatus(){
        return sharedPreferences.getString(MHS_STATUS, null);
    }

    public int getSessionDosenBatasBimbingan(){
        return sharedPreferences.getInt(DSN_BATAS_BIMBINGAN, 0);
    }


    public int getSessionDosenBatasReviewer(){
        return sharedPreferences.getInt(DSN_BATAS_REVIEWER, 0);
    }

    public int getSessionMahasiswaIdJudul(){
        return sharedPreferences.getInt(MHS_ID_JUDUL, 0);
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