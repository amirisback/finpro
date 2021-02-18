package org.d3ifcool.finpro.core.models;

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
    private String koor_nip;

    @Expose
    @SerializedName("koor_nama")
    private String koor_nama;

    @Expose
    @SerializedName("koor_kode")
    private String koor_kode;

    @Expose
    @SerializedName("koor_kontak")
    private String koor_kontak;

    @Expose
    @SerializedName("koor_foto")
    private String koor_foto;

    @Expose
    @SerializedName("koor_email")
    private String koor_email;

    @Expose
    @SerializedName("username")
    private String username;

    public KoordinatorPa(String koor_nip, String koor_nama, String koor_kode, String koor_kontak, String koor_foto, String koor_email, String username) {
        this.koor_nip = koor_nip;
        this.koor_nama = koor_nama;
        this.koor_kode = koor_kode;
        this.koor_kontak = koor_kontak;
        this.koor_foto = koor_foto;
        this.koor_email = koor_email;
        this.username = username;
    }

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

    public String getKoor_kode() {
        return koor_kode;
    }

    public void setKoor_kode(String koor_kode) {
        this.koor_kode = koor_kode;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.koor_nip);
        dest.writeString(this.koor_nama);
        dest.writeString(this.koor_kode);
        dest.writeString(this.koor_kontak);
        dest.writeString(this.koor_foto);
        dest.writeString(this.koor_email);
        dest.writeString(this.username);
    }

    protected KoordinatorPa(Parcel in) {
        this.koor_nip = in.readString();
        this.koor_nama = in.readString();
        this.koor_kode = in.readString();
        this.koor_kontak = in.readString();
        this.koor_foto = in.readString();
        this.koor_email = in.readString();
        this.username = in.readString();
    }

    public static final Creator<KoordinatorPa> CREATOR = new Creator<KoordinatorPa>() {
        @Override
        public KoordinatorPa createFromParcel(Parcel source) {
            return new KoordinatorPa(source);
        }

        @Override
        public KoordinatorPa[] newArray(int size) {
            return new KoordinatorPa[size];
        }
    };
}
