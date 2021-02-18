package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KategoriJudul implements Parcelable {

    @Expose
    @SerializedName("kategori_id")
    private int id;

    @Expose
    @SerializedName("kategori_nama")
    private String kategori_nama;

    public KategoriJudul(int id, String kategori_nama) {
        this.id = id;
        this.kategori_nama = kategori_nama;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKategori_nama() {
        return kategori_nama;
    }

    public void setKategori_nama(String kategori_nama) {
        this.kategori_nama = kategori_nama;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.kategori_nama);
    }

    protected KategoriJudul(Parcel in) {
        this.id = in.readInt();
        this.kategori_nama = in.readString();
    }

    public static final Creator<KategoriJudul> CREATOR = new Creator<KategoriJudul>() {
        @Override
        public KategoriJudul createFromParcel(Parcel source) {
            return new KategoriJudul(source);
        }

        @Override
        public KategoriJudul[] newArray(int size) {
            return new KategoriJudul[size];
        }
    };
}
