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
    private int nama_tim;

    @Expose
    @SerializedName("id_judul")
    private int id_judul;

    @Expose
    @SerializedName("mhs_nim")
    private int mhs_nim;

    @Expose
    @SerializedName("dsn_nip")
    private int dsn_nip;

    public ProyekAkhir(int proyek_akhir_id, int nilai_total, int nama_tim, int id_judul, int mhs_nim, int dsn_nip) {
        this.proyek_akhir_id = proyek_akhir_id;
        this.nilai_total = nilai_total;
        this.nama_tim = nama_tim;
        this.id_judul = id_judul;
        this.mhs_nim = mhs_nim;
        this.dsn_nip = dsn_nip;
    }

    protected ProyekAkhir(Parcel in) {
        proyek_akhir_id = in.readInt();
        nilai_total = in.readInt();
        nama_tim = in.readInt();
        id_judul = in.readInt();
        mhs_nim = in.readInt();
        dsn_nip = in.readInt();
    }

    public static final Parcelable.Creator<ProyekAkhir> CREATOR = new Parcelable.Creator<ProyekAkhir>() {
        @Override
        public ProyekAkhir createFromParcel(Parcel in) {
            return new ProyekAkhir(in);
        }

        @Override
        public ProyekAkhir[] newArray(int size) {
            return new ProyekAkhir[size];
        }
    };

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

    public int getNama_tim() {
        return nama_tim;
    }

    public void setNama_tim(int nama_tim) {
        this.nama_tim = nama_tim;
    }

    public int getId_judul() {
        return id_judul;
    }

    public void setId_judul(int id_judul) {
        this.id_judul = id_judul;
    }

    public int getMhs_nim() {
        return mhs_nim;
    }

    public void setMhs_nim(int mhs_nim) {
        this.mhs_nim = mhs_nim;
    }

    public int getDsn_nip() {
        return dsn_nip;
    }

    public void setDsn_nip(int dsn_nip) {
        this.dsn_nip = dsn_nip;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(proyek_akhir_id);
        dest.writeInt(nilai_total);
        dest.writeInt(nama_tim);
        dest.writeInt(id_judul);
        dest.writeInt(mhs_nim);
        dest.writeInt(dsn_nip);
    };
}
