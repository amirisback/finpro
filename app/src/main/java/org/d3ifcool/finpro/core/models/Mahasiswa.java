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

    @Expose
    @SerializedName("judul_id")
    private int judul_id;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("judul_nama")
    private String judul_nama;

    @Expose
    @SerializedName("judul_deskripsi")
    private String judul_deskripsi;

    @Expose
    @SerializedName("judul_status")
    private String judul_status;

    @Expose
    @SerializedName("judul_waktu")
    private String judul_waktu;

    @Expose
    @SerializedName("dsn_nip")
    private String dsn_nip;

    @Expose
    @SerializedName("kategori_id")
    private String kategori_id;

    public Mahasiswa(String mhs_nim, String mhs_nama, String angkatan, String mhs_kontak, String mhs_foto, String mhs_email, String status, int judul_id, String username, String judul_nama, String judul_deskripsi, String judul_status, String judul_waktu, String dsn_nip, String kategori_id) {
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.angkatan = angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.mhs_email = mhs_email;
        this.status = status;
        this.judul_id = judul_id;
        this.username = username;
        this.judul_nama = judul_nama;
        this.judul_deskripsi = judul_deskripsi;
        this.judul_status = judul_status;
        this.judul_waktu = judul_waktu;
        this.dsn_nip = dsn_nip;
        this.kategori_id = kategori_id;
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

    public int getJudul_id() {
        return judul_id;
    }

    public void setJudul_id(int judul_id) {
        this.judul_id = judul_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getJudul_nama() {
        return judul_nama;
    }

    public void setJudul_nama(String judul_nama) {
        this.judul_nama = judul_nama;
    }

    public String getJudul_deskripsi() {
        return judul_deskripsi;
    }

    public void setJudul_deskripsi(String judul_deskripsi) {
        this.judul_deskripsi = judul_deskripsi;
    }

    public String getJudul_status() {
        return judul_status;
    }

    public void setJudul_status(String judul_status) {
        this.judul_status = judul_status;
    }

    public String getJudul_waktu() {
        return judul_waktu;
    }

    public void setJudul_waktu(String judul_waktu) {
        this.judul_waktu = judul_waktu;
    }

    public String getDsn_nip() {
        return dsn_nip;
    }

    public void setDsn_nip(String dsn_nip) {
        this.dsn_nip = dsn_nip;
    }

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mhs_nim);
        dest.writeString(this.mhs_nama);
        dest.writeString(this.angkatan);
        dest.writeString(this.mhs_kontak);
        dest.writeString(this.mhs_foto);
        dest.writeString(this.mhs_email);
        dest.writeString(this.status);
        dest.writeInt(this.judul_id);
        dest.writeString(this.username);
        dest.writeString(this.judul_nama);
        dest.writeString(this.judul_deskripsi);
        dest.writeString(this.judul_status);
        dest.writeString(this.judul_waktu);
        dest.writeString(this.dsn_nip);
        dest.writeString(this.kategori_id);
    }

    protected Mahasiswa(Parcel in) {
        this.mhs_nim = in.readString();
        this.mhs_nama = in.readString();
        this.angkatan = in.readString();
        this.mhs_kontak = in.readString();
        this.mhs_foto = in.readString();
        this.mhs_email = in.readString();
        this.status = in.readString();
        this.judul_id = in.readInt();
        this.username = in.readString();
        this.judul_nama = in.readString();
        this.judul_deskripsi = in.readString();
        this.judul_status = in.readString();
        this.judul_waktu = in.readString();
        this.dsn_nip = in.readString();
        this.kategori_id = in.readString();
    }

    public static final Creator<Mahasiswa> CREATOR = new Creator<Mahasiswa>() {
        @Override
        public Mahasiswa createFromParcel(Parcel source) {
            return new Mahasiswa(source);
        }

        @Override
        public Mahasiswa[] newArray(int size) {
            return new Mahasiswa[size];
        }
    };
}
