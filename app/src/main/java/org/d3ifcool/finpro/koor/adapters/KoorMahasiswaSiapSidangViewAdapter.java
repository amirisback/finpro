package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.models.SiapSidang;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 28/06/2019.
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
public class KoorMahasiswaSiapSidangViewAdapter extends RecyclerView.Adapter<KoorMahasiswaSiapSidangViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<SiapSidang> mMahasiswa;
    private int layouyType;

    public KoorMahasiswaSiapSidangViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmMahasiswa(ArrayList<SiapSidang> mMahasiswa) {
        this.mMahasiswa = mMahasiswa;
        notifyDataSetChanged();
    }

    public void setLayouyType(int layouyType) {
        this.layouyType = layouyType;
    }

    @Override
    public KoorMahasiswaSiapSidangViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layouyType,parent,false);
        return new KoorMahasiswaSiapSidangViewAdapter.ViewHolder(view);
    }


    @Override
    public int getItemCount() {
        return mMahasiswa.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView nama_mhs, nim_mhs, status_judul;
        CircleImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            nama_mhs = itemView.findViewById(R.id.ctn_koor_textview_nama_mahasiswa);
            nim_mhs = itemView.findViewById(R.id.ctn_koor_mahasiswa_nim);
            status_judul = itemView.findViewById(R.id.ctn_koor_status_judul);
            foto = itemView.findViewById(R.id.ctn_koor_mahasiswa_circle_image);
        }
    }

    @Override
    public void onBindViewHolder(KoorMahasiswaSiapSidangViewAdapter.ViewHolder holder, final int position) {

        String VAR_JUDUL = "Judul : ";

        if (mMahasiswa.get(position).getJudul_nama() != null) {
            holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundGreen));
            String tempJudul = VAR_JUDUL + mMahasiswa.get(position).getJudul_nama();
            holder.status_judul.setText(tempJudul);
        } else {
            holder.status_judul.setTextColor(mContext.getResources().getColor(R.color.colorBackgroundRed));
            String tempJudul = VAR_JUDUL + "Belum mengajukan judul";
            holder.status_judul.setText(tempJudul);
        }

        holder.nama_mhs.setText(mMahasiswa.get(position).getMhs_nama());
        holder.nim_mhs.setText(mMahasiswa.get(position).getMhs_nim());
        Picasso.get().load(URL_FOTO_MAHASISWA + mMahasiswa.get(position).getMhs_foto()).into(holder.foto);


    }

}

