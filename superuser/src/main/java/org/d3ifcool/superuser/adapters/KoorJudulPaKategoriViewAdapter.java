package org.d3ifcool.superuser.adapters;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.Kategori;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;
import de.hdodenhof.circleimageview.CircleImageView;

public class KoorJudulPaKategoriViewAdapter extends RecyclerView.Adapter<KoorJudulPaKategoriViewAdapter.ViewHolder> {
    private ArrayList<Kategori> kategori;
    private Context context;
    private int layoutType;
    private int viewDialog =R.layout.content_item_dialog_edit_kategori;

    public KoorJudulPaKategoriViewAdapter(Context context) {
        this.context = context;
    }

    public void setJudul(ArrayList<Kategori> kategori) {
        this.kategori = kategori;
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.tv_kategori.setText(kategori.get(position).getKategori());
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               new AlertDialog
                       .Builder(context)
                       .setView(View.inflate(context,viewDialog,null))
                       .setPositiveButton("ubah", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               String kategori_baru = holder.et_kategori.getText().toString();
                               holder.tv_kategori.setText(kategori_baru);

                           }
                       })
                        .setNegativeButton("batak",null)
                        .show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return kategori.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tv_kategori;
        ImageButton btn_edit, btn_hapus;
        EditText et_kategori;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kategori = itemView.findViewById(R.id.ctn_koor_judul_kategori);
            btn_edit = itemView.findViewById(R.id.ctn_koor_button_edit);
            btn_hapus = itemView.findViewById(R.id.ctn_koor_button_hapus);
            et_kategori =  itemView.findViewById(R.id.dialog_kategori_ubah);

        }
    }
}
