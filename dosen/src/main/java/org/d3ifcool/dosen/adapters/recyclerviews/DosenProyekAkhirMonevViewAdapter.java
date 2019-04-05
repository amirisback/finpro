package org.d3ifcool.dosen.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.details.DosenProyekAkhirMonevActivity;
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
public class DosenProyekAkhirMonevViewAdapter extends RecyclerView.Adapter<DosenProyekAkhirMonevViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> data_proyek_akhir;
    private ArrayList<KategoriJudul> data_kategori;
    private int layoutType;

    public DosenProyekAkhirMonevViewAdapter(Context context) {
        this.context = context;
    }

    public void addItemPa(ArrayList<ProyekAkhir> data_proyek_akhir){
        this.data_proyek_akhir = data_proyek_akhir;
        notifyDataSetChanged();
    }

    public void addItemKategori(ArrayList<KategoriJudul> data_kategori){
        this.data_kategori = data_kategori;
        notifyDataSetChanged();
    }
    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pa_judul, tv_pa_kategori, tv_pa_kelompok;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            tv_pa_judul = itemView.findViewById(R.id.ctn_dsn_pa_monev_textview_judul);
            tv_pa_kategori = itemView.findViewById(R.id.ctn_dsn_pa_monev_textview_kategori);
//            tv_pa_kelompok = itemView.findViewById(R.id.ctn_dsn_pa_monev_textview_kelompok);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final DosenProyekAkhirMonevViewAdapter.ViewHolder holder, final int position) {
        holder.tv_pa_judul.setText(data_proyek_akhir.get(position).getJudul_nama());
        holder.tv_pa_kategori.setText(data_kategori.get(position).getKategori_nama());
        holder.tv_pa_kelompok.setText(data_proyek_akhir.get(position).getNama_tim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenProyekAkhirMonevActivity.class);
                ProyekAkhir parcelInfo = data_proyek_akhir.get(position);
                intentData.putExtra(DosenProyekAkhirMonevActivity.EXTRA_PROYEK_AKHIR, parcelInfo);
                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public DosenProyekAkhirMonevViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new DosenProyekAkhirMonevViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data_proyek_akhir.size();
    }

}
