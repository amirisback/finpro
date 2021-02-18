package org.d3ifcool.finpro.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.ProyekAkhir;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 30/05/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */
public class AnggotaViewAdapter extends RecyclerView.Adapter<AnggotaViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> data;

    public AnggotaViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<ProyekAkhir> data){
        this.data = data;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.nama.setText(data.get(position).getMhs_nama());
        holder.nim.setText(data.get(position).getMhs_nim());

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_anggota, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama, nim;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            nama = itemView.findViewById(R.id.ctn_all_anggota_nama);
            nim = itemView.findViewById(R.id.ctn_all_anggota_nim);
            // -------------------------------------------------------------------------------------
        }
    }


}