package org.d3ifcool.dosen.adapters.recyclerviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.Notifikasi;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 11/02/2019.
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
public class DosenPemberitahuanViewAdapter extends RecyclerView.Adapter<DosenPemberitahuanViewAdapter.ViewHolder>{
    private Context context;
    private ArrayList<Notifikasi> data;


    public DosenPemberitahuanViewAdapter(Context context, ArrayList<Notifikasi> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_item_dosen_pemberitahuan, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.isi.setText(data.get(position).getDeskripsi());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView isi, tanggal, waktu;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            isi = itemView.findViewById(R.id.ctn_dsn_pemberitahuan_isi);
            waktu = itemView.findViewById(R.id.ctn_dsn_pemberitahuan_menit);
            tanggal = itemView.findViewById(R.id.ctn_dsn_pemberitahuan_waktu);

        }
    }

}
