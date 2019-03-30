package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailMonev implements Parcelable {
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
    @SerializedName("monev_reviewer")
    private String monev_reviewer;

    @Expose
    @SerializedName("monev_id")
    private int monev_id;

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    public DetailMonev(int monev_nilai, String monev_tanggal, String monev_ulasan, String monev_reviewer, int monev_id, int proyek_akhir_id) {
        this.monev_nilai = monev_nilai;
        this.monev_tanggal = monev_tanggal;
        this.monev_ulasan = monev_ulasan;
        this.monev_reviewer = monev_reviewer;
        this.monev_id = monev_id;
        this.proyek_akhir_id = proyek_akhir_id;
    }


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

    public String getMonev_reviewer() {
        return monev_reviewer;
    }

    public void setMonev_reviewer(String monev_reviewer) {
        this.monev_reviewer = monev_reviewer;
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

    protected DetailMonev(Parcel in) {
        monev_nilai = in.readInt();
        monev_tanggal = in.readString();
        monev_ulasan = in.readString();
        monev_reviewer = in.readString();
        monev_id = in.readInt();
        proyek_akhir_id = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(monev_nilai);
        dest.writeString(monev_tanggal);
        dest.writeString(monev_ulasan);
        dest.writeString(monev_reviewer);
        dest.writeInt(monev_id);
        dest.writeInt(proyek_akhir_id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DetailMonev> CREATOR = new Creator<DetailMonev>() {
        @Override
        public DetailMonev createFromParcel(Parcel in) {
            return new DetailMonev(in);
        }

        @Override
        public DetailMonev[] newArray(int size) {
            return new DetailMonev[size];
        }
    };
}