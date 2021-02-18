package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.models.Notifikasi;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 04/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */
public class KoorPemberitahuanViewAdapter extends RecyclerView.Adapter<KoorPemberitahuanViewAdapter.ViewHolder>{

    private Context context;
    private ArrayList<Notifikasi> data;
    private NotifikasiPresenter notifikasiPresenter;

    public KoorPemberitahuanViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Notifikasi> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setNotifikasiPresenter(NotifikasiPresenter notifikasiPresenter){
        this.notifikasiPresenter = notifikasiPresenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_notifikasi, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView isi, tanggal, pengirim;
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            pengirim = itemView.findViewById(R.id.ctn_pemberitahuan_pengirim);
            isi = itemView.findViewById(R.id.ctn_pemberitahuan_isi);
            tanggal = itemView.findViewById(R.id.ctn_pemberitahuan_tanggal);
            container = itemView.findViewById(R.id.container_notif);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (data.get(position).getNotifikasi_baca() == 0) {
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorBackground));
        } else {
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundWhite));
        }

        holder.pengirim.setText(data.get(position).getNotifikasi_dari());
        holder.isi.setText(data.get(position).getNotifikasi_deskripsi());
        holder.tanggal.setText(data.get(position).getNotifikasi_tanggal());

        final int id = data.get(position).getNotifikasi_id();
        final int changeStateWasRead = 1;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.get(position).getNotifikasi_baca() == 0) {
                    notifikasiPresenter.updateNotifikasi(id,changeStateWasRead);
                }
            }
        });


    }
}
