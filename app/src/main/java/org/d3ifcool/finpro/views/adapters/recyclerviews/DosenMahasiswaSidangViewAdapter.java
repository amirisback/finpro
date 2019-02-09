package org.d3ifcool.finpro.views.adapters.recyclerviews;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.models.dataclass.Mahasiswa;
import org.d3ifcool.finpro.views.activities.details.DosenInformasiDetailActivity;
import org.d3ifcool.finpro.views.activities.details.DosenMahasiswaBimbinganDetailActivity;
import org.d3ifcool.finpro.views.activities.details.DosenMahasiswaSidangDetailActivity;

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
public class DosenMahasiswaSidangViewAdapter extends RecyclerView.Adapter<DosenMahasiswaSidangViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Mahasiswa> data;
    private int layoutType;

    public DosenMahasiswaSidangViewAdapter(Context context) {
        this.context = context;
    }

    public void addItem(ArrayList<Mahasiswa> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mhsNama, mhsNim, mhsKelompok;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            mhsNama = itemView.findViewById(R.id.ctn_dsn_mhs_sidang_textview_nama);
            mhsNim = itemView.findViewById(R.id.ctn_dsn_mhs_sidang_textview_nim);
            mhsKelompok = itemView.findViewById(R.id.ctn_dsn_mhs_sidang_textview_kelompok);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final DosenMahasiswaSidangViewAdapter.ViewHolder holder, final int position) {
        holder.mhsNama.setText(data.get(position).getNama());
        holder.mhsNim.setText(data.get(position).getNim());
        holder.mhsKelompok.setText(data.get(position).getKelompok());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(context, DosenMahasiswaSidangDetailActivity.class);
                Mahasiswa parcelInfo = data.get(position);
                intentData.putExtra(DosenMahasiswaSidangDetailActivity.EXTRA_MAHASISWA, parcelInfo);
                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public DosenMahasiswaSidangViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new DosenMahasiswaSidangViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
