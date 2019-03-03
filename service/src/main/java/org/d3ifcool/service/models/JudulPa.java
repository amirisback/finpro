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
public class JudulPa implements Parcelable {

    @Expose
    @SerializedName("id_judul")
    int id;

    @Expose
    @SerializedName("judul")
    private String judul;

    @Expose
    @SerializedName("kategori")
    private String kategori;

    @Expose
    @SerializedName("deskripsi")
    private String deskripsi;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("nip_dosen")
    private String nip_dosen;

    @Expose
    @SerializedName("kelompok")
    private String kelompok;

    public JudulPa(String judul, String kategori) {
        this.judul = judul;
        this.kategori = kategori;
    }

    protected JudulPa(Parcel in) {
        judul = in.readString();
        kategori = in.readString();
    }

    public static final Creator<JudulPa> CREATOR = new Creator<JudulPa>() {
        @Override
        public JudulPa createFromParcel(Parcel in) {
            return new JudulPa(in);
        }

        @Override
        public JudulPa[] newArray(int size) {
            return new JudulPa[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(judul);
        dest.writeString(kategori);
    }
}
