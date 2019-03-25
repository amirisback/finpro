package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 24/12/2018.
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
public class Judul implements Parcelable {

    @Expose
    @SerializedName("judul_id")
    int id;

    @Expose
    @SerializedName("judul_nama")
    private String judul;

    @Expose
    @SerializedName("judul_kategori")
    private String kategori;

    @Expose
    @SerializedName("judul_deskripsi")
    private String deskripsi;

    @Expose
    @SerializedName("judul_status")
    private String tersedia;

    @Expose
    @SerializedName("dsn_nip")
    private String nip_dosen;


    public Judul(int id, String judul, String kategori, String deskripsi, String tersedia, String nip_dosen) {
        this.id = id;
        this.judul = judul;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.tersedia = tersedia;
        this.nip_dosen = nip_dosen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTersedia() {
        return tersedia;
    }

    public void setTersedia(String tersedia) {
        this.tersedia = tersedia;
    }

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    protected Judul(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        kategori = in.readString();
        deskripsi = in.readString();
        tersedia = in.readString();
        nip_dosen = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(judul);
        dest.writeString(kategori);
        dest.writeString(deskripsi);
        dest.writeString(tersedia);
        dest.writeString(nip_dosen);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Judul> CREATOR = new Creator<Judul>() {
        @Override
        public Judul createFromParcel(Parcel in) {
            return new Judul(in);
        }

        @Override
        public Judul[] newArray(int size) {
            return new Judul[size];
        }
    };
}
