package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.service.models.Judul;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.details.KoorJudulPaSubdosenDetailActivity;

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
public class KoorJudulPaSubdosenViewAdapter extends RecyclerView.Adapter<KoorJudulPaSubdosenViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> judul;

    public KoorJudulPaSubdosenViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_koor_judul_dsn_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_koor_judul_dsn_textview_kategori);
        }
    }

    @NonNull
    @Override
    public KoorJudulPaSubdosenViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item_koor_judulpa, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KoorJudulPaSubdosenViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorJudulPaSubdosenDetailActivity.class);
                Judul parcelInfo = judul.get(position);
//                intent.putExtra(KoorJudulPaSubdosenTambahActivity.EXTRA_INFORMASI, parcelInfo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}
