package org.d3ifcool.mahasiswa.adapters.recyclerview;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.activities.detail.MahasiswaBimbinganDetailActivity;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.models.ProyekAkhir;

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
public class MahasiswaPaBimbinganViewAdapter extends RecyclerView.Adapter<MahasiswaPaBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bimbingan> data;
    private ArrayList<ProyekAkhir> datapa;
    private int layoutType;

    public MahasiswaPaBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Bimbingan> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoIsi, infoTanggal;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            infoIsi = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_tanggal);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.infoTanggal.setText(data.get(position).getTanggal());
        holder.infoIsi.setText(data.get(position).getIsi());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MahasiswaBimbinganDetailActivity.class);
                Bimbingan parcelBimbingan = data.get(position);
                intent.putExtra(MahasiswaBimbinganDetailActivity.EXTRA_BIMBINGAN, parcelBimbingan);
                context.startActivity(intent);
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