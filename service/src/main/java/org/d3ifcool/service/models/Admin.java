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
public class Admin implements Parcelable {

    @Expose
    @SerializedName("username")
    String username;
    @Expose
    @SerializedName("password")
    String password;
    @Expose
    @SerializedName("nip_admin")
    String nip_admin;
    @Expose
    @SerializedName("nama_admin")
    String nama_admin;
    @Expose
    @SerializedName("no_telpon")
    String no_telpon;
    @Expose
    @SerializedName("email")
    String email;

    public Admin(String username, String password, String nip_admin, String nama_admin, String no_telpon, String email) {
        this.username = username;
        this.password = password;
        this.nip_admin = nip_admin;
        this.nama_admin = nama_admin;
        this.no_telpon = no_telpon;
        this.email = email;
    }

    protected Admin(Parcel in) {
        username = in.readString();
        password = in.readString();
        nip_admin = in.readString();
        nama_admin = in.readString();
        no_telpon = in.readString();
        email = in.readString();
    }

    public static final Creator<Admin> CREATOR = new Creator<Admin>() {
        @Override
        public Admin createFromParcel(Parcel in) {
            return new Admin(in);
        }

        @Override
        public Admin[] newArray(int size) {
            return new Admin[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNip_admin() {
        return nip_admin;
    }

    public void setNip_admin(String nip_admin) {
        this.nip_admin = nip_admin;
    }

    public String getNama_admin() {
        return nama_admin;
    }

    public void setNama_admin(String nama_admin) {
        this.nama_admin = nama_admin;
    }

    public String getNo_telpon() {
        return no_telpon;
    }

    public void setNo_telpon(String no_telpon) {
        this.no_telpon = no_telpon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(nip_admin);
        dest.writeString(nama_admin);
        dest.writeString(no_telpon);
        dest.writeString(email);
    }
}
