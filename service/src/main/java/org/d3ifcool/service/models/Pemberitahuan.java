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
public class Pemberitahuan implements Parcelable {

    @Expose
    @SerializedName("deskripsi")
    String isi;

    @Expose
    @SerializedName("created_at")
    String tanggal;

    @Expose
    @SerializedName("deskripsi")
    String waktu;

    @Expose
    @SerializedName("dari")
    String dari;

    @Expose
    @SerializedName("untuk")
    String untuk;

    @Expose
    @SerializedName("info_kategori")
    String info_kategori;

    @Expose
    @SerializedName("status")
    String status;


    public Pemberitahuan(String isi, String tanggal, String waktu, String dari, String untuk, String info_kategori, String status) {
        this.isi = isi;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.dari = dari;
        this.untuk = untuk;
        this.info_kategori = info_kategori;
        this.status = status;
    }

    protected Pemberitahuan(Parcel in) {
        isi = in.readString();
        tanggal = in.readString();
        waktu = in.readString();
        dari = in.readString();
        untuk = in.readString();
        info_kategori = in.readString();
        status = in.readString();
    }

    public static final Creator<Pemberitahuan> CREATOR = new Creator<Pemberitahuan>() {
        @Override
        public Pemberitahuan createFromParcel(Parcel in) {
            return new Pemberitahuan(in);
        }

        @Override
        public Pemberitahuan[] newArray(int size) {
            return new Pemberitahuan[size];
        }
    };

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(isi);
        dest.writeString(tanggal);
        dest.writeString(waktu);
        dest.writeString(dari);
        dest.writeString(untuk);
        dest.writeString(info_kategori);
        dest.writeString(status);
    }
}
