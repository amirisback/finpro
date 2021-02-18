package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.koor.activities.detail.KoorInformasiDetailActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

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
public class KoorInformasiViewAdapter extends RecyclerView.Adapter<KoorInformasiViewAdapter.ViewHolder> {

    private ArrayList<Informasi> data;
    private Context context;

    public KoorInformasiViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Informasi> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public KoorInformasiViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_informasi, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KoorInformasiViewAdapter.ViewHolder holder, final int position) {

        holder.infoDosen.setText(data.get(position).getPenerbit());
        holder.infoIsi.setText(data.get(position).getInfo_deskripsi());
        holder.infoJudul.setText(data.get(position).getInfo_judul());
        holder.infoTanggal.setText(data.get(position).getTanggal());
//        Picasso.get().load(URL_FOTO_DOSEN+sessionManager.getSessionDosenFoto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorInformasiDetailActivity.class);
                Informasi parcelInfo = data.get(position);
                intent.putExtra(KoorInformasiDetailActivity.EXTRA_INFORMASI, parcelInfo);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView infoJudul, infoIsi, infoTanggal, infoDosen;
        CircleImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.ctn_koor_circle_image);
            foto = itemView.findViewById(R.id.ctn_all_info_photo);
            infoJudul = itemView.findViewById(R.id.ctn_all_info_textview_judul);
            infoIsi = itemView.findViewById(R.id.ctn_all_info_textview_isi);
            infoTanggal = itemView.findViewById(R.id.ctn_all_info_textview_tanggal);
            infoDosen = itemView.findViewById(R.id.ctn_all_info_textview_nama);
        }
    }
}
