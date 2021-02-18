package org.d3ifcool.finpro.core.models;

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
    private int notifikasi_id;

    @Expose
    @SerializedName("notifikasi_tanggal")
    private String notifikasi_tanggal;

    @Expose
    @SerializedName("notifikasi_kategori")
    private String notifikasi_kategori;

    @Expose
    @SerializedName("notifikasi_deskripsi")
    private String notifikasi_deskripsi;

    @Expose
    @SerializedName("notifikasi_dari")
    private String notifikasi_dari;

    @Expose
    @SerializedName("notifikasi_untuk")
    private String notifikasi_untuk;

    @Expose
    @SerializedName("notifikasi_baca")
    private int notifikasi_baca;

    public Notifikasi(int notifikasi_id, String notifikasi_tanggal, String notifikasi_kategori, String notifikasi_deskripsi, String notifikasi_dari, String notifikasi_untuk, int notifikasi_baca) {
        this.notifikasi_id = notifikasi_id;
        this.notifikasi_tanggal = notifikasi_tanggal;
        this.notifikasi_kategori = notifikasi_kategori;
        this.notifikasi_deskripsi = notifikasi_deskripsi;
        this.notifikasi_dari = notifikasi_dari;
        this.notifikasi_untuk = notifikasi_untuk;
        this.notifikasi_baca = notifikasi_baca;
    }

    public int getNotifikasi_id() {
        return notifikasi_id;
    }

    public void setNotifikasi_id(int notifikasi_id) {
        this.notifikasi_id = notifikasi_id;
    }

    public String getNotifikasi_tanggal() {
        return notifikasi_tanggal;
    }

    public void setNotifikasi_tanggal(String notifikasi_tanggal) {
        this.notifikasi_tanggal = notifikasi_tanggal;
    }

    public String getNotifikasi_kategori() {
        return notifikasi_kategori;
    }

    public void setNotifikasi_kategori(String notifikasi_kategori) {
        this.notifikasi_kategori = notifikasi_kategori;
    }

    public String getNotifikasi_deskripsi() {
        return notifikasi_deskripsi;
    }

    public void setNotifikasi_deskripsi(String notifikasi_deskripsi) {
        this.notifikasi_deskripsi = notifikasi_deskripsi;
    }

    public String getNotifikasi_dari() {
        return notifikasi_dari;
    }

    public void setNotifikasi_dari(String notifikasi_dari) {
        this.notifikasi_dari = notifikasi_dari;
    }

    public String getNotifikasi_untuk() {
        return notifikasi_untuk;
    }

    public void setNotifikasi_untuk(String notifikasi_untuk) {
        this.notifikasi_untuk = notifikasi_untuk;
    }

    public int getNotifikasi_baca() {
        return notifikasi_baca;
    }

    public void setNotifikasi_baca(int notifikasi_baca) {
        this.notifikasi_baca = notifikasi_baca;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.notifikasi_id);
        dest.writeString(this.notifikasi_tanggal);
        dest.writeString(this.notifikasi_kategori);
        dest.writeString(this.notifikasi_deskripsi);
        dest.writeString(this.notifikasi_dari);
        dest.writeString(this.notifikasi_untuk);
        dest.writeInt(this.notifikasi_baca);
    }

    protected Notifikasi(Parcel in) {
        this.notifikasi_id = in.readInt();
        this.notifikasi_tanggal = in.readString();
        this.notifikasi_kategori = in.readString();
        this.notifikasi_deskripsi = in.readString();
        this.notifikasi_dari = in.readString();
        this.notifikasi_untuk = in.readString();
        this.notifikasi_baca = in.readInt();
    }

    public static final Creator<Notifikasi> CREATOR = new Creator<Notifikasi>() {
        @Override
        public Notifikasi createFromParcel(Parcel source) {
            return new Notifikasi(source);
        }

        @Override
        public Notifikasi[] newArray(int size) {
            return new Notifikasi[size];
        }
    };
}
