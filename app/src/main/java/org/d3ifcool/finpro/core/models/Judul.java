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
public class Judul implements Parcelable {

    @Expose
    @SerializedName("judul_id")
    private int id;

    @Expose
    @SerializedName("judul_nama")
    private String judul;

    @Expose
    @SerializedName("kategori_id")
    private String kategori_id;

    @Expose
    @SerializedName("kategori_nama")
    private String kategori_nama;

    @Expose
    @SerializedName("judul_deskripsi")
    private String deskripsi;

    @Expose
    @SerializedName("judul_status")
    private String judul_status;

    @Expose
    @SerializedName("judul_waktu")
    private String judul_waktu;

    @Expose
    @SerializedName("dsn_nip")
    private String nip_dosen;

    @Expose
    @SerializedName("dsn_nama")
    private String dsn_nama;

    @Expose
    @SerializedName("dsn_kode")
    private String dsn_kode;

    @Expose
    @SerializedName("dsn_kontak")
    private String dsn_kontak;

    @Expose
    @SerializedName("dsn_foto")
    private String dsn_foto;

    @Expose
    @SerializedName("username")
    private String username;

    @Expose
    @SerializedName("dsn_email")
    private String dsn_email;

    public Judul(int id, String judul, String kategori_id, String kategori_nama, String deskripsi, String judul_status, String judul_waktu, String nip_dosen, String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_foto, String username, String dsn_email) {
        this.id = id;
        this.judul = judul;
        this.kategori_id = kategori_id;
        this.kategori_nama = kategori_nama;
        this.deskripsi = deskripsi;
        this.judul_status = judul_status;
        this.judul_waktu = judul_waktu;
        this.nip_dosen = nip_dosen;
        this.dsn_nama = dsn_nama;
        this.dsn_kode = dsn_kode;
        this.dsn_kontak = dsn_kontak;
        this.dsn_foto = dsn_foto;
        this.username = username;
        this.dsn_email = dsn_email;
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

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getKategori_nama() {
        return kategori_nama;
    }

    public void setKategori_nama(String kategori_nama) {
        this.kategori_nama = kategori_nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
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

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    public String getDsn_nama() {
        return dsn_nama;
    }

    public void setDsn_nama(String dsn_nama) {
        this.dsn_nama = dsn_nama;
    }

    public String getDsn_kode() {
        return dsn_kode;
    }

    public void setDsn_kode(String dsn_kode) {
        this.dsn_kode = dsn_kode;
    }

    public String getDsn_kontak() {
        return dsn_kontak;
    }

    public void setDsn_kontak(String dsn_kontak) {
        this.dsn_kontak = dsn_kontak;
    }

    public String getDsn_foto() {
        return dsn_foto;
    }

    public void setDsn_foto(String dsn_foto) {
        this.dsn_foto = dsn_foto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDsn_email() {
        return dsn_email;
    }

    public void setDsn_email(String dsn_email) {
        this.dsn_email = dsn_email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.judul);
        dest.writeString(this.kategori_id);
        dest.writeString(this.kategori_nama);
        dest.writeString(this.deskripsi);
        dest.writeString(this.judul_status);
        dest.writeString(this.judul_waktu);
        dest.writeString(this.nip_dosen);
        dest.writeString(this.dsn_nama);
        dest.writeString(this.dsn_kode);
        dest.writeString(this.dsn_kontak);
        dest.writeString(this.dsn_foto);
        dest.writeString(this.username);
        dest.writeString(this.dsn_email);
    }

    protected Judul(Parcel in) {
        this.id = in.readInt();
        this.judul = in.readString();
        this.kategori_id = in.readString();
        this.kategori_nama = in.readString();
        this.deskripsi = in.readString();
        this.judul_status = in.readString();
        this.judul_waktu = in.readString();
        this.nip_dosen = in.readString();
        this.dsn_nama = in.readString();
        this.dsn_kode = in.readString();
        this.dsn_kontak = in.readString();
        this.dsn_foto = in.readString();
        this.username = in.readString();
        this.dsn_email = in.readString();
    }

    public static final Creator<Judul> CREATOR = new Creator<Judul>() {
        @Override
        public Judul createFromParcel(Parcel source) {
            return new Judul(source);
        }

        @Override
        public Judul[] newArray(int size) {
            return new Judul[size];
        }
    };
}
