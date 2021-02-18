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
    private int nilai_proposal;

    @Expose
    @SerializedName("nilai_penguji_1")
    private int nilai_penguji_1;

    @Expose
    @SerializedName("nilai_penguji_2")
    private int nilai_penguji_2;

    @Expose
    @SerializedName("nilai_pembimbing")
    private int nilai_pembimbing;

    @Expose
    @SerializedName("nilai_total")
    private double nilai_total;


    @Expose
    @SerializedName("sidang_status")
    private String sidang_status;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    @Expose
    @SerializedName("nama_tim")
    private String nama_tim;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("judul_id")
    private int judul_id;

    public Sidang(String sidang_id, String sidang_review, String sidang_tanggal, int nilai_proposal, int nilai_penguji_1, int nilai_penguji_2, int nilai_pembimbing, double nilai_total, String sidang_status, int proyek_akhir_id, String nama_tim, String mhs_nim, int judul_id) {
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
        this.nama_tim = nama_tim;
        this.mhs_nim = mhs_nim;
        this.judul_id = judul_id;
    }

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

    public int getNilai_proposal() {
        return nilai_proposal;
    }

    public void setNilai_proposal(int nilai_proposal) {
        this.nilai_proposal = nilai_proposal;
    }

    public int getNilai_penguji_1() {
        return nilai_penguji_1;
    }

    public void setNilai_penguji_1(int nilai_penguji_1) {
        this.nilai_penguji_1 = nilai_penguji_1;
    }

    public int getNilai_penguji_2() {
        return nilai_penguji_2;
    }

    public void setNilai_penguji_2(int nilai_penguji_2) {
        this.nilai_penguji_2 = nilai_penguji_2;
    }

    public int getNilai_pembimbing() {
        return nilai_pembimbing;
    }

    public void setNilai_pembimbing(int nilai_pembimbing) {
        this.nilai_pembimbing = nilai_pembimbing;
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

    public String getNama_tim() {
        return nama_tim;
    }

    public void setNama_tim(String nama_tim) {
        this.nama_tim = nama_tim;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public int getJudul_id() {
        return judul_id;
    }

    public void setJudul_id(int judul_id) {
        this.judul_id = judul_id;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.sidang_id);
        dest.writeString(this.sidang_review);
        dest.writeString(this.sidang_tanggal);
        dest.writeInt(this.nilai_proposal);
        dest.writeInt(this.nilai_penguji_1);
        dest.writeInt(this.nilai_penguji_2);
        dest.writeInt(this.nilai_pembimbing);
        dest.writeDouble(this.nilai_total);
        dest.writeString(this.sidang_status);
        dest.writeInt(this.proyek_akhir_id);
        dest.writeString(this.nama_tim);
        dest.writeString(this.mhs_nim);
        dest.writeInt(this.judul_id);
    }

    protected Sidang(Parcel in) {
        this.sidang_id = in.readString();
        this.sidang_review = in.readString();
        this.sidang_tanggal = in.readString();
        this.nilai_proposal = in.readInt();
        this.nilai_penguji_1 = in.readInt();
        this.nilai_penguji_2 = in.readInt();
        this.nilai_pembimbing = in.readInt();
        this.nilai_total = in.readDouble();
        this.sidang_status = in.readString();
        this.proyek_akhir_id = in.readInt();
        this.nama_tim = in.readString();
        this.mhs_nim = in.readString();
        this.judul_id = in.readInt();
    }

    public static final Creator<Sidang> CREATOR = new Creator<Sidang>() {
        @Override
        public Sidang createFromParcel(Parcel source) {
            return new Sidang(source);
        }

        @Override
        public Sidang[] newArray(int size) {
            return new Sidang[size];
        }
    };
}
