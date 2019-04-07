package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.service.models.Judul;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.details.KoorPemetaanMonevDetailActivity;
import org.d3ifcool.superuser.activities.details.KoorProyekAkhirDetailActivity;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KoorPemetaanMonevViewAdapter extends RecyclerView.Adapter<KoorPemetaanMonevViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> judul;

    public KoorPemetaanMonevViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori, pembimbing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_koor_pemetaan_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_koor_pemetaan_textview_kategori);
            pembimbing = itemView.findViewById(R.id.ctn_koor_pemetaan_textview_dosenbimbing);
        }
    }

    @NonNull
    @Override
    public KoorPemetaanMonevViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item_pemetaan_monev, parent, false);
        return new org.d3ifcool.superuser.adapters.KoorPemetaanMonevViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull org.d3ifcool.superuser.adapters.KoorPemetaanMonevViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori_nama());
        holder.pembimbing.setText(judul.get(position).getDsn_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorPemetaanMonevDetailActivity.class);
                Judul parcelJudul = judul.get(position);
                intent.putExtra(KoorPemetaanMonevDetailActivity.EXTRA_JUDUL, parcelJudul);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}

