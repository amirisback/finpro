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
public class Sidang implements Parcelable {

    @Expose
    @SerializedName("sidang_id")
    private String sidang_id;

    @Expose
    @SerializedName("sidang_review")
    private String sidang_review;

    @Expose
    @SerializedName("sidang_tanggal")
    private String sidang_tanggal;

    @Expose
    @SerializedName("sidang_nilai")
    private String sidang_nilai;

    @Expose
    @SerializedName("sidang_status")
    private String sidang_status;

    @Expose
    @SerializedName("proyek_akhir_id")
    private String proyek_akhir_id;


    public Sidang(String sidang_id, String sidang_review, String sidang_tanggal, String sidang_nilai, String sidang_status) {
        this.sidang_id = sidang_id;
        this.sidang_review = sidang_review;
        this.sidang_tanggal = sidang_tanggal;
        this.sidang_nilai = sidang_nilai;
        this.sidang_status = sidang_status;
    }

    protected Sidang(Parcel in) {
        sidang_id = in.readString();
        sidang_review = in.readString();
        sidang_tanggal = in.readString();
        sidang_nilai = in.readString();
        sidang_status = in.readString();
        proyek_akhir_id = in.readString();
    }

    public static final Creator<Sidang> CREATOR = new Creator<Sidang>() {
        @Override
        public Sidang createFromParcel(Parcel in) {
            return new Sidang(in);
        }

        @Override
        public Sidang[] newArray(int size) {
            return new Sidang[size];
        }
    };

    public String getSidang_id() {
        return sidang_id;
    }

    public void setSidang_id(String sidang_id) {
        this.sidang_id = sidang_id;
    }

    public String getSidang_review() {
        return sidang_review;
    }

    public void setSidang_review(String sidang_review) {
        this.sidang_review = sidang_review;
    }

    public String getSidang_tanggal() {
        return sidang_tanggal;
    }

    public void setSidang_tanggal(String sidang_tanggal) {
        this.sidang_tanggal = sidang_tanggal;
    }

    public String getSidang_nilai() {
        return sidang_nilai;
    }

    public void setSidang_nilai(String sidang_nilai) {
        this.sidang_nilai = sidang_nilai;
    }

    public String getSidang_status() {
        return sidang_status;
    }

    public void setSidang_status(String sidang_status) {
        this.sidang_status = sidang_status;
    }

    public String getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(String proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sidang_id);
        dest.writeString(sidang_review);
        dest.writeString(sidang_tanggal);
        dest.writeString(sidang_nilai);
        dest.writeString(sidang_status);
        dest.writeString(proyek_akhir_id);
    }
}
