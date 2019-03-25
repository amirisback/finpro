package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Kategori implements Parcelable {
    String kategori;

    protected Kategori(Parcel in) {
        kategori = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(kategori);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Kategori> CREATOR = new Creator<Kategori>() {
        @Override
        public Kategori createFromParcel(Parcel in) {
            return new Kategori(in);
        }

        @Override
        public Kategori[] newArray(int size) {
            return new Kategori[size];
        }
    };

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Kategori(String kategori) {
        this.kategori = kategori;
    }

}
