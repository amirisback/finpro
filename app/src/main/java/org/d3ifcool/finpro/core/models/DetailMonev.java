package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailMonev implements Parcelable {

    @Expose
    @SerializedName("monev_detail_id")
    private int monev_detail_id;

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

    @Expose
    @SerializedName("nilai_total")
    private int nilai_total;

    @Expose
    @SerializedName("nama_tim")
    private String nama_tim;

    @Expose
    @SerializedName("judul_id")
    private String judul_id;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("dsn_nip")
    private String dsn_nip;

    @Expose
    @SerializedName("monev_kategori")
    private String monev_kategori;

    public DetailMonev(int monev_detail_id, int monev_nilai, String monev_tanggal, String monev_ulasan, int monev_id, int proyek_akhir_id, int nilai_total, String nama_tim, String judul_id, String mhs_nim, String dsn_nip, String monev_kategori) {
        this.monev_detail_id = monev_detail_id;
        this.monev_nilai = monev_nilai;
        this.monev_tanggal = monev_tanggal;
        this.monev_ulasan = monev_ulasan;
        this.monev_id = monev_id;
        this.proyek_akhir_id = proyek_akhir_id;
        this.nilai_total = nilai_total;
        this.nama_tim = nama_tim;
        this.judul_id = judul_id;
        this.mhs_nim = mhs_nim;
        this.dsn_nip = dsn_nip;
        this.monev_kategori = monev_kategori;
    }

    public int getMonev_detail_id() {
        return monev_detail_id;
    }

    public void setMonev_detail_id(int monev_detail_id) {
        this.monev_detail_id = monev_detail_id;
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

    public int getNilai_total() {
        return nilai_total;
    }

    public void setNilai_total(int nilai_total) {
        this.nilai_total = nilai_total;
    }

    public String getNama_tim() {
        return nama_tim;
    }

    public void setNama_tim(String nama_tim) {
        this.nama_tim = nama_tim;
    }

    public String getJudul_id() {
        return judul_id;
    }

    public void setJudul_id(String judul_id) {
        this.judul_id = judul_id;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public String getDsn_nip() {
        return dsn_nip;
    }

    public void setDsn_nip(String dsn_nip) {
        this.dsn_nip = dsn_nip;
    }

    public String getMonev_kategori() {
        return monev_kategori;
    }

    public void setMonev_kategori(String monev_kategori) {
        this.monev_kategori = monev_kategori;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.monev_detail_id);
        dest.writeInt(this.monev_nilai);
        dest.writeString(this.monev_tanggal);
        dest.writeString(this.monev_ulasan);
        dest.writeInt(this.monev_id);
        dest.writeInt(this.proyek_akhir_id);
        dest.writeInt(this.nilai_total);
        dest.writeString(this.nama_tim);
        dest.writeString(this.judul_id);
        dest.writeString(this.mhs_nim);
        dest.writeString(this.dsn_nip);
        dest.writeString(this.monev_kategori);
    }

    protected DetailMonev(Parcel in) {
        this.monev_detail_id = in.readInt();
        this.monev_nilai = in.readInt();
        this.monev_tanggal = in.readString();
        this.monev_ulasan = in.readString();
        this.monev_id = in.readInt();
        this.proyek_akhir_id = in.readInt();
        this.nilai_total = in.readInt();
        this.nama_tim = in.readString();
        this.judul_id = in.readString();
        this.mhs_nim = in.readString();
        this.dsn_nip = in.readString();
        this.monev_kategori = in.readString();
    }

    public static final Creator<DetailMonev> CREATOR = new Creator<DetailMonev>() {
        @Override
        public DetailMonev createFromParcel(Parcel source) {
            return new DetailMonev(source);
        }

        @Override
        public DetailMonev[] newArray(int size) {
            return new DetailMonev[size];
        }
    };
}