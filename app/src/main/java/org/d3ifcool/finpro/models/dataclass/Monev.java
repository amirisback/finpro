package org.d3ifcool.finpro.models.dataclass;

import android.os.Parcel;
import android.os.Parcelable;

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
public class Monev implements Parcelable {

    private int nilai;
    private String tanggal;
    private String komentar;

    public Monev(int nilai, String tanggal, String komentar) {
        this.nilai = nilai;
        this.tanggal = tanggal;
        this.komentar = komentar;
    }

    public int getNilai() {
        return nilai;
    }

    public void setNilai(int nilai) {
        this.nilai = nilai;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.nilai);
        dest.writeString(this.tanggal);
        dest.writeString(this.komentar);
    }

    protected Monev(Parcel in) {
        this.nilai = in.readInt();
        this.tanggal = in.readString();
        this.komentar = in.readString();
    }

    public static final Parcelable.Creator<Monev> CREATOR = new Parcelable.Creator<Monev>() {
        @Override
        public Monev createFromParcel(Parcel source) {
            return new Monev(source);
        }

        @Override
        public Monev[] newArray(int size) {
            return new Monev[size];
        }
    };
}
