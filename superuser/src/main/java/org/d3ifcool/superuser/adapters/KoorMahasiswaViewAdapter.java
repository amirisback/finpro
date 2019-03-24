package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.details.KoorDosenDetailActivity;
import org.d3ifcool.superuser.activities.details.KoorMahasiswaDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_FOTO_DOSEN;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

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
public class KoorMahasiswaViewAdapter extends RecyclerView.Adapter<KoorMahasiswaViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Mahasiswa> mMahasiswa;
    private int layouyType;

    public KoorMahasiswaViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmMahasiswa(ArrayList<Mahasiswa> mMahasiswa) {
        this.mMahasiswa = mMahasiswa;
    }

    public void setLayouyType(int layouyType) {
        this.layouyType = layouyType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layouyType,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.nama_mhs.setText(mMahasiswa.get(position).getMhs_nama());
        holder.nim_mhs.setText(mMahasiswa.get(position).getMhs_nim());
        Picasso.get().load(URL_FOTO_MAHASISWA + mMahasiswa.get(position).getMhs_foto()).into(holder.foto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, KoorMahasiswaDetailActivity.class);
                Mahasiswa parcelMahasiswa = mMahasiswa.get(position);
                intent.putExtra(KoorMahasiswaDetailActivity.EXTRA_MAHASISWA, parcelMahasiswa);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMahasiswa.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama_mhs, nim_mhs;
        CircleImageView foto;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_mhs = itemView.findViewById(R.id.ctn_koor_textview_nama_mahasiswa);
            nim_mhs = itemView.findViewById(R.id.ctn_koor_mahasiswa_nim);
            foto = itemView.findViewById(R.id.ctn_koor_mahasiswa_circle_image);
        }
    }
}
