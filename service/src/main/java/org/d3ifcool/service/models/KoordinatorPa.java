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
public class KoordinatorPa implements Parcelable {

    @Expose
    @SerializedName("koor_nip")
    String koor_nip;
    @Expose
    @SerializedName("koor_nama")
    String koor_nama;
    @Expose
    @SerializedName("koor_kode")
    String koor_kode;
    @Expose
    @SerializedName("koor_kontak")
    String koor_kontak;
    @Expose
    @SerializedName("koor_foto")
    String koor_foto;
    @Expose
    @SerializedName("koor_email")
    String koor_email;
    @Expose
    @SerializedName("created_at")
    String created_at;
    @Expose
    @SerializedName("update_at")
    String update_at;


    public KoordinatorPa(String koor_nip, String koor_nama, String koor_kontak, String koor_foto, String koor_email) {
        this.koor_nip = koor_nip;
        this.koor_nama = koor_nama;
        this.koor_kontak = koor_kontak;
        this.koor_foto = koor_foto;
        this.koor_email = koor_email;
    }

    protected KoordinatorPa(Parcel in) {
        koor_nip = in.readString();
        koor_nama = in.readString();
        koor_kontak = in.readString();
        koor_foto = in.readString();
        koor_email = in.readString();
        created_at = in.readString();
        update_at = in.readString();
    }

    public static final Creator<KoordinatorPa> CREATOR = new Creator<KoordinatorPa>() {
        @Override
        public KoordinatorPa createFromParcel(Parcel in) {
            return new KoordinatorPa(in);
        }

        @Override
        public KoordinatorPa[] newArray(int size) {
            return new KoordinatorPa[size];
        }
    };

    public String getKoor_nip() {
        return koor_nip;
    }

    public void setKoor_nip(String koor_nip) {
        this.koor_nip = koor_nip;
    }

    public String getKoor_nama() {
        return koor_nama;
    }

    public void setKoor_nama(String koor_nama) {
        this.koor_nama = koor_nama;
    }

    public String getKoor_kontak() {
        return koor_kontak;
    }

    public void setKoor_kontak(String koor_kontak) {
        this.koor_kontak = koor_kontak;
    }

    public String getKoor_foto() {
        return koor_foto;
    }

    public void setKoor_foto(String koor_foto) {
        this.koor_foto = koor_foto;
    }

    public String getKoor_email() {
        return koor_email;
    }

    public void setKoor_email(String koor_email) {
        this.koor_email = koor_email;
    }

    public String getKoor_kode() {
        return koor_kode;
    }

    public void setKoor_kode(String koor_kode) {
        this.koor_kode = koor_kode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(koor_nip);
        dest.writeString(koor_nama);
        dest.writeString(koor_kontak);
        dest.writeString(koor_foto);
        dest.writeString(koor_email);
        dest.writeString(created_at);
        dest.writeString(update_at);
    }
}
