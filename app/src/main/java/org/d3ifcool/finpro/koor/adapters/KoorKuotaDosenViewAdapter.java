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

import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.models.KuotaDosen;
import org.d3ifcool.finpro.core.presenters.KuotaDosenPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/06/2019.
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
public class KoorKuotaDosenViewAdapter extends RecyclerView.Adapter<KoorKuotaDosenViewAdapter.ViewHolder> {
    private ArrayList<KuotaDosen> data;
    private Context mContext;

    private KuotaDosenPresenter presenter;

    private AlertDialog.Builder mDialog;
    private View mDialogView;

    public KoorKuotaDosenViewAdapter(Context mContext, KuotaDosenPresenter presenter) {
        this.mContext = mContext;
        this.presenter = presenter;
    }

    public void setKuotaDosen(ArrayList<KuotaDosen> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public void initDialog(AlertDialog.Builder mDialog, View mDialogView) {
        this.mDialog = mDialog;
        this.mDialogView = mDialogView;
    }


    @Override
    public KoorKuotaDosenViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.content_list_koor_dosen_kuota, parent, false);
        return new KoorKuotaDosenViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(KoorKuotaDosenViewAdapter.ViewHolder holder, final int position) {
        holder.tv_kategori_monev.setText(data.get(position).getKuota_nama());
        holder.tv_jumlah_bimbingan.setText(data.get(position).getKuota_value());
        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog
                        .Builder(mContext)
                        .setMessage(mContext.getString(R.string.dialog_hapus_text))
                        .setTitle(mContext.getString(R.string.dialog_hapus_title))
                        .setPositiveButton(mContext.getString(R.string.iya), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                presenter.deleteKuota(data.get(position).getKuota_id());
                                notifyDataSetChanged();
                                presenter.getKuota();
                                dialogInterface.dismiss();
                                if (mDialogView.getParent() != null) {
                                    ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                                }
                            }
                        })
                        .setNegativeButton(mContext.getString(R.string.tidak), new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if (mDialogView.getParent() != null) {
                                    ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                                }
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mDialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit_kategori_monev, null);
//                mDialog = new AlertDialog.Builder(mDialogView.getContext());
//                mDialog.setView(mDialogView);
//                mDialog.setCancelable(true);
//                mDialog.setPositiveButton(mContext.getText(R.string.ubah), new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        EditText et_kategori = mDialogView.findViewById(R.id.dialog_kategori_monev);
//                        EditText et_jml_bimbingan = mDialogView.findViewById(R.id.dialog_jumlah_bimbingan);
//                        String result = et_kategori.getText().toString();
//                        String bimbingan = et_jml_bimbingan.getText().toString();
//
//                        presenter.updateKuotaDosen(data.get(position).getKuotaDosen_id() , result, bimbingan);
//                        notifyDataSetChanged();
//
//                        dialog.dismiss(); // Keluar Dari Dialog
//                        if (mDialogView.getParent() != null) {
//                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
//                        }
//                    }
//                });
//
//                mDialog.setNegativeButton(mContext.getText(R.string.batal), new DialogInterface.OnClickListener() {
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
            public void onClick(View view) {
                mDialogView = LayoutInflater.from(mContext).inflate(R.layout.dialog_edit_kategori_monev, null);
                mDialog = new AlertDialog.Builder(mDialogView.getContext());
                mDialog.setView(mDialogView);
                mDialog.setCancelable(true);
                mDialog.setPositiveButton(mContext.getText(R.string.ubah), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText et_kategori = mDialogView.findViewById(R.id.dialog_kategori_monev);
                        EditText et_jml_bimbingan = mDialogView.findViewById(R.id.dialog_jumlah_bimbingan);
                        String result = et_kategori.getText().toString();
                        String bimbingan = et_jml_bimbingan.getText().toString();

                        presenter.updateKuota(data.get(position).getKuota_id() , result, bimbingan);
                        notifyDataSetChanged();

                        dialog.dismiss(); // Keluar Dari Dialog
                        if (mDialogView.getParent() != null) {
                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                        }
                    }
                });

                mDialog.setNegativeButton(mContext.getText(R.string.batal), new DialogInterface.OnClickListener() {
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

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_kategori_monev, tv_jumlah_bimbingan;

        ImageView btn_hapus, btn_edit;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_kategori_monev = itemView.findViewById(R.id.ctn_kuota_dosen);
            tv_jumlah_bimbingan = itemView.findViewById(R.id.ctn_kuota_value);
            btn_hapus = itemView.findViewById(R.id.ctn_koor_button_hapus);
            btn_edit = itemView.findViewById(R.id.ctn_koor_button_edit);
        }
    }
}
