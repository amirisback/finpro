package org.d3ifcool.finpro.models.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 04/02/2019.
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
public class JudulPaDosen implements Parcelable {

    private String judul;
    private String kategori;
    private String jumlah;

    public JudulPaDosen(String judul, String kategori, String jumlah) {
        this.judul = judul;
        this.kategori = kategori;
        this.jumlah = jumlah;
    }

    public String getJudul() {
        return judul;
    }

    public String getKategori() {
        return kategori;
    }

    public String getJumlah() {
        return jumlah;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.judul);
        dest.writeString(this.kategori);
        dest.writeString(this.jumlah);
    }

    protected JudulPaDosen(Parcel in) {
        this.judul = in.readString();
        this.kategori = in.readString();
        this.jumlah = in.readString();
    }

    public static final Parcelable.Creator<JudulPaDosen> CREATOR = new Parcelable.Creator<JudulPaDosen>() {
        @Override
        public JudulPaDosen createFromParcel(Parcel source) {
            return new JudulPaDosen(source);
        }

        @Override
        public JudulPaDosen[] newArray(int size) {
            return new JudulPaDosen[size];
        }
    };
}
