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
public class Informasi implements Parcelable {
    @Expose
    @SerializedName("id")
    private int id;

    @Expose
    @SerializedName("info_judul")
    private String info_judul;

    @Expose
    @SerializedName("info_deskripsi")
    private String info_deskripsi;

    @Expose
    @SerializedName("created_at")
    private String info_tanggal;

    @Expose
    @SerializedName("publisher")
    private String publisher;

    public Informasi(int id, String info_judul, String info_deskrips, String info_tanggal, String publisher) {
        this.id = id;
        this.info_judul = info_judul;
        this.info_deskripsi = info_deskrips;
        this.info_tanggal = info_tanggal;
        this.publisher = publisher;
    }

    protected Informasi(Parcel in) {
        id = in.readInt();
        info_judul = in.readString();
        info_deskripsi = in.readString();
        info_tanggal = in.readString();
        publisher = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(info_judul);
        dest.writeString(info_deskripsi);
        dest.writeString(info_tanggal);
        dest.writeString(publisher);
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

    public void setInfo_deskripsi(String info_deskrips) {
        this.info_deskripsi = info_deskrips;
    }

    public String getInfo_tanggal() {
        return info_tanggal;
    }

    public void setInfo_tanggal(String info_tanggal) {
        this.info_tanggal = info_tanggal;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
}
