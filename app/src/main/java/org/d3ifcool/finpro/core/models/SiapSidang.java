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
 * Copyright (C) 28/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro.core.models
 */
public class SiapSidang implements Parcelable {

    @Expose
    @SerializedName("nama_tim")
    private String nama_tim;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("mhs_nama")
    private String mhs_nama;

    @Expose
    @SerializedName("mhs_foto")
    private String mhs_foto;

    @Expose
    @SerializedName("judul_nama")
    private String judul_nama;

    @Expose
    @SerializedName("jumlah_bimbingan")
    private String jumlah_bimbingan;

    public SiapSidang(String nama_tim, String mhs_nim, String mhs_nama, String mhs_foto, String judul_nama, String jumlah_bimbingan) {
        this.nama_tim = nama_tim;
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.mhs_foto = mhs_foto;
        this.judul_nama = judul_nama;
        this.jumlah_bimbingan = jumlah_bimbingan;
    }

    protected SiapSidang(Parcel in) {
        nama_tim = in.readString();
        mhs_nim = in.readString();
        mhs_nama = in.readString();
        mhs_foto = in.readString();
        judul_nama = in.readString();
        jumlah_bimbingan = in.readString();
    }

    public static final Creator<SiapSidang> CREATOR = new Creator<SiapSidang>() {
        @Override
        public SiapSidang createFromParcel(Parcel in) {
            return new SiapSidang(in);
        }

        @Override
        public SiapSidang[] newArray(int size) {
            return new SiapSidang[size];
        }
    };

    public String getNama_tim() {
        return nama_tim;
    }

    public void setNama_tim(String nama_tim) {
        this.nama_tim = nama_tim;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public String getMhs_nama() {
        return mhs_nama;
    }

    public void setMhs_nama(String mhs_nama) {
        this.mhs_nama = mhs_nama;
    }

    public String getMhs_foto() {
        return mhs_foto;
    }

    public void setMhs_foto(String mhs_foto) {
        this.mhs_foto = mhs_foto;
    }

    public String getJudul_nama() {
        return judul_nama;
    }

    public void setJudul_nama(String judul_nama) {
        this.judul_nama = judul_nama;
    }

    public String getJumlah_bimbingan() {
        return jumlah_bimbingan;
    }

    public void setJumlah_bimbingan(String jumlah_bimbingan) {
        this.jumlah_bimbingan = jumlah_bimbingan;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nama_tim);
        parcel.writeString(mhs_nim);
        parcel.writeString(mhs_nama);
        parcel.writeString(mhs_foto);
        parcel.writeString(judul_nama);
        parcel.writeString(jumlah_bimbingan);
    }
}
