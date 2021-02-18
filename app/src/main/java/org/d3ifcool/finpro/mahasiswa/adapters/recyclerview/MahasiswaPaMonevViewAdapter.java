package org.d3ifcool.finpro.mahasiswa.adapters.recyclerview;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.DetailMonev;

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
public class MahasiswaPaMonevViewAdapter extends RecyclerView.Adapter<MahasiswaPaMonevViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DetailMonev> data;
    private int layoutType;

    public MahasiswaPaMonevViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<DetailMonev> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoTanggal, infoNilai, infoIsi, infoJudul;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            infoTanggal = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_tanggal);
            infoJudul = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_judul);
            infoIsi = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_komentar);
            infoNilai = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_nilai);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.infoJudul.setText(data.get(position).getMonev_kategori());
        holder.infoNilai.setText(String.valueOf(data.get(position).getMonev_nilai()));
        holder.infoTanggal.setText(data.get(position).getMonev_tanggal());
        holder.infoIsi.setText(data.get(position).getMonev_ulasan());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}