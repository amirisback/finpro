package org.d3ifcool.service.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProyekAkhir implements Parcelable{


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
    @SerializedName("mhs_nim")
    private int mhs_nim;

    @Expose
    @SerializedName("mhs_nama")
    private String mhs_nama;

    @Expose
    @SerializedName("mhs_angkatan")
    private String angkatan;

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
    private String status;

    @Expose
    @SerializedName("mhs_username")
    private String mhs_username;

    @Expose
    @SerializedName("dsn_nip_pembimbing")
    private String nip_dosen;

    @Expose
    @SerializedName("mhs_judul_id")
    private int mhs_judul_id;

    @Expose
    @SerializedName("judul_id")
    private int judul_id;

    @Expose
    @SerializedName("judul_nama")
    private String judul;

    @Expose
    @SerializedName("judul_deskripsi")
    private String deskripsi;

    @Expose
    @SerializedName("judul_status")
    private String tersedia;

    @Expose
    @SerializedName("kategori_id")
    private String kategori_id;

    @Expose
    @SerializedName("reviewer_dsn_nip")
    private int dsn_nip;

    @Expose
    @SerializedName("reviewer_dsn_nama")
    private String dsn_nama;

    @Expose
    @SerializedName("reviewer_dsn_kode")
    private String dsn_kode;

    @Expose
    @SerializedName("reviewer_dsn_kontak")
    private String dsn_kontak;

    @Expose
    @SerializedName("reviewer_dsn_foto")
    private String dsn_foto;

    @Expose
    @SerializedName("reviewer_dsn_email")
    private String dsn_email;

    @Expose
    @SerializedName("reviewer_dsn_status")
    private String dsn_status;

    @Expose
    @SerializedName("reviewer_dsn_username")
    private String dsn_username;

    public ProyekAkhir(String nama_tim, String judul) {
        this.nama_tim = nama_tim;
        this.judul = judul;
    }

    public ProyekAkhir(int proyek_akhir_id, int nilai_total, String nama_tim, int mhs_nim, String mhs_nama, String angkatan, String mhs_kontak, String mhs_foto, String mhs_email, String status, String mhs_username, String nip_dosen, int mhs_judul_id, int judul_id, String judul, String deskripsi, String tersedia, String kategori_id, int dsn_nip, String dsn_nama, String dsn_kode, String dsn_kontak, String dsn_foto, String dsn_email, String dsn_status, String dsn_username) {
        this.proyek_akhir_id = proyek_akhir_id;
        this.nilai_total = nilai_total;
        this.nama_tim = nama_tim;
        this.mhs_nim = mhs_nim;
        this.mhs_nama = mhs_nama;
        this.angkatan = angkatan;
        this.mhs_kontak = mhs_kontak;
        this.mhs_foto = mhs_foto;
        this.mhs_email = mhs_email;
        this.status = status;
        this.mhs_username = mhs_username;
        this.nip_dosen = nip_dosen;
        this.mhs_judul_id = mhs_judul_id;
        this.judul_id = judul_id;
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.tersedia = tersedia;
        this.kategori_id = kategori_id;
        this.dsn_nip = dsn_nip;
        this.dsn_nama = dsn_nama;
        this.dsn_kode = dsn_kode;
        this.dsn_kontak = dsn_kontak;
        this.dsn_foto = dsn_foto;
        this.dsn_email = dsn_email;
        this.dsn_status = dsn_status;
        this.dsn_username = dsn_username;
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

    public int getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(int mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public String getMhs_nama() {
        return mhs_nama;
    }

    public void setMhs_nama(String mhs_nama) {
        this.mhs_nama = mhs_nama;
    }

    public String getAngkatan() {
        return angkatan;
    }

    public void setAngkatan(String angkatan) {
        this.angkatan = angkatan;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMhs_username() {
        return mhs_username;
    }

    public void setMhs_username(String mhs_username) {
        this.mhs_username = mhs_username;
    }

    public String getNip_dosen() {
        return nip_dosen;
    }

    public void setNip_dosen(String nip_dosen) {
        this.nip_dosen = nip_dosen;
    }

    public int getMhs_judul_id() {
        return mhs_judul_id;
    }

    public void setMhs_judul_id(int mhs_judul_id) {
        this.mhs_judul_id = mhs_judul_id;
    }

    public int getJudul_id() {
        return judul_id;
    }

    public void setJudul_id(int judul_id) {
        this.judul_id = judul_id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTersedia() {
        return tersedia;
    }

    public void setTersedia(String tersedia) {
        this.tersedia = tersedia;
    }

    public String getKategori_id() {
        return kategori_id;
    }

    public void setKategori_id(String kategori_id) {
        this.kategori_id = kategori_id;
    }

    public int getDsn_nip() {
        return dsn_nip;
    }

    public void setDsn_nip(int dsn_nip) {
        this.dsn_nip = dsn_nip;
    }

    public String getDsn_nama() {
        return dsn_nama;
    }

    public void setDsn_nama(String dsn_nama) {
        this.dsn_nama = dsn_nama;
    }

    public String getDsn_kode() {
        return dsn_kode;
    }

    public void setDsn_kode(String dsn_kode) {
        this.dsn_kode = dsn_kode;
    }

    public String getDsn_kontak() {
        return dsn_kontak;
    }

    public void setDsn_kontak(String dsn_kontak) {
        this.dsn_kontak = dsn_kontak;
    }

    public String getDsn_foto() {
        return dsn_foto;
    }

    public void setDsn_foto(String dsn_foto) {
        this.dsn_foto = dsn_foto;
    }

    public String getDsn_email() {
        return dsn_email;
    }

    public void setDsn_email(String dsn_email) {
        this.dsn_email = dsn_email;
    }

    public String getDsn_status() {
        return dsn_status;
    }

    public void setDsn_status(String dsn_status) {
        this.dsn_status = dsn_status;
    }

    public String getDsn_username() {
        return dsn_username;
    }

    public void setDsn_username(String dsn_username) {
        this.dsn_username = dsn_username;
    }

    protected ProyekAkhir(Parcel in) {
        proyek_akhir_id = in.readInt();
        nilai_total = in.readInt();
        nama_tim = in.readString();
        judul_id = in.readInt();
        mhs_nim = in.readInt();
        dsn_nip = in.readInt();
        nip_dosen = in.readString();
        judul = in.readString();
        kategori_id = in.readString();
        deskripsi = in.readString();
        tersedia = in.readString();
        mhs_nama = in.readString();
        angkatan = in.readString();
        mhs_kontak = in.readString();
        mhs_foto = in.readString();
        mhs_email = in.readString();
        status = in.readString();
        dsn_username = in.readString();
        mhs_username = in.readString();
        dsn_nama = in.readString();
        dsn_kode = in.readString();
        dsn_kontak = in.readString();
        dsn_foto = in.readString();
        dsn_email = in.readString();
        dsn_status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(proyek_akhir_id);
        dest.writeInt(nilai_total);
        dest.writeString(nama_tim);
        dest.writeInt(judul_id);
        dest.writeInt(mhs_nim);
        dest.writeInt(dsn_nip);
        dest.writeString(nip_dosen);
        dest.writeString(judul);
        dest.writeString(kategori_id);
        dest.writeString(deskripsi);
        dest.writeString(tersedia);
        dest.writeString(mhs_nama);
        dest.writeString(angkatan);
        dest.writeString(mhs_kontak);
        dest.writeString(mhs_foto);
        dest.writeString(mhs_email);
        dest.writeString(status);
        dest.writeString(dsn_username);
        dest.writeString(mhs_username);
        dest.writeString(dsn_nama);
        dest.writeString(dsn_kode);
        dest.writeString(dsn_kontak);
        dest.writeString(dsn_foto);
        dest.writeString(dsn_email);
        dest.writeString(dsn_status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ProyekAkhir> CREATOR = new Creator<ProyekAkhir>() {
        @Override
        public ProyekAkhir createFromParcel(Parcel in) {
            return new ProyekAkhir(in);
        }

        @Override
        public ProyekAkhir[] newArray(int size) {
            return new ProyekAkhir[size];
        }
    };
}
