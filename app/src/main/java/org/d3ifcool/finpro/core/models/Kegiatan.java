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
 * Copyright (C) 23/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro.core.models
 */
public class Kegiatan implements Parcelable {

    @Expose
    @SerializedName("kegiatan_id")
    private int kegiatan_id;

    @Expose
    @SerializedName("kegiatan")
    private String kegiatan;

    @Expose
    @SerializedName("tanggal_mulai")
    private String tanggal_mulai;

    @Expose
    @SerializedName("tanggal_berakhir")
    private String tanggal_berakhir;

    @Expose
    @SerializedName("pelaku")
    private String pelaku;

    @Expose
    @SerializedName("keterangan")
    private String keterangan;

    public Kegiatan(int kegiatan_id, String kegiatan, String tanggal_mulai, String tanggal_berakhir, String pelaku, String keterangan) {
        this.kegiatan_id = kegiatan_id;
        this.kegiatan = kegiatan;
        this.tanggal_mulai = tanggal_mulai;
        this.tanggal_berakhir = tanggal_berakhir;
        this.pelaku = pelaku;
        this.keterangan = keterangan;
    }

    public int getKegiatan_id() {
        return kegiatan_id;
    }

    public void setKegiatan_id(int kegiatan_id) {
        this.kegiatan_id = kegiatan_id;
    }

    public String getKegiatan() {
        return kegiatan;
    }

    public void setKegiatan(String kegiatan) {
        this.kegiatan = kegiatan;
    }

    public String getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(String tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public String getTanggal_berakhir() {
        return tanggal_berakhir;
    }

    public void setTanggal_berakhir(String tanggal_berakhir) {
        this.tanggal_berakhir = tanggal_berakhir;
    }

    public String getPelaku() {
        return pelaku;
    }

    public void setPelaku(String pelaku) {
        this.pelaku = pelaku;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.kegiatan_id);
        dest.writeString(this.kegiatan);
        dest.writeString(this.tanggal_mulai);
        dest.writeString(this.tanggal_berakhir);
        dest.writeString(this.pelaku);
        dest.writeString(this.keterangan);
    }

    protected Kegiatan(Parcel in) {
        this.kegiatan_id = in.readInt();
        this.kegiatan = in.readString();
        this.tanggal_mulai = in.readString();
        this.tanggal_berakhir = in.readString();
        this.pelaku = in.readString();
        this.keterangan = in.readString();
    }

    public static final Parcelable.Creator<Kegiatan> CREATOR = new Parcelable.Creator<Kegiatan>() {
        @Override
        public Kegiatan createFromParcel(Parcel source) {
            return new Kegiatan(source);
        }

        @Override
        public Kegiatan[] newArray(int size) {
            return new Kegiatan[size];
        }
    };
}
