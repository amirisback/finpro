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
 * Copyright (C) 03/03/2019.
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
public class Login implements Parcelable {

    @Expose
    @SerializedName("username")
    private int username;

    @Expose
    @SerializedName("password")
    private int password;

    @Expose
    @SerializedName("pengguna")
    private int pengguna;

    @Expose
    @SerializedName("success")
    private Boolean success;

    @Expose
    @SerializedName("message")
    private String message;

    public Login(int username, int password, int pengguna, Boolean success, String message) {
        this.username = username;
        this.password = password;
        this.pengguna = pengguna;
        this.success = success;
        this.message = message;
    }

    public int getUsername() {
        return username;
    }

    public void setUsername(int username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getPengguna() {
        return pengguna;
    }

    public void setPengguna(int pengguna) {
        this.pengguna = pengguna;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.username);
        dest.writeInt(this.password);
        dest.writeInt(this.pengguna);
        dest.writeValue(this.success);
        dest.writeString(this.message);
    }

    protected Login(Parcel in) {
        this.username = in.readInt();
        this.password = in.readInt();
        this.pengguna = in.readInt();
        this.success = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.message = in.readString();
    }

    public static final Creator<Login> CREATOR = new Creator<Login>() {
        @Override
        public Login createFromParcel(Parcel source) {
            return new Login(source);
        }

        @Override
        public Login[] newArray(int size) {
            return new Login[size];
        }
    };
}
