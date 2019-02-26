package org.d3ifcool.dosen.views.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.dosen.views.activities.details.DosenInformasiDetailActivity;

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
public class DosenInformasiViewAdapter extends RecyclerView.Adapter<DosenInformasiViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Informasi> data;
    private int layoutType;

    public DosenInformasiViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Informasi> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoIsi, infoTanggal, infoDosen;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            infoIsi = itemView.findViewById(R.id.ctn_dsn_info_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_dsn_info_textview_tanggal);
            infoDosen = itemView.findViewById(R.id.ctn_dsn_info_textview_dosen);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.infoIsi.setText(data.get(position).getInfo_isi());
        holder.infoTanggal.setText(data.get(position).getInfo_tanggal());
        holder.infoDosen.setText(data.get(position).getInfo_dosen());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenInformasiDetailActivity.class);
                Informasi parcelInfo = data.get(position);
                intentData.putExtra(DosenInformasiDetailActivity.EXTRA_INFORMASI, parcelInfo);
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