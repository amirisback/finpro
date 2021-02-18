package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.DosenProyekAkhirActivity;
import org.d3ifcool.finpro.core.models.Judul;

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
public class DosenProyekAkhirBimbinganViewAdapter extends RecyclerView.Adapter<DosenProyekAkhirBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> data;
    private int layoutType;

    public DosenProyekAkhirBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItemPa(ArrayList<Judul> data){
        this.data = data;
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
            judul = itemView.findViewById(R.id.ctn_all_judul_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_all_judul_textview_kategori);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final DosenProyekAkhirBimbinganViewAdapter.ViewHolder holder, final int position) {
        holder.judul.setText(data.get(position).getJudul());
        holder.kategori.setText(data.get(position).getKategori_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenProyekAkhirActivity.class);
                Judul parcelJudul = data.get(position);
                intentData.putExtra(DosenProyekAkhirActivity.EXTRA_JUDUL, parcelJudul);
                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public DosenProyekAkhirBimbinganViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new DosenProyekAkhirBimbinganViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
