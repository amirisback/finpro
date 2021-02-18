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

import org.d3ifcool.finpro.core.models.Monev;
import org.d3ifcool.finpro.core.presenters.MonevPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;

import androidx.recyclerview.widget.RecyclerView;

public class KoorMonevKategoriViewAdapter extends RecyclerView.Adapter<KoorMonevKategoriViewAdapter.ViewHolder> {
    private ArrayList<Monev> monev;
    private Context mContext;
    int LayoutType;

    private MonevPresenter presenter;

    private AlertDialog.Builder mDialog;
    private View mDialogView;

    public KoorMonevKategoriViewAdapter(Context mContext, MonevPresenter presenter) {
        this.mContext = mContext;
        this.presenter = presenter;
    }

    public void setMonev(ArrayList<Monev> monev) {
        this.monev = monev;
        notifyDataSetChanged();
    }

    public void initDialog(AlertDialog.Builder mDialog, View mDialogView) {
        this.mDialog = mDialog;
        this.mDialogView = mDialogView;
    }


    public void setLayoutType(int layoutType) {
        LayoutType = layoutType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(LayoutType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tv_kategori_monev.setText(monev.get(position).getKategori());
        holder.tv_jumlah_bimbingan.setText(monev.get(position).getJumlah_bimbingan());
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
                                presenter.deleteMonev(monev.get(position).getMonev_id());
                                notifyDataSetChanged();
                                presenter.getMonev();
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
//                        presenter.updateMonev(monev.get(position).getMonev_id() , result, bimbingan);
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

                        presenter.updateMonev(monev.get(position).getMonev_id() , result, bimbingan);
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
        return monev.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_kategori_monev, tv_jumlah_bimbingan;

        ImageView btn_hapus, btn_edit;
        public ViewHolder(View itemView) {
            super(itemView);
            tv_kategori_monev = itemView.findViewById(R.id.ctn_koor_monev_kategori);
            tv_jumlah_bimbingan = itemView.findViewById(R.id.ctn_koor_monev_jumlah_bimbingan);
            btn_hapus = itemView.findViewById(R.id.ctn_koor_button_hapus);
            btn_edit = itemView.findViewById(R.id.ctn_koor_button_edit);
        }
    }
}
