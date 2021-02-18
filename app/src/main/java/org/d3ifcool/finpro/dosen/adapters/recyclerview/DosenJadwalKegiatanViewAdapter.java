package org.d3ifcool.finpro.dosen.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.detail.DosenJadwalKegiatanDetailActivity;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 24/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.dosen.adapters.recyclerview
 */
public class DosenJadwalKegiatanViewAdapter extends RecyclerView.Adapter<DosenJadwalKegiatanViewAdapter.ViewHolder> {

    private ArrayList<Kegiatan> data;
    private Context context;

    public DosenJadwalKegiatanViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Kegiatan> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public DosenJadwalKegiatanViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_jadwal_kegiatan, parent, false);
        return new DosenJadwalKegiatanViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DosenJadwalKegiatanViewAdapter.ViewHolder holder, final int position) {
        holder.textViewKegiatan.setText(data.get(position).getKegiatan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DosenJadwalKegiatanDetailActivity.class);
                intent.putExtra(DosenJadwalKegiatanDetailActivity.EXTRA_JADWAL_KEGIATAN, data.get(position));
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

