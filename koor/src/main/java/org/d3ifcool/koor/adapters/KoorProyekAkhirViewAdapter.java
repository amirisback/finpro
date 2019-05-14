package org.d3ifcool.koor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.base.models.Judul;
import org.d3ifcool.koor.R;
import org.d3ifcool.koor.activities.details.KoorProyekAkhirDetailActivity;

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

    private Context context;
    private ArrayList<Judul> judul;

    public KoorProyekAkhirViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori, pembimbing;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_koor_pa_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_koor_pa_textview_kategori);
//            pembimbing = itemView.findViewById(R.id.ctn_koor_pa_textview_dosenbimbing);
        }

    }

    @NonNull
    @Override
    public org.d3ifcool.koor.adapters.KoorProyekAkhirViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item_koor_pa, parent, false);
        return new org.d3ifcool.koor.adapters.KoorProyekAkhirViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull org.d3ifcool.koor.adapters.KoorProyekAkhirViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori_nama());
//        holder.pembimbing.setText(judul.get(position).getDsn_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorProyekAkhirDetailActivity.class);
                Judul parcelJudul = judul.get(position);
                intent.putExtra(KoorProyekAkhirDetailActivity.EXTRA_JUDUL, parcelJudul);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}
