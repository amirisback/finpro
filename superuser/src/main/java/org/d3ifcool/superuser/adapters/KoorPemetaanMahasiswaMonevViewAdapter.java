package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.details.KoorPemetaanMahasiswaMonevDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KoorPemetaanMahasiswaMonevViewAdapter extends RecyclerView.Adapter<KoorPemetaanMahasiswaMonevViewAdapter.ViewHolder> {
    private ArrayList<DetailMonev> detailMonevs;
    private ArrayList<ProyekAkhir> proyekAkhirs;
    private Context mContext;
    private int LayoutType;

    public KoorPemetaanMahasiswaMonevViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void addItemDetailMonev(ArrayList<DetailMonev> detailMonevs) {
        this.detailMonevs = detailMonevs;
        notifyDataSetChanged();
    }
    public void addItemProyekAkhir(ArrayList<ProyekAkhir> proyekAkhirs) {
        this.proyekAkhirs = proyekAkhirs;
        notifyDataSetChanged();
    }

    public void setLayoutType(int layoutType) {
        LayoutType = layoutType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(LayoutType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.judul.setText(proyekAkhirs.get(position).getJudul_nama());
        holder.reviewer.setText(detailMonevs.get(position).getMonev_reviewer());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, KoorPemetaanMahasiswaMonevDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return detailMonevs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, reviewer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.ctn_koor_pemetaan_judul);
            reviewer = itemView.findViewById(R.id.ctn_koor_pemetaan_reviewer);
        }
    }
}
