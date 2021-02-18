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
public class Informasi implements Parcelable {
    @Expose
    @SerializedName("informasi_id")
    private int id;

    @Expose
    @SerializedName("informasi_judul")
    private String info_judul;

    @Expose
    @SerializedName("informasi_isi")
    private String info_deskripsi;

    @Expose
    @SerializedName("penerbit")
    private String penerbit;

    @Expose
    @SerializedName("informasi_waktu")
    private String tanggal;

    public Informasi(int id, String info_judul, String info_deskripsi, String penerbit, String tanggal) {
        this.id = id;
        this.info_judul = info_judul;
        this.info_deskripsi = info_deskripsi;
        this.penerbit = penerbit;
        this.tanggal = tanggal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo_judul() {
        return info_judul;
    }

    public void setInfo_judul(String info_judul) {
        this.info_judul = info_judul;
    }

    public String getInfo_deskripsi() {
        return info_deskripsi;
    }

    public void setInfo_deskripsi(String info_deskripsi) {
        this.info_deskripsi = info_deskripsi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    protected Informasi(Parcel in) {
        id = in.readInt();
        info_judul = in.readString();
        info_deskripsi = in.readString();
        tanggal = in.readString();
        penerbit = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(info_judul);
        dest.writeString(info_deskripsi);
        dest.writeString(tanggal);
        dest.writeString(penerbit);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Informasi> CREATOR = new Creator<Informasi>() {
        @Override
        public Informasi createFromParcel(Parcel in) {
            return new Informasi(in);
        }

        @Override
        public Informasi[] newArray(int size) {
            return new Informasi[size];
        }
    };
}
