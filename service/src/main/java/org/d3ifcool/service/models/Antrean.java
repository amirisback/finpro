package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/1/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class Antrean implements Parcelable {
    @Expose
    @SerializedName("id_antrian")
    int id_antrian;

    @Expose
    @SerializedName("status_antrian")
    String status_antrian;

    @Expose
    @SerializedName("id_judul")
    int id_judul;

    @Expose
    @SerializedName("nim_mhs")
    String nim_mhs;

    public Antrean(int id_antrian, String status_antrian, int id_judul, String nim_mhs) {
        this.id_antrian = id_antrian;
        this.status_antrian = status_antrian;
        this.id_judul = id_judul;
        this.nim_mhs = nim_mhs;
    }

    protected Antrean(Parcel in) {
        id_antrian = in.readInt();
        status_antrian = in.readString();
        id_judul = in.readInt();
        nim_mhs = in.readString();
    }

    public static final Creator<Antrean> CREATOR = new Creator<Antrean>() {
        @Override
        public Antrean createFromParcel(Parcel in) {
            return new Antrean(in);
        }

        @Override
        public Antrean[] newArray(int size) {
            return new Antrean[size];
        }
    };

    public int getId_antrian() {
        return id_antrian;
    }

    public void setId_antrian(int id_antrian) {
        this.id_antrian = id_antrian;
    }

    public String getStatus_antrian() {
        return status_antrian;
    }

    public void setStatus_antrian(String status_antrian) {
        this.status_antrian = status_antrian;
    }

    public int getId_judul() {
        return id_judul;
    }

    public void setId_judul(int id_judul) {
        this.id_judul = id_judul;
    }

    public String getNim_mhs() {
        return nim_mhs;
    }

    public void setNim_mhs(String nim_mhs) {
        this.nim_mhs = nim_mhs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id_antrian);
        dest.writeString(status_antrian);
        dest.writeInt(id_judul);
        dest.writeString(nim_mhs);
    }
}
