package org.d3ifcool.finpro.koor.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;


import androidx.recyclerview.widget.RecyclerView;

public class KoorJudulPaKategoriViewAdapter extends RecyclerView.Adapter<KoorJudulPaKategoriViewAdapter.ViewHolder> {

    private ArrayList<KategoriJudul> data;
    private Context context;
    private int layoutType;

    private AlertDialog.Builder mDialog;
    private View mDialogView;

    private KategoriJudulPresenter presenter;

    public KoorJudulPaKategoriViewAdapter(Context context, KategoriJudulPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }


    public void setKategoriJudul(ArrayList<KategoriJudul> kategori) {
        this.data = kategori;
        notifyDataSetChanged();
    }

    public void setLayoutType(int layoutType) {
        this.layoutType = layoutType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layoutType, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_kategori;
        ImageView btn_hapus, btn_edit;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_kategori = itemView.findViewById(R.id.ctn_koor_judul_kategori);
            btn_hapus = itemView.findViewById(R.id.ctn_koor_button_hapus);
            btn_edit = itemView.findViewById(R.id.ctn_koor_button_edit);
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv_kategori.setText(data.get(position).getKategori_nama());

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                    .Builder(context)
                    .setTitle(context.getString(R.string.dialog_hapus_title))
                    .setMessage(context.getString(R.string.dialog_hapus_text))
                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            presenter.deleteKategori(data.get(position).getId());
                            notifyDataSetChanged();
                            presenter.getKategori();
                            dialog.dismiss(); // Keluar Dari Dialog
                            if (mDialogView.getParent() != null) {
                                ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                            }
                        }
                    })

                    .setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Keluar Dari Dialog
                            if (mDialogView.getParent() != null) {
                                ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                            }
                        }
                    })

                    .show();
            }
        });

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_kategori_judul, null);
//                mDialog = new AlertDialog.Builder(mDialogView.getContext());
//                mDialog.setView(mDialogView);
//                mDialog.setCancelable(true);
//                mDialog.setPositiveButton(context.getText(R.string.ubah), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        TextView tv_title = mDialogView.findViewById(R.id.ctn_dialog_title);
//                        tv_title.setText("Ubah Kategori Judul");
//                        EditText et_kategori = mDialogView.findViewById(R.id.dialog_kategori_ubah);
//                        String result = et_kategori.getText().toString();
//                        presenter.updateKategori(data.get(position).getId() , result);
//                        notifyDataSetChanged();
//                        presenter.getKategori();
//                        dialog.dismiss(); // Keluar Dari Dialog
//                        if (mDialogView.getParent() != null) {
//                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
//                        }
//                    }
//                });
//
//                mDialog.setNegativeButton(context.getText(R.string.batal), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss(); // Keluar Dari Dialog
//                        if (mDialogView.getParent() != null) {
//                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
//                        }
//                    }
//                });
//                mDialog.show();
//            }
//        });

        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_kategori_judul, null);
                mDialog = new AlertDialog.Builder(mDialogView.getContext());
                mDialog.setView(mDialogView);
                mDialog.setCancelable(true);
                mDialog.setPositiveButton(context.getText(R.string.ubah), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv_title = mDialogView.findViewById(R.id.ctn_dialog_title);
                        tv_title.setText("Ubah Kategori Judul");
                        EditText et_kategori = mDialogView.findViewById(R.id.dialog_kategori_ubah);
                        String result = et_kategori.getText().toString();
                        presenter.updateKategori(data.get(position).getId() , result);
                             notifyDataSetChanged();
                        presenter.getKategori();
                        dialog.dismiss(); // Keluar Dari Dialog
                        if (mDialogView.getParent() != null) {
                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                        }
                    }
                });

                mDialog.setNegativeButton(context.getText(R.string.batal), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); // Keluar Dari Dialog
                        if (mDialogView.getParent() != null) {
                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                        }
                    }
                });
                mDialog.show();
            }
        });

    }

}
