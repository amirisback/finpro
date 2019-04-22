package org.d3ifcool.mahasiswa.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.models.Judul;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MahasiswaJudulPaArsipViewAdapter extends RecyclerView.Adapter<MahasiswaJudulPaArsipViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> judul;

    public MahasiswaJudulPaArsipViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_judul_pa_arsip_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_judul_pa_arsip_textview_kategori);
        }
    }

    @NonNull
    @Override
    public MahasiswaJudulPaArsipViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_item_mahasiswa_judulpa_arsip, parent, false);
        return new MahasiswaJudulPaArsipViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MahasiswaJudulPaArsipViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(context, KoorJudulPaArsipDetailActivity.class);
//                Judul parcelJudul = judul.get(position);
//                intent.putExtra(KoorJudulPaArsipDetailActivity.EXTRA_JUDUL, parcelJudul);
//                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}