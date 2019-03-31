package org.d3ifcool.dosen.adapters.recyclerviews;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DosenJudulPaArsipViewAdapter extends RecyclerView.Adapter<DosenJudulPaArsipViewAdapter.ViewHolder> {
    private ArrayList<Judul> listjudul;
    private ArrayList<KategoriJudul> listKategori;
    private Context mContext;
    private int LayoutType;

    public void setListKategori(ArrayList<KategoriJudul> listKategori) {
        this.listKategori = listKategori;
        notifyDataSetChanged();
    }

    public DosenJudulPaArsipViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setListjudul(ArrayList<Judul> listjudul) {
        this.listjudul = listjudul;
        notifyDataSetChanged();
    }

    public void setLayoutType(int layoutType) {
        LayoutType = layoutType;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.judul.setText(listjudul.get(position).getJudul());
        holder.kategori.setText(listKategori.get(position).getKategori_nama());
    }

    @Override
    public int getItemCount() {
        return listjudul.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.ctn_judul_pa_arsip_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_judul_pa_arsip_textview_kategori);
        }
    }
}
