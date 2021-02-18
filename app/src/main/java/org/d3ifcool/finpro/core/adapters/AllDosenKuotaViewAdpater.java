package org.d3ifcool.finpro.core.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Dosen;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.base.adapters
 */
public class AllDosenKuotaViewAdpater extends RecyclerView.Adapter<AllDosenKuotaViewAdpater.ViewHolder> {
    private Context context;
    private ArrayList<Dosen> dosens;

    public AllDosenKuotaViewAdpater(Context context) {
        this.context = context;
    }


    public void setDosens(ArrayList<Dosen> dosens) {
        this.dosens = dosens;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_all_kuota_dosen, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.nama_dosen.setText(dosens.get(position).getDsn_nama());
        holder.nip_dosen.setText(dosens.get(position).getDsn_nip());
        holder.kuota_bimbingan.setText(String.valueOf(dosens.get(position).getBatas_bimbingan()));
        holder.kuota_reviewer.setText(String.valueOf(dosens.get(position).getBatas_reviewer()));
        Picasso.get().load(URL_FOTO_DOSEN+dosens.get(position).getDsn_foto()).into(holder.foto);

    }

    @Override
    public int getItemCount() {
        return dosens.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView nama_dosen, nip_dosen, kuota_bimbingan, kuota_reviewer;
        CircleImageView foto;

        public ViewHolder(View itemView) {
            super(itemView);
            nama_dosen = itemView.findViewById(R.id.ctn_all_kuota_dosen_nama);
            nip_dosen = itemView.findViewById(R.id.ctn_all_kuota_dosen_nip);
            kuota_bimbingan = itemView.findViewById(R.id.ctn_all_kuota_dosen_bimbingan);
            kuota_reviewer = itemView.findViewById(R.id.ctn_all_kuota_dosen_reviewer);
            foto = itemView.findViewById(R.id.ctn_all_kuota_dosen_circle_image);
        }
    }
}
