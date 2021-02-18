package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.detail.DosenBimbinganDetailActivity;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.ProyekAkhir;

import java.util.ArrayList;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_PENDING;

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
public class DosenBimbinganViewAdapter extends RecyclerView.Adapter<DosenBimbinganViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Bimbingan> dataBimbingan;
    private ArrayList<Bimbingan> extraDataBimbingan;
    private ArrayList<ProyekAkhir> dataProyekAkhir;
    private int layoutType;

    public DosenBimbinganViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Bimbingan> dataBimbingan){
        this.dataBimbingan = dataBimbingan;
        notifyDataSetChanged();
    }

    public void addProyekAkhir(ArrayList<ProyekAkhir> dataProyekAkhir){
        this.dataProyekAkhir = dataProyekAkhir;
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoIsi, infoTanggal, status;
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            infoIsi = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_tanggal);
            status = itemView.findViewById(R.id.ctn_mhs_pa_bimbingan_textview_status);
            container = itemView.findViewById(R.id.container_bimbingan);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (dataBimbingan.get(position).getBimbingan_status().equals(STATUS_BIMBINGAN_PENDING)){
            holder.status.setVisibility(View.VISIBLE);
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundNotYet));
        } else {
            holder.container.setBackgroundColor(Color.TRANSPARENT);
            holder.status.setVisibility(View.GONE);
        }

        holder.infoTanggal.setText(dataBimbingan.get(position).getBimbingan_tanggal());
        holder.infoIsi.setText(dataBimbingan.get(position).getBimbingan_review());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenBimbinganDetailActivity.class);
                Bimbingan parcelBimbingan = dataBimbingan.get(position);
                ArrayList<ProyekAkhir> parcelProyekAkhir = dataProyekAkhir;
                intentData.putExtra(DosenBimbinganDetailActivity.EXTRA_BIMBINGAN, parcelBimbingan);
                intentData.putExtra(DosenBimbinganDetailActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
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
        return dataBimbingan.size();
    }

}