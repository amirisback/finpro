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
public class Mahasiswa implements Parcelable {
    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;
    @Expose
    @SerializedName("mhs_nama")
    private String mhs_nama;
    @Expose
    @SerializedName("angkatan")
    private String angkatan;
    @Expose
    @SerializedName("mhs_kontak")
    private String mhs_kontak;
    @Expose
    @SerializedName("mhs_foto")
    private String mhs_foto;
    @Expose
    @SerializedName("mhs_email")
    private String mhs_email;
    @Expose
    @SerializedName("status")
    private String status;


    public Mahasiswa(String mhs_nim, String mhs_nama, String angkatan, String mhs_kontak, String mhs_foto, String mhs_email, String status) {
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.angkatan = angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.mhs_email = mhs_email;
        this.status = status;
    }

    protected Mahasiswa(Parcel in) {
        mhs_nim = in.readString();
        mhs_nama = in.readString();
        angkatan = in.readString();
        mhs_kontak = in.readString();
        mhs_foto = in.readString();
        mhs_email = in.readString();
        status = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel in) {
            return new Mahasiswa(in);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mhs_nim);
        dest.writeString(mhs_nama);
        dest.writeString(angkatan);
        dest.writeString(mhs_kontak);
        dest.writeString(mhs_foto);
        dest.writeString(mhs_email);
        dest.writeString(status);
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

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getMhs_kontak() {
        return mhs_kontak;
    }

    public void setMhs_kontak(String mhs_kontak) {
        this.mhs_kontak = mhs_kontak;
    }

    public String getMhs_foto() {
        return mhs_foto;
    }

    public void setMhs_foto(String mhs_foto) {
        this.mhs_foto = mhs_foto;
    }

    public String getMhs_email() {
        return mhs_email;
    }

    public void setMhs_email(String mhs_email) {
        this.mhs_email = mhs_email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
