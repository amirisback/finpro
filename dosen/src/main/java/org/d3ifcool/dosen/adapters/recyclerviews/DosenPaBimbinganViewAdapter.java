package org.d3ifcool.dosen.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.details.DosenBimbinganInfoActivity;
import org.d3ifcool.dosen.activities.details.DosenMahasiswaBimbinganDetailActivity;
import org.d3ifcool.dosen.fragments.DosenBimbinganInfoFragment;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.models.ProyekAkhir;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 28/02/2019.
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
public class DosenPaBimbinganViewAdapter extends RecyclerView.Adapter<DosenPaBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> dataPa;
    private ArrayList<KategoriJudul> dataKategori;
    private int layoutType;

    public DosenPaBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItemPa(ArrayList<ProyekAkhir> data){
        this.dataPa = data;
        notifyDataSetChanged();
    }

    public void addItemKategori(ArrayList<KategoriJudul> data){
        this.dataKategori = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, kategori, kelompok;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            judul = itemView.findViewById(R.id.ctn_dsn_pa_bimbingan_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_dsn_pa_bimbingan_textview_kategori);
            kelompok = itemView.findViewById(R.id.ctn_dsn_pa_bimbingan_textview_kelompok);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final DosenPaBimbinganViewAdapter.ViewHolder holder, final int position) {
        holder.judul.setText(dataPa.get(position).getJudul());
        holder.kategori.setText(dataKategori.get(position).getKategori_nama());
        holder.kelompok.setText(dataPa.get(position).getNama_tim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenBimbinganInfoActivity.class);
//                Mahasiswa parcelInfo = data.get(position);
//                intentData.putExtra(DosenMahasiswaBimbinganDetailActivity.EXTRA_MAHASISWA, parcelInfo);
                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public DosenPaBimbinganViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new DosenPaBimbinganViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return dataPa.size();
    }

}
