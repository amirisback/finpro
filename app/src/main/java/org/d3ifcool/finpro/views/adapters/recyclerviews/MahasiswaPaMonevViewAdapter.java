package org.d3ifcool.finpro.views.adapters.recyclerviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.models.dataclass.Bimbingan;
import org.d3ifcool.finpro.models.dataclass.Monev;

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
    private ArrayList<Monev> data;
    private int layoutType;

    public MahasiswaPaMonevViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Monev> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoTanggal, infoNilai, infoIsi;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            infoTanggal = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_tanggal);
            infoIsi = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_komentar);
            infoNilai = itemView.findViewById(R.id.ctn_mhs_pa_monev_textview_nilai);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final MahasiswaPaMonevViewAdapter.ViewHolder holder, final int position) {
        holder.infoNilai.setText(String.valueOf(data.get(position).getNilai()));
        holder.infoTanggal.setText(data.get(position).getTanggal());
        holder.infoIsi.setText(data.get(position).getKomentar());
    }

    @NonNull
    @Override
    public MahasiswaPaMonevViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new MahasiswaPaMonevViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}