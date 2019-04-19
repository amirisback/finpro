package org.d3ifcool.superuser.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.d3ifcool.service.models.Monev;
import org.d3ifcool.service.presenters.MonevPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
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

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(LayoutType, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tv_kategori_monev.setText(monev.get(position).getKategori());
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


        holder.itemView.setOnClickListener(new View.OnClickListener() {
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
                        String result = et_kategori.getText().toString();
                        presenter.updateMonev(monev.get(position).getMonev_id() , result);
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
        TextView tv_kategori_monev;
        ImageView btn_hapus;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_kategori_monev = itemView.findViewById(R.id.ctn_koor_monev_kategori);
            btn_hapus = itemView.findViewById(R.id.ctn_koor_button_hapus);
        }
    }
}
