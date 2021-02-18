package org.d3ifcool.finpro.koor.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.koor.activities.detail.KoorPemetaanMonevDetailActivity;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class KoorPemetaanMonevViewAdapter extends RecyclerView.Adapter<KoorPemetaanMonevViewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ProyekAkhir> proyekAkhir;

    public KoorPemetaanMonevViewAdapter(Context context) {
        this.context = context;
    }

    public void additem(ArrayList<ProyekAkhir> proyekAkhir){
        this.proyekAkhir = proyekAkhir;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView judulpa, reviewer;
        LinearLayout container;

        public ViewHolder(View itemView) {
            super(itemView);
            judulpa = itemView.findViewById(R.id.ctn_koor_pemetaan_textview_judul);
            reviewer = itemView.findViewById(R.id.ctn_koor_pemetaan_textview_judul_reviewer);
            container = itemView.findViewById(R.id.container_content);
        }
    }

    @Override
    public KoorPemetaanMonevViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.content_list_koor_pemetaan_monev, parent, false);
        return new KoorPemetaanMonevViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KoorPemetaanMonevViewAdapter.ViewHolder holder, final int position) {
        holder.judulpa.setText(proyekAkhir.get(position).getJudul_nama());

        if (proyekAkhir.get(position).getReviewer_dsn_nama() != null){
            holder.reviewer.setText(proyekAkhir.get(position).getReviewer_dsn_nama());
            holder.container.setBackgroundColor(Color.TRANSPARENT);
        } else {
            holder.reviewer.setText(context.getString(R.string.text_no_dosen_monev));
            holder.container.setBackgroundColor(context.getResources().getColor(R.color.colorBackgroundNotYet));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KoorPemetaanMonevDetailActivity.class);
                ProyekAkhir parcelProyekAkhir = proyekAkhir.get(position);
                intent.putExtra(KoorPemetaanMonevDetailActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return proyekAkhir.size();
    }
}

