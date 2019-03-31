package org.d3ifcool.dosen.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.details.DosenMahasiswaSidangDetailActivity;
import org.d3ifcool.dosen.activities.details.DosenMahasiswaSidangHasilDetailActivity;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.models.ProyekAkhir;

import java.util.ArrayList;

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
public class DosenMahasiswaSidangViewAdapter extends RecyclerView.Adapter<DosenMahasiswaSidangViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> dataPa;
    private ArrayList<KategoriJudul> dataKategori;
    private int layoutType;

    public DosenMahasiswaSidangViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<ProyekAkhir> data){
        this.dataPa = data;
        notifyDataSetChanged();
    }

    public void addItemkategori(ArrayList<KategoriJudul> dataKategori){
        this.dataKategori = dataKategori;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pa_judul, tv_pa_kategori, tv_pa_kelompok;
        ImageView img_detail;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            tv_pa_judul = itemView.findViewById(R.id.ctn_dsn_pa_sidang_textview_judul);
            tv_pa_kelompok = itemView.findViewById(R.id.ctn_dsn_pa_sidang_textview_kelompok);
            tv_pa_kategori = itemView.findViewById(R.id.ctn_dsn_pa_sidang_textview_kategori);
            img_detail = itemView.findViewById(R.id.ctn_dsn_pa_sidang_image_detail);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_pa_judul.setText(dataPa.get(position).getJudul_nama());
        holder.tv_pa_kelompok.setText(dataPa.get(position).getNama_tim());
        holder.tv_pa_kategori.setText(dataKategori.get(position).getKategori_nama());

        holder.img_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DosenMahasiswaSidangHasilDetailActivity.class);
                context.startActivity(intent);
            }
        });

//        holder.tv_pa_kelompok.setText(data.get(position).getKelompok());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenMahasiswaSidangDetailActivity.class);
                ProyekAkhir parcelInfo = dataPa.get(position);
                KategoriJudul parcelInfokategori = dataKategori.get(position);
//                intentData.putExtra(DosenMahasiswaSidangDetailActivity.EXTRA_PROYEK_AKHIR, parcelInfo);
                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return dataPa.size();
    }

}
