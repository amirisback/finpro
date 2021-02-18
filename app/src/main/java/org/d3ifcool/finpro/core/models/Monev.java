package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/01/2019.
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
public class Monev implements Parcelable {

    @Expose
    @SerializedName("monev_id")
    private int monev_id;

    @Expose
    @SerializedName("monev_kategori")
    private String kategori;

    @Expose
    @SerializedName("jumlah_bimbingan")
    private String jumlah_bimbingan;

    public Monev() {
    }

    public Monev(int monev_id, String kategori, String jumlah_bimbingan) {
        this.monev_id = monev_id;
        this.kategori = kategori;
        this.jumlah_bimbingan = jumlah_bimbingan;
    }

    public int getMonev_id() {
        return monev_id;
    }

    public void setMonev_id(int monev_id) {
        this.monev_id = monev_id;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getJumlah_bimbingan() {
        return jumlah_bimbingan;
    }

    public void setJumlah_bimbingan(String jumlah_bimbingan) {
        this.jumlah_bimbingan = jumlah_bimbingan;
    }

    protected Monev(Parcel in) {
        monev_id = in.readInt();
        kategori = in.readString();
        jumlah_bimbingan = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(monev_id);
        dest.writeString(kategori);
        dest.writeString(jumlah_bimbingan);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Monev> CREATOR = new Creator<Monev>() {
        @Override
        public Monev createFromParcel(Parcel in) {
            return new Monev(in);
        }

        @Override
        public Monev[] newArray(int size) {
            return new Monev[size];
        }
    };
}
