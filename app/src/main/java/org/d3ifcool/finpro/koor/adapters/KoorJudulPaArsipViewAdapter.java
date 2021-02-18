package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.koor.activities.detail.KoorJudulPaArsipDetailActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class KoorJudulPaArsipViewAdapter  extends RecyclerView.Adapter<KoorJudulPaArsipViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Judul> judul;

    public KoorJudulPaArsipViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<Judul> juduls){
        this.judul = juduls;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, kategori;
        public ViewHolder(View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_all_judul_textview_judul);
            kategori = itemView.findViewById(R.id.ctn_all_judul_textview_kategori);
        }
    }

    @Override
    public KoorJudulPaArsipViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_all_pa_judul, parent, false);
        return new KoorJudulPaArsipViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KoorJudulPaArsipViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(judul.get(position).getJudul());
        holder.kategori.setText(judul.get(position).getKategori_nama());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorJudulPaArsipDetailActivity.class);
                Judul parcelJudul = judul.get(position);
                intent.putExtra(KoorJudulPaArsipDetailActivity.EXTRA_JUDUL, parcelJudul);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return judul.size();
    }
}
