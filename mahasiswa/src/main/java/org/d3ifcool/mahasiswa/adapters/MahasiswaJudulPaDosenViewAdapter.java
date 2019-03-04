package org.d3ifcool.mahasiswa.adapters;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.mahasiswa.activities.MahasiswaJudulPaDosenDetailActivity;
import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.models.JudulPaDosen;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 05/02/2019.
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
public class MahasiswaJudulPaDosenViewAdapter extends RecyclerView.Adapter<MahasiswaJudulPaDosenViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<JudulPaDosen> data;
    private int layoutType;

    public MahasiswaJudulPaDosenViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<JudulPaDosen> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, kategori, jumlah;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            judul = itemView.findViewById(R.id.ctn_mhs_judul_pa_dosen_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_mhs_judul_pa_dosen_textview_kategori);
            jumlah = itemView.findViewById(R.id.ctn_mhs_judul_pa_dosen_textview_jumlah);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.judul.setText(data.get(position).getJudul());
        holder.kategori.setText(data.get(position).getKategori());
        holder.jumlah.setText(data.get(position).getJumlah());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, MahasiswaJudulPaDosenDetailActivity.class);
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
        return data.size();
    }

}
