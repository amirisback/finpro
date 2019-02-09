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
public class Informasi implements Parcelable {

    private String info_id;
    private String info_judul;
    private String info_isi;
    private String info_tanggal;
    private String info_dosen;

    public Informasi(String info_id, String info_judul, String info_isi, String info_tanggal, String info_dosen) {
        this.info_id = info_id;
        this.info_judul = info_judul;
        this.info_isi = info_isi;
        this.info_tanggal = info_tanggal;
        this.info_dosen = info_dosen;
    }

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getInfo_judul() {
        return info_judul;
    }

    public void setInfo_judul(String info_judul) {
        this.info_judul = info_judul;
    }

    public String getInfo_isi() {
        return info_isi;
    }

    public void setInfo_isi(String info_isi) {
        this.info_isi = info_isi;
    }

    public String getInfo_tanggal() {
        return info_tanggal;
    }

    public void setInfo_tanggal(String info_tanggal) {
        this.info_tanggal = info_tanggal;
    }

    public String getInfo_dosen() {
        return info_dosen;
    }

    public void setInfo_dosen(String info_dosen) {
        this.info_dosen = info_dosen;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.info_id);
        dest.writeString(this.info_judul);
        dest.writeString(this.info_isi);
        dest.writeString(this.info_tanggal);
        dest.writeString(this.info_dosen);
    }

    protected Informasi(Parcel in) {
        this.info_id = in.readString();
        this.info_judul = in.readString();
        this.info_isi = in.readString();
        this.info_tanggal = in.readString();
        this.info_dosen = in.readString();
    }

    public static final Parcelable.Creator<Informasi> CREATOR = new Parcelable.Creator<Informasi>() {
        @Override
        public Informasi createFromParcel(Parcel source) {
            return new Informasi(source);
        }

        @Override
        public Informasi[] newArray(int size) {
            return new Informasi[size];
        }
    };
}
