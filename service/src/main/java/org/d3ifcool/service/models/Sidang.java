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
    @SerializedName("nilai_proposal")
    private double nilai_proposal;

    @Expose
    @SerializedName("nilai_penguji_1")
    private double nilai_penguji_1;

    @Expose
    @SerializedName("nilai_penguji_2")
    private double nilai_penguji_2;

    @Expose
    @SerializedName("nilai_pembimbing")
    private double nilai_pembimbing;


    @Expose
    @SerializedName("nilai_total")
    private double nilai_total;


    @Expose
    @SerializedName("sidang_status")
    private String sidang_status;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    public Sidang(String sidang_id, String sidang_review, String sidang_tanggal, double nilai_proposal, double nilai_penguji_1, double nilai_penguji_2, double nilai_pembimbing,  double nilai_total, String sidang_status, int proyek_akhir_id) {
        this.sidang_id = sidang_id;
        this.sidang_review = sidang_review;
        this.sidang_tanggal = sidang_tanggal;
        this.nilai_proposal = nilai_proposal;
        this.nilai_penguji_1 = nilai_penguji_1;
        this.nilai_penguji_2 = nilai_penguji_2;
        this.nilai_pembimbing = nilai_pembimbing;
        this.nilai_total = nilai_total;
        this.sidang_status = sidang_status;
        this.proyek_akhir_id = proyek_akhir_id;
    }

    protected Sidang(Parcel in) {
        sidang_id = in.readString();
        sidang_review = in.readString();
        sidang_tanggal = in.readString();
        nilai_proposal = in.readDouble();
        nilai_penguji_1 = in.readDouble();
        nilai_penguji_2 = in.readDouble();
        nilai_pembimbing = in.readDouble();
        nilai_total = in.readDouble();
        sidang_status = in.readString();
        proyek_akhir_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(sidang_id);
        dest.writeString(sidang_review);
        dest.writeString(sidang_tanggal);
        dest.writeDouble(nilai_proposal);
        dest.writeDouble(nilai_penguji_1);
        dest.writeDouble(nilai_penguji_2);
        dest.writeDouble(nilai_pembimbing);
        dest.writeDouble(nilai_total);
        dest.writeString(sidang_status);
        dest.writeInt(proyek_akhir_id);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public double getNilai_total() {
        return nilai_total;
    }

    public void setNilai_total(double nilai_total) {
        this.nilai_total = nilai_total;
    }

    public String getSidang_status() {
        return sidang_status;
    }

    public void setSidang_status(String sidang_status) {
        this.sidang_status = sidang_status;
    }

    public int getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(int proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }

    public double getNilai_proposal() {
        return nilai_proposal;
    }

    public void setNilai_proposal(double nilai_proposal) {
        this.nilai_proposal = nilai_proposal;
    }

    public double getNilai_penguji_1() {
        return nilai_penguji_1;
    }

    public void setNilai_penguji_1(double nilai_penguji_1) {
        this.nilai_penguji_1 = nilai_penguji_1;
    }

    public double getNilai_penguji_2() {
        return nilai_penguji_2;
    }

    public void setNilai_penguji_2(double nilai_penguji_2) {
        this.nilai_penguji_2 = nilai_penguji_2;
    }

    public double getNilai_pembimbing() {
        return nilai_pembimbing;
    }

    public void setNilai_pembimbing(double nilai_pembimbing) {
        this.nilai_pembimbing = nilai_pembimbing;
    }


}
