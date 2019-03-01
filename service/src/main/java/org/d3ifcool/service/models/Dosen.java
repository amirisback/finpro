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
 * Copyright (C) 24/12/2018.
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
public class Dosen implements Parcelable {
    @Expose
    @SerializedName("nip_dosen")
    String nip_dosen;

    @Expose
    @SerializedName("nama_d")
    String nama_d;


    @Expose
    @SerializedName("foto")
    String foto;

    @Expose
    @SerializedName("email")
    String email;

    @Expose
    @SerializedName("kontak_d")
    String no_telefon;

    @Expose
    @SerializedName("limit")
    int limit;

    @Expose
    @SerializedName("username_dosen")
    String username;

    public Dosen(String nip_dosen, String nama_d, String foto, String email, String no_telefon, int limit, String username) {
        this.nip_dosen = nip_dosen;
        this.nama_d = nama_d;
        this.foto = foto;
        this.email = email;
        this.no_telefon = no_telefon;
        this.limit = limit;
        this.username = username;
    }

    public Dosen() {
    }

    protected Dosen(Parcel in) {
        nip_dosen = in.readString();
        nama_d = in.readString();
        foto = in.readString();
        email = in.readString();
        no_telefon = in.readString();
        limit = in.readInt();
        username = in.readString();
    }

    public static final Creator<Dosen> CREATOR = new Creator<Dosen>() {
        @Override
        public Dosen createFromParcel(Parcel in) {
            return new Dosen(in);
        }

        @Override
        public Dosen[] newArray(int size) {
            return new Dosen[size];
        }
    };

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    public String getNama_d() {
        return nama_d;
    }

    public void setNama_d(String nama_d) {
        this.nama_d = nama_d;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_telefon() {
        return no_telefon;
    }

    public void setNo_telefon(String no_telefon) {
        this.no_telefon = no_telefon;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
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
        dest.writeString(this.nip_dosen);
        dest.writeString(this.nama_d);
        dest.writeString(this.foto);
        dest.writeString(this.email);
        dest.writeString(this.no_telefon);
        dest.writeInt(this.limit);
        dest.writeString(this.username);
    }
}
