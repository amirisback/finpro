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
public class Bimbingan implements Parcelable {

    @Expose
    @SerializedName("bimbingan_id")
    private String id;

    @Expose
    @SerializedName("bimbingan_review")
    private String isi;

    @Expose
    @SerializedName("bimbingan_judul")
    private String judul_bimbingan;

    @Expose
    @SerializedName("bimbingan_tanggal")
    private String tanggal;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    public Bimbingan(String id, String isi, String judul_bimbingan, String tanggal, int proyek_akhir_id) {
        this.id = id;
        this.isi = isi;
        this.judul_bimbingan = judul_bimbingan;
        this.tanggal = tanggal;
        this.proyek_akhir_id = proyek_akhir_id;
    }

    protected Bimbingan(Parcel in) {
        id = in.readString();
        isi = in.readString();
        judul_bimbingan = in.readString();
        tanggal = in.readString();
        proyek_akhir_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(isi);
        dest.writeString(judul_bimbingan);
        dest.writeString(tanggal);
        dest.writeInt(proyek_akhir_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Bimbingan> CREATOR = new Creator<Bimbingan>() {
        @Override
        public Bimbingan createFromParcel(Parcel in) {
            return new Bimbingan(in);
        }

        @Override
        public Bimbingan[] newArray(int size) {
            return new Bimbingan[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getJudul_bimbingan() {
        return judul_bimbingan;
    }

    public void setJudul_bimbingan(String judul_bimbingan) {
        this.judul_bimbingan = judul_bimbingan;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public int getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(int proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }

}
