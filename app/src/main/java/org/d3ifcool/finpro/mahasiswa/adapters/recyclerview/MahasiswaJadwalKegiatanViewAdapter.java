package org.d3ifcool.finpro.mahasiswa.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.detail.MahasiswaJadwalKegiatanDetailActivity;

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
 * org.d3ifcool.mahasiswa.adapters.recyclerview
 */
public class MahasiswaJadwalKegiatanViewAdapter extends RecyclerView.Adapter<MahasiswaJadwalKegiatanViewAdapter.ViewHolder> {

    private ArrayList<Kegiatan> data;
    private Context context;

    public MahasiswaJadwalKegiatanViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Kegiatan> data){
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public MahasiswaJadwalKegiatanViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_all_jadwal_kegiatan, parent, false);
        return new MahasiswaJadwalKegiatanViewAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MahasiswaJadwalKegiatanViewAdapter.ViewHolder holder, final int position) {
        holder.textViewKegiatan.setText(data.get(position).getKegiatan());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MahasiswaJadwalKegiatanDetailActivity.class);
                intent.putExtra(MahasiswaJadwalKegiatanDetailActivity.EXTRA_JADWAL_KEGIATAN, data.get(position));
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

