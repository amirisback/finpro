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
    @SerializedName("nama_m")
    private String nama_m;
    @Expose
    @SerializedName("nim_mhs")
    private String nim_mhs;
    @Expose
    @SerializedName("foto_m")
    private String foto_m;
    @Expose
    @SerializedName("email_m")
    private String email_m;
    @Expose
    @SerializedName("kontak_m")
    private String kontak_m;
    @Expose
    @SerializedName("angkatan")
    private String angkatan;
    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("username_mhs")
    private String username_mhs;

    public Mahasiswa(String nama_m, String nim_mhs, String foto_m, String email_m, String kontak_m, String angkatan, String status, String username_mhs) {
        this.nama_m = nama_m;
        this.nim_mhs = nim_mhs;
        this.foto_m = foto_m;
        this.email_m = email_m;
        this.kontak_m = kontak_m;
        this.angkatan = angkatan;
        this.status = status;
        this.username_mhs = username_mhs;
    }

    public String getNama_m() {
        return nama_m;
    }

    public void setNama_m(String nama_m) {
        this.nama_m = nama_m;
    }

    public String getNim_mhs() {
        return nim_mhs;
    }

    public void setNim_mhs(String nim_mhs) {
        this.nim_mhs = nim_mhs;
    }

    public String getFoto_m() {
        return foto_m;
    }

    public void setFoto_m(String foto_m) {
        this.foto_m = foto_m;
    }

    public String getEmail_m() {
        return email_m;
    }

    public void setEmail_m(String email_m) {
        this.email_m = email_m;
    }

    public String getKontak_m() {
        return kontak_m;
    }

    public void setKontak_m(String kontak_m) {
        this.kontak_m = kontak_m;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername_mhs() {
        return username_mhs;
    }

    public void setUsername_mhs(String username_mhs) {
        this.username_mhs = username_mhs;
    }

    protected Mahasiswa(Parcel in) {
        nama_m = in.readString();
        nim_mhs = in.readString();
        foto_m = in.readString();
        email_m = in.readString();
        kontak_m = in.readString();
        angkatan = in.readString();
        status = in.readString();
        username_mhs = in.readString();
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
        dest.writeString(nama_m);
        dest.writeString(nim_mhs);
        dest.writeString(foto_m);
        dest.writeString(email_m);
        dest.writeString(kontak_m);
        dest.writeString(angkatan);
        dest.writeString(status);
        dest.writeString(username_mhs);
    }
}
