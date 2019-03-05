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
    @SerializedName("tersedia")
    private String tersedia;

    @Expose
    @SerializedName("nip_dosen")
    private String nip_dosen;

    @Expose
    @SerializedName("kelompok")
    private String kelompok;

    @Expose
    @SerializedName("created_at")
    private String created_at;

    @Expose
    @SerializedName("updated_at")
    private String updated_at;

    public JudulPa(String judul, String kategori) {
        this.judul = judul;
        this.kategori = kategori;
    }

    public JudulPa(int id, String judul, String kategori, String deskripsi, String tersedia, String nip_dosen, String kelompok, String created_at, String update_at) {
        this.id = id;
        this.judul = judul;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.tersedia = tersedia;
        this.nip_dosen = nip_dosen;
        this.kelompok = kelompok;
        this.created_at = created_at;
        this.updated_at = update_at;
    }

    protected JudulPa(Parcel in) {
        id = in.readInt();
        judul = in.readString();
        kategori = in.readString();
        deskripsi = in.readString();
        tersedia = in.readString();
        nip_dosen = in.readString();
        kelompok = in.readString();
        created_at = in.readString();
        updated_at= in.readString();
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

    public String getStatus() {
        return tersedia;
    }

    public void setStatus(String status) {
        this.tersedia = status;
    }

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(judul);
        dest.writeString(kategori);
        dest.writeString(deskripsi);
        dest.writeString(tersedia);
        dest.writeString(nip_dosen);
        dest.writeString(kelompok);
        dest.writeString(created_at);
        dest.writeString(updated_at);
    }
}
