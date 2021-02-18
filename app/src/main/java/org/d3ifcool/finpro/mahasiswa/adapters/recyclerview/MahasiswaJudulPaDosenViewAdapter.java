package org.d3ifcool.finpro.mahasiswa.adapters.recyclerview;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.mahasiswa.activities.detail.MahasiswaJudulPaDosenDetailActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Judul;

import java.util.ArrayList;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERSEDIA;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 05/02/2019.
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
public class MahasiswaJudulPaDosenViewAdapter extends RecyclerView.Adapter<MahasiswaJudulPaDosenViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> dataJudul;
    private int layoutType;

    public MahasiswaJudulPaDosenViewAdapter(Context context) {
        this.context = context;
    }

    public void addItemJudul(ArrayList<Judul> data){
        this.dataJudul = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView judul, kategori;
        ImageView status;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            judul = itemView.findViewById(R.id.ctn_mhs_judul_pa_dosen_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_mhs_judul_pa_dosen_textview_kategori);
            status = itemView.findViewById(R.id.ctn_mhs_judul_pa_dosen_status);
            // -------------------------------------------------------------------------------------
        }
    }


    private void cekStatusJudul(ImageView imageViewStatus, String status){

        if (status.equalsIgnoreCase(JUDUL_STATUS_TERSEDIA)){
            imageViewStatus.setImageResource(R.drawable.ic_judul_available);
            imageViewStatus.setBackgroundResource(R.drawable.circle_judul_available);
        } else if (status.equalsIgnoreCase(JUDUL_STATUS_DIGUNAKAN)) {
            imageViewStatus.setImageResource(R.drawable.ic_judul_not_available);
            imageViewStatus.setBackgroundResource(R.drawable.circle_judul_not_available);
        } else {
            imageViewStatus.setVisibility(View.GONE);
        }

    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        final String status = dataJudul.get(position).getJudul_status();
        holder.judul.setText(dataJudul.get(position).getJudul());
        holder.kategori.setText(dataJudul.get(position).getKategori_nama());
        cekStatusJudul(holder.status, status);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (status.equalsIgnoreCase(JUDUL_STATUS_TERSEDIA)){
                    Intent intentData = new Intent(context, MahasiswaJudulPaDosenDetailActivity.class);
                    Judul parcelJudul = dataJudul.get(position);
                    intentData.putExtra(MahasiswaJudulPaDosenDetailActivity.EXTRA_JUDUL, parcelJudul);
                    context.startActivity(intentData);
                } else if (status.equalsIgnoreCase(JUDUL_STATUS_DIGUNAKAN)) {
                    Toast.makeText(context, R.string.text_judul_digunakan, Toast.LENGTH_SHORT).show();
                }
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
        return dataJudul.size();
    }

}
