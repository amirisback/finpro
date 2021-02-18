package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.DosenJudulPaSubdosenAccActivity;
import org.d3ifcool.finpro.core.models.ProyekAkhir;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 06/04/2019.
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
public class DosenKelompokPengajuanJudulViewAdapter extends RecyclerView.Adapter<DosenKelompokPengajuanJudulViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> data;
    private int layoutType;
    private int judul_id;

    public DosenKelompokPengajuanJudulViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<ProyekAkhir> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void addExtraJudul(int judul_id){
        this.judul_id = judul_id;
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView kelompok;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            kelompok = itemView.findViewById(R.id.content_nama_kelompok);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final DosenKelompokPengajuanJudulViewAdapter.ViewHolder holder, final int position) {
        holder.kelompok.setText(data.get(position).getNama_tim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenJudulPaSubdosenAccActivity.class);
                intentData.putExtra(DosenJudulPaSubdosenAccActivity.EXTRA_JUDUL, judul_id);
                ProyekAkhir parcelProyekAkhir = data.get(position);
                intentData.putExtra(DosenJudulPaSubdosenAccActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                context.startActivity(intentData);
                ((Activity)context).finish();
            }
        });
    }

    @NonNull
    @Override
    public DosenKelompokPengajuanJudulViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new DosenKelompokPengajuanJudulViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
