package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.detail.DosenInformasiDetailActivity;
import org.d3ifcool.finpro.core.models.Informasi;

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

    public DosenInformasiViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Informasi> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView infoJudul, infoIsi, infoTanggal, infoDosen;
        CircleImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            foto = itemView.findViewById(R.id.ctn_all_info_photo);
            infoJudul = itemView.findViewById(R.id.ctn_all_info_textview_judul);
            infoIsi = itemView.findViewById(R.id.ctn_all_info_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_all_info_textview_tanggal);
            infoDosen = itemView.findViewById(R.id.ctn_all_info_textview_nama);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.infoJudul.setText(data.get(position).getInfo_judul());
        holder.infoIsi.setText(data.get(position).getInfo_deskripsi());
        holder.infoTanggal.setText(data.get(position).getTanggal());
        holder.infoDosen.setText(data.get(position).getPenerbit());
//        Picasso.get().load(URL_FOTO_DOSEN+manager.getSessionDosenFoto()).into(holder.foto);
//        Picasso.get().load(URL_FOTO_DOSEN+data.get(position).getFoto()).transform(new CircleTransform()).into(holder.foto);
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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_informasi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}