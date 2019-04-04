package org.d3ifcool.superuser.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.service.models.Monev;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class KoorMonevKategoriViewAdapter extends RecyclerView.Adapter<KoorMonevKategoriViewAdapter.ViewHolder> {
    private ArrayList<Monev> monev;
    private Context mContext;
    int LayoutType;

    public KoorMonevKategoriViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMonev(ArrayList<Monev> monev) {
        this.monev = monev;
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
        holder.tv_kategori_monev.setText(monev.get(position).getKategori());
    }

    @Override
    public int getItemCount() {
        return monev.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_kategori_monev;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kategori_monev = itemView.findViewById(R.id.ctn_koor_monev_kategori);
        }
    }
}
