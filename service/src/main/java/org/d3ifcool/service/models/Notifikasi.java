package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 2/27/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class Notifikasi implements Parcelable {

    @Expose
    @SerializedName("notifikasi_id")
    private String notifikasi_id;

    @Expose
    @SerializedName("token_pesan")
    private String token_pesan;

    @Expose
    @SerializedName("dari")
    private String dari;

    @Expose
    @SerializedName("untuk")
    private String untuk;

    @Expose
    @SerializedName("info_kategori")
    private String info_kategori;

    @Expose
    @SerializedName("deskripsi")
    private String deskripsi;

    @Expose
    @SerializedName("status")
    private String status;


    public Notifikasi(String notifikasi_id, String token_pesan, String dari, String untuk, String info_kategori, String deskripsi, String status) {
        this.notifikasi_id = notifikasi_id;
        this.token_pesan = token_pesan;
        this.dari = dari;
        this.untuk = untuk;
        this.info_kategori = info_kategori;
        this.deskripsi = deskripsi;
        this.status = status;
    }

    public String getNotifikasi_id() {
        return notifikasi_id;
    }

    public void setNotifikasi_id(String notifikasi_id) {
        this.notifikasi_id = notifikasi_id;
    }

    public String getToken_pesan() {
        return token_pesan;
    }

    public void setToken_pesan(String token_pesan) {
        this.token_pesan = token_pesan;
    }

    public String getDari() {
        return dari;
    }

    public void setDari(String dari) {
        this.dari = dari;
    }

    public String getUntuk() {
        return untuk;
    }

    public void setUntuk(String untuk) {
        this.untuk = untuk;
    }

    public String getInfo_kategori() {
        return info_kategori;
    }

    public void setInfo_kategori(String info_kategori) {
        this.info_kategori = info_kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    protected Notifikasi(Parcel in) {
        notifikasi_id = in.readString();
        token_pesan = in.readString();
        dari = in.readString();
        untuk = in.readString();
        info_kategori = in.readString();
        deskripsi = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(notifikasi_id);
        dest.writeString(token_pesan);
        dest.writeString(dari);
        dest.writeString(untuk);
        dest.writeString(info_kategori);
        dest.writeString(deskripsi);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notifikasi> CREATOR = new Creator<Notifikasi>() {
        @Override
        public Notifikasi createFromParcel(Parcel in) {
            return new Notifikasi(in);
        }

        @Override
        public Notifikasi[] newArray(int size) {
            return new Notifikasi[size];
        }
    };
}
