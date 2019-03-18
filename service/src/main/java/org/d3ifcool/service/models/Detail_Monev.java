package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Detail_Monev implements Parcelable {
    @Expose
    @SerializedName("monev_nilai")
    private int monev_nilai;

    @Expose
    @SerializedName("monev_tanggal")
    private String monev_tanggal;

    @Expose
    @SerializedName("monev_ulasan")
    private String monev_ulasan;

    @Expose
    @SerializedName("monev_id")
    private int monev_id;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    public Detail_Monev(int monev_nilai, String monev_tanggal, String monev_ulasan, int monev_id, int proyek_akhir_id) {
        this.monev_nilai = monev_nilai;
        this.monev_tanggal = monev_tanggal;
        this.monev_ulasan = monev_ulasan;
        this.monev_id = monev_id;
        this.proyek_akhir_id = proyek_akhir_id;
    }

    protected Detail_Monev(Parcel in) {
        monev_nilai = in.readInt();
        monev_tanggal = in.readString();
        monev_ulasan = in.readString();
        monev_id = in.readInt();
        proyek_akhir_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(monev_nilai);
        dest.writeString(monev_tanggal);
        dest.writeString(monev_ulasan);
        dest.writeInt(monev_id);
        dest.writeInt(proyek_akhir_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Detail_Monev> CREATOR = new Creator<Detail_Monev>() {
        @Override
        public Detail_Monev createFromParcel(Parcel in) {
            return new Detail_Monev(in);
        }

        @Override
        public Detail_Monev[] newArray(int size) {
            return new Detail_Monev[size];
        }
    };

    public int getMonev_nilai() {
        return monev_nilai;
    }

    public void setMonev_nilai(int monev_nilai) {
        this.monev_nilai = monev_nilai;
    }

    public String getMonev_tanggal() {
        return monev_tanggal;
    }

    public void setMonev_tanggal(String monev_tanggal) {
        this.monev_tanggal = monev_tanggal;
    }

    public String getMonev_ulasan() {
        return monev_ulasan;
    }

    public void setMonev_ulasan(String monev_ulasan) {
        this.monev_ulasan = monev_ulasan;
    }

    public int getMonev_id() {
        return monev_id;
    }

    public void setMonev_id(int monev_id) {
        this.monev_id = monev_id;
    }

    public int getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(int proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }
}