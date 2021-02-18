package org.d3ifcool.finpro.core.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProyekAkhir implements Parcelable {

    @Expose
    @SerializedName("proyek_akhir_id")
    private int proyek_akhir_id;

    @Expose
    @SerializedName("nilai_total")
    private double nilai_total;

    @Expose
    @SerializedName("nama_tim")
    private String nama_tim;

    @Expose
    @SerializedName("mhs_nim")
    private String mhs_nim;

    @Expose
    @SerializedName("mhs_nama")
    private String mhs_nama;

    @Expose
    @SerializedName("mhs_angkatan")
    private String mhs_angkatan;

    @Expose
    @SerializedName("mhs_kontak")
    private String mhs_kontak;

    @Expose
    @SerializedName("mhs_foto")
    private String mhs_foto;

    @Expose
    @SerializedName("mhs_email")
    private String mhs_email;

    @Expose
    @SerializedName("mhs_status")
    private String mhs_status;

    @Expose
    @SerializedName("mhs_judul_id")
    private int mhs_judul_id;

    @Expose
    @SerializedName("mhs_username")
    private String mhs_username;

    @Expose
    @SerializedName("pembimbing_dsn_nip")
    private String pembimbing_dsn_nip;

    @Expose
    @SerializedName("judul_id")
    private int judul_id;

    @Expose
    @SerializedName("judul_nama")
    private String judul_nama;

    @Expose
    @SerializedName("judul_deskripsi")
    private String judul_deskripsi;

    @Expose
    @SerializedName("judul_status")
    private String judul_status;

    @Expose
    @SerializedName("judul_waktu")
    private String judul_waktu;

    @Expose
    @SerializedName("kategori_id")
    private String kategori_id;

    @Expose
    @SerializedName("reviewer_dsn_nip")
    private String reviewer_dsn_nip;

    @Expose
    @SerializedName("reviewer_dsn_nama")
    private String reviewer_dsn_nama;

    @Expose
    @SerializedName("reviewer_dsn_kode")
    private String reviewer_dsn_kode;

    @Expose
    @SerializedName("reviewer_dsn_kontak")
    private String reviewer_dsn_kontak;

    @Expose
    @SerializedName("reviewer_dsn_foto")
    private String reviewer_dsn_foto;

    @Expose
    @SerializedName("reviewer_dsn_email")
    private String reviewer_dsn_email;

    @Expose
    @SerializedName("reviewer_dsn_username")
    private String dsn_username;

    public ProyekAkhir(int proyek_akhir_id, double nilai_total, String nama_tim, String mhs_nim, String mhs_nama, String mhs_angkatan, String mhs_kontak, String mhs_foto, String mhs_email, String mhs_status, int mhs_judul_id, String mhs_username, String pembimbing_dsn_nip, int judul_id, String judul_nama, String judul_deskripsi, String judul_status, String judul_waktu, String kategori_id, String reviewer_dsn_nip, String reviewer_dsn_nama, String reviewer_dsn_kode, String reviewer_dsn_kontak, String reviewer_dsn_foto, String reviewer_dsn_email, String dsn_username) {
        this.proyek_akhir_id = proyek_akhir_id;
        this.nilai_total = nilai_total;
        this.nama_tim = nama_tim;
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.mhs_angkatan = mhs_angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.mhs_email = mhs_email;
        this.mhs_status = mhs_status;
        this.mhs_judul_id = mhs_judul_id;
        this.mhs_username = mhs_username;
        this.pembimbing_dsn_nip = pembimbing_dsn_nip;
        this.judul_id = judul_id;
        this.judul_nama = judul_nama;
        this.judul_deskripsi = judul_deskripsi;
        this.judul_status = judul_status;
        this.judul_waktu = judul_waktu;
        this.kategori_id = kategori_id;
        this.reviewer_dsn_nip = reviewer_dsn_nip;
        this.reviewer_dsn_nama = reviewer_dsn_nama;
        this.reviewer_dsn_kode = reviewer_dsn_kode;
        this.reviewer_dsn_kontak = reviewer_dsn_kontak;
        this.reviewer_dsn_foto = reviewer_dsn_foto;
        this.reviewer_dsn_email = reviewer_dsn_email;
        this.dsn_username = dsn_username;
    }

    public int getProyek_akhir_id() {
        return proyek_akhir_id;
    }

    public void setProyek_akhir_id(int proyek_akhir_id) {
        this.proyek_akhir_id = proyek_akhir_id;
    }

    public double getNilai_total() {
        return nilai_total;
    }

    public void setNilai_total(double nilai_total) {
        this.nilai_total = nilai_total;
    }

    public String getNama_tim() {
        return nama_tim;
    }

    public void setNama_tim(String nama_tim) {
        this.nama_tim = nama_tim;
    }

    public String getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(String mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public String getMhs_nama() {
        return mhs_nama;
    }

    public void setMhs_nama(String mhs_nama) {
        this.mhs_nama = mhs_nama;
    }

    public String getMhs_angkatan() {
        return mhs_angkatan;
    }

    public void setMhs_angkatan(String mhs_angkatan) {
        this.mhs_angkatan = mhs_angkatan;
    }

    public String getMhs_kontak() {
        return mhs_kontak;
    }

    public void setMhs_kontak(String mhs_kontak) {
        this.mhs_kontak = mhs_kontak;
    }

    public String getMhs_foto() {
        return mhs_foto;
    }

    public void setMhs_foto(String mhs_foto) {
        this.mhs_foto = mhs_foto;
    }

    public String getMhs_email() {
        return mhs_email;
    }

    public void setMhs_email(String mhs_email) {
        this.mhs_email = mhs_email;
    }

    public String getMhs_status() {
        return mhs_status;
    }

    public void setMhs_status(String mhs_status) {
        this.mhs_status = mhs_status;
    }

    public int getMhs_judul_id() {
        return mhs_judul_id;
    }

    public void setMhs_judul_id(int mhs_judul_id) {
        this.mhs_judul_id = mhs_judul_id;
    }

    public String getMhs_username() {
        return mhs_username;
    }

    public void setMhs_username(String mhs_username) {
        this.mhs_username = mhs_username;
    }

    public String getPembimbing_dsn_nip() {
        return pembimbing_dsn_nip;
    }

    public void setPembimbing_dsn_nip(String pembimbing_dsn_nip) {
        this.pembimbing_dsn_nip = pembimbing_dsn_nip;
    }

    public int getJudul_id() {
        return judul_id;
    }

    public void setJudul_id(int judul_id) {
        this.judul_id = judul_id;
    }

    public String getJudul_nama() {
        return judul_nama;
    }

    public void setJudul_nama(String judul_nama) {
        this.judul_nama = judul_nama;
    }

    public String getJudul_deskripsi() {
        return judul_deskripsi;
    }

    public void setJudul_deskripsi(String judul_deskripsi) {
        this.judul_deskripsi = judul_deskripsi;
    }

    public String getJudul_status() {
        return judul_status;
    }

    public void setJudul_status(String judul_status) {
        this.judul_status = judul_status;
    }

    public String getJudul_waktu() {
        return judul_waktu;
    }

    public void setJudul_waktu(String judul_waktu) {
        this.judul_waktu = judul_waktu;
    }

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }

    public String getReviewer_dsn_nip() {
        return reviewer_dsn_nip;
    }

    public void setReviewer_dsn_nip(String reviewer_dsn_nip) {
        this.reviewer_dsn_nip = reviewer_dsn_nip;
    }

    public String getReviewer_dsn_nama() {
        return reviewer_dsn_nama;
    }

    public void setReviewer_dsn_nama(String reviewer_dsn_nama) {
        this.reviewer_dsn_nama = reviewer_dsn_nama;
    }

    public String getReviewer_dsn_kode() {
        return reviewer_dsn_kode;
    }

    public void setReviewer_dsn_kode(String reviewer_dsn_kode) {
        this.reviewer_dsn_kode = reviewer_dsn_kode;
    }

    public String getReviewer_dsn_kontak() {
        return reviewer_dsn_kontak;
    }

    public void setReviewer_dsn_kontak(String reviewer_dsn_kontak) {
        this.reviewer_dsn_kontak = reviewer_dsn_kontak;
    }

    public String getReviewer_dsn_foto() {
        return reviewer_dsn_foto;
    }

    public void setReviewer_dsn_foto(String reviewer_dsn_foto) {
        this.reviewer_dsn_foto = reviewer_dsn_foto;
    }

    public String getReviewer_dsn_email() {
        return reviewer_dsn_email;
    }

    public void setReviewer_dsn_email(String reviewer_dsn_email) {
        this.reviewer_dsn_email = reviewer_dsn_email;
    }

    public String getDsn_username() {
        return dsn_username;
    }

    public void setDsn_username(String dsn_username) {
        this.dsn_username = dsn_username;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.proyek_akhir_id);
        dest.writeDouble(this.nilai_total);
        dest.writeString(this.nama_tim);
        dest.writeString(this.mhs_nim);
        dest.writeString(this.mhs_nama);
        dest.writeString(this.mhs_angkatan);
        dest.writeString(this.mhs_kontak);
        dest.writeString(this.mhs_foto);
        dest.writeString(this.mhs_email);
        dest.writeString(this.mhs_status);
        dest.writeInt(this.mhs_judul_id);
        dest.writeString(this.mhs_username);
        dest.writeString(this.pembimbing_dsn_nip);
        dest.writeInt(this.judul_id);
        dest.writeString(this.judul_nama);
        dest.writeString(this.judul_deskripsi);
        dest.writeString(this.judul_status);
        dest.writeString(this.judul_waktu);
        dest.writeString(this.kategori_id);
        dest.writeString(this.reviewer_dsn_nip);
        dest.writeString(this.reviewer_dsn_nama);
        dest.writeString(this.reviewer_dsn_kode);
        dest.writeString(this.reviewer_dsn_kontak);
        dest.writeString(this.reviewer_dsn_foto);
        dest.writeString(this.reviewer_dsn_email);
        dest.writeString(this.dsn_username);
    }

    protected ProyekAkhir(Parcel in) {
        this.proyek_akhir_id = in.readInt();
        this.nilai_total = in.readDouble();
        this.nama_tim = in.readString();
        this.mhs_nim = in.readString();
        this.mhs_nama = in.readString();
        this.mhs_angkatan = in.readString();
        this.mhs_kontak = in.readString();
        this.mhs_foto = in.readString();
        this.mhs_email = in.readString();
        this.mhs_status = in.readString();
        this.mhs_judul_id = in.readInt();
        this.mhs_username = in.readString();
        this.pembimbing_dsn_nip = in.readString();
        this.judul_id = in.readInt();
        this.judul_nama = in.readString();
        this.judul_deskripsi = in.readString();
        this.judul_status = in.readString();
        this.judul_waktu = in.readString();
        this.kategori_id = in.readString();
        this.reviewer_dsn_nip = in.readString();
        this.reviewer_dsn_nama = in.readString();
        this.reviewer_dsn_kode = in.readString();
        this.reviewer_dsn_kontak = in.readString();
        this.reviewer_dsn_foto = in.readString();
        this.reviewer_dsn_email = in.readString();
        this.dsn_username = in.readString();
    }

    public static final Creator<ProyekAkhir> CREATOR = new Creator<ProyekAkhir>() {
        @Override
        public ProyekAkhir createFromParcel(Parcel source) {
            return new ProyekAkhir(source);
        }

        @Override
        public ProyekAkhir[] newArray(int size) {
            return new ProyekAkhir[size];
        }
    };
}