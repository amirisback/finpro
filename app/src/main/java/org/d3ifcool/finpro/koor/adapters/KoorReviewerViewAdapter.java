package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.models.ProyekAkhir;
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
public class KoorReviewerViewAdapter extends RecyclerView.Adapter<KoorReviewerViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> data;
    private int layoutType;

    public KoorReviewerViewAdapter(Context context) {
        this.context = context;
    }

    public void addItemPa(ArrayList<ProyekAkhir> data){
        this.data = data;
        notifyDataSetChanged();
    }

    public void setLayoutType(int mLayoutType){
        this.layoutType = mLayoutType;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tv_pa_judul, tv_pa_kelompok;

        public ViewHolder(View itemView) {
            super(itemView);
            // -------------------------------------------------------------------------------------
            tv_pa_judul = itemView.findViewById(R.id.ctn_koor_pa_monev_textview_judul);
            tv_pa_kelompok = itemView.findViewById(R.id.ctn_koor_pa_monev_textview_kelompok);
            // -------------------------------------------------------------------------------------
        }
    }

    @Override
    public void onBindViewHolder(final KoorReviewerViewAdapter.ViewHolder holder, final int position) {
        holder.tv_pa_judul.setText(data.get(position).getJudul_nama());
        holder.tv_pa_kelompok.setText(data.get(position).getNama_tim());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intentData = new Intent(context, KoorReviewerViewAdapter.class);
//                ProyekAkhir parcelInfo = data.get(position);
//                intentData.putExtra(KoorReviewerViewAdapter.EXTRA_PROYEK_AKHIR, parcelInfo);
//                context.startActivity(intentData);
            }
        });
    }

    @NonNull
    @Override
    public KoorReviewerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent, false);
        return new KoorReviewerViewAdapter.ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}
