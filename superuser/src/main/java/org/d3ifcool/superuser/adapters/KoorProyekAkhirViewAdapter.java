package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.details.KoorProyekAkhirDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 08/03/2019.
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
public class KoorProyekAkhirViewAdapter extends RecyclerView.Adapter<KoorProyekAkhirViewAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ProyekAkhir> proyekAkhirs;
    private ArrayList<Judul> juduls;
    private ArrayList<Dosen> dosens;
    private int layoutType;

    public KoorProyekAkhirViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setProyekAkhirs(ArrayList<ProyekAkhir> proyekAkhirs) {
        this.proyekAkhirs = proyekAkhirs;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layoutType,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.judul.setText("finpro - an mobile based final project management system");
        holder.kategori.setText("android");
        holder.pembimbing.setText("Hariandi Maulid");
        holder.kelompok.setText("seven primavera");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, KoorProyekAkhirDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, kelompok, pembimbing, kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.ctn_koor_pa_textview_judul);
            kelompok = itemView.findViewById(R.id.ctn_koor_pa_textview_kelompok);
            pembimbing = itemView.findViewById(R.id.ctn_koor_pa_textview_dosenbimbing);
            kategori = itemView.findViewById(R.id.ctn_koor_pa_textview_kategori);
        }
    }
}
