package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.koor.activities.detail.KoorJadwalKegiatanDetailActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 23/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.koor.adapters
 */
public class KoorJadwalKegiatanViewAdapter extends RecyclerView.Adapter<KoorJadwalKegiatanViewAdapter.ViewHolder> {

    private ArrayList<Kegiatan> data;
    private Context context;

    public KoorJadwalKegiatanViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Kegiatan> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public KoorJadwalKegiatanViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_jadwal_kegiatan, parent, false);
        return new KoorJadwalKegiatanViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(KoorJadwalKegiatanViewAdapter.ViewHolder holder, final int position) {
        holder.textViewKegiatan.setText(data.get(position).getKegiatan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorJadwalKegiatanDetailActivity.class);
                intent.putExtra(KoorJadwalKegiatanDetailActivity.EXTRA_JADWAL_KEGIATAN, data.get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewKegiatan;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewKegiatan = itemView.findViewById(R.id.ctn_all_textview_kegiatan);

        }
    }

}
