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
public class Bimbingan implements Parcelable {

    @Expose
    @SerializedName("bimbingan_id")
    private String bimbingan_id;

    @Expose
    @SerializedName("bimbingan_review")
    private String bimbingan_review;

    @Expose
    @SerializedName("bimbingan_kehadiran")
    private String bimbingan_kehadiran;

    @Expose
    @SerializedName("bimbingan_tanggal")
    private String bimbingan_tanggal;

    @Expose
    @SerializedName("bimbingan_status")
    private String bimbingan_status;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    @Expose
    @SerializedName("nilai_total")
    private int nilai_total;

    @Expose
    @SerializedName("nama_tim")
    private String nama_tim;

    @Expose
    @SerializedName("judul_id")
    private int judul_id;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("dsn_nip")
    private String dsn_nip;

    public Bimbingan(String bimbingan_id, String bimbingan_review, String bimbingan_kehadiran, String bimbingan_tanggal, String bimbingan_status, int proyek_akhir_id, int nilai_total, String nama_tim, int judul_id, String mhs_nim, String dsn_nip) {
        this.bimbingan_id = bimbingan_id;
        this.bimbingan_review = bimbingan_review;
        this.bimbingan_kehadiran = bimbingan_kehadiran;
        this.bimbingan_tanggal = bimbingan_tanggal;
        this.bimbingan_status = bimbingan_status;
        this.proyek_akhir_id = proyek_akhir_id;
        this.nilai_total = nilai_total;
        this.nama_tim = nama_tim;
        this.judul_id = judul_id;
        this.mhs_nim = mhs_nim;
        this.dsn_nip = dsn_nip;
    }

    public String getBimbingan_id() {
        return bimbingan_id;
    }

    public void setBimbingan_id(String bimbingan_id) {
        this.bimbingan_id = bimbingan_id;
    }

    public String getBimbingan_review() {
        return bimbingan_review;
    }

    public void setBimbingan_review(String bimbingan_review) {
        this.bimbingan_review = bimbingan_review;
    }

    public String getBimbingan_kehadiran() {
        return bimbingan_kehadiran;
    }

    public void setBimbingan_kehadiran(String bimbingan_kehadiran) {
        this.bimbingan_kehadiran = bimbingan_kehadiran;
    }

    public String getBimbingan_tanggal() {
        return bimbingan_tanggal;
    }

    public void setBimbingan_tanggal(String bimbingan_tanggal) {
        this.bimbingan_tanggal = bimbingan_tanggal;
    }

    public String getBimbingan_status() {
        return bimbingan_status;
    }

    public void setBimbingan_status(String bimbingan_status) {
        this.bimbingan_status = bimbingan_status;
    }

    public int getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(int proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }

    public int getNilai_total() {
        return nilai_total;
    }

    public void setNilai_total(int nilai_total) {
        this.nilai_total = nilai_total;
    }

    public String getNama_tim() {
        return nama_tim;
    }

    public void setNama_tim(String nama_tim) {
        this.nama_tim = nama_tim;
    }

    public int getJudul_id() {
        return judul_id;
    }

    public void setJudul_id(int judul_id) {
        this.judul_id = judul_id;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public String getDsn_nip() {
        return dsn_nip;
    }

    public void setDsn_nip(String dsn_nip) {
        this.dsn_nip = dsn_nip;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bimbingan_id);
        dest.writeString(this.bimbingan_review);
        dest.writeString(this.bimbingan_kehadiran);
        dest.writeString(this.bimbingan_tanggal);
        dest.writeString(this.bimbingan_status);
        dest.writeInt(this.proyek_akhir_id);
        dest.writeInt(this.nilai_total);
        dest.writeString(this.nama_tim);
        dest.writeInt(this.judul_id);
        dest.writeString(this.mhs_nim);
        dest.writeString(this.dsn_nip);
    }

    protected Bimbingan(Parcel in) {
        this.bimbingan_id = in.readString();
        this.bimbingan_review = in.readString();
        this.bimbingan_kehadiran = in.readString();
        this.bimbingan_tanggal = in.readString();
        this.bimbingan_status = in.readString();
        this.proyek_akhir_id = in.readInt();
        this.nilai_total = in.readInt();
        this.nama_tim = in.readString();
        this.judul_id = in.readInt();
        this.mhs_nim = in.readString();
        this.dsn_nip = in.readString();
    }

    public static final Creator<Bimbingan> CREATOR = new Creator<Bimbingan>() {
        @Override
        public Bimbingan createFromParcel(Parcel source) {
            return new Bimbingan(source);
        }

        @Override
        public Bimbingan[] newArray(int size) {
            return new Bimbingan[size];
        }
    };
}
