package org.d3ifcool.finpro.koor.fragments;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.core.interfaces.lists.MonevListView;
import org.d3ifcool.finpro.core.interfaces.works.MonevWorkView;
import org.d3ifcool.finpro.core.models.Monev;
import org.d3ifcool.finpro.core.presenters.MonevPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.koor.adapters.KoorMonevKategoriViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorKategoriMonevFragment extends Fragment implements MonevWorkView , MonevListView {

    private RecyclerView recyclerView;
    private KoorMonevKategoriViewAdapter koorMonevKategoriViewAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Monev> monevs = new ArrayList<>();
    private View empty_view;
    private MonevPresenter monevPresenter;
    private ProgressDialog progressDialog;
    public KoorKategoriMonevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_koor_kategori_monev, container, false);

        FloatingActionButton actionButton = view.findViewById(R.id.frg_koor_kategori_monev_fab);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        monevPresenter = new MonevPresenter(this, this);
        monevPresenter.initContext(getContext());

        koorMonevKategoriViewAdapter = new KoorMonevKategoriViewAdapter(getContext(), monevPresenter);

        empty_view = view.findViewById(R.id.view_emptyview);
        recyclerView = view.findViewById(R.id.frg_koor_kategori_monev_rv);

        koorMonevKategoriViewAdapter.notifyDataSetChanged();
        monevPresenter.getMonev();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final View mDialogView = getLayoutInflater().inflate(R.layout.dialog_add_kategori_monev,null);
                AlertDialog alertDialog = new AlertDialog.Builder(mDialogView.getContext())
                .setView(mDialogView)
                .setPositiveButton(R.string.tambah, null)
                .setNegativeButton(R.string.batal, null)
                .create();

                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(final DialogInterface dialog) {
                        Button buttonPositive = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_POSITIVE);
                        buttonPositive.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                EditText et_kategori_monev = mDialogView.findViewById(R.id.dialog_kategori_monev);
                                EditText et_jml_bimbingan = mDialogView.findViewById(R.id.dialog_jumlah_bimbingan);
                                String kategori = et_kategori_monev.getText().toString();
                                String bimbingan = et_jml_bimbingan.getText().toString();
                                int jml = Integer.parseInt(bimbingan);
                                if (kategori.isEmpty()){
                                    et_kategori_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else if (bimbingan.isEmpty()){
                                    et_jml_bimbingan.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else if (jml > 16){
                                    et_jml_bimbingan.setError(getString(R.string.validate_tidak_lebih_16));
                                } else {
                                    monevPresenter.createMonev(kategori, bimbingan);
                                    monevPresenter.getMonev();
                                    et_kategori_monev.setText("");
                                    dialog.dismiss();
                                    if (mDialogView.getParent() != null) {
                                        ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                                    }
                                }
                            }
                        });

                        Button buttonNegative = ((AlertDialog) dialog).getButton(DialogInterface.BUTTON_NEGATIVE);
                        buttonNegative.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                    }
                });
                alertDialog.show();

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                monevPresenter.getMonev();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        koorMonevKategoriViewAdapter.notifyDataSetChanged();
        monevPresenter.getMonev();

    }

    @Override
    public void showProgress() {
        progressDialog.show();
        koorMonevKategoriViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetListMonev(List<Monev> monevList) {
        monevs.clear();
        monevs.addAll(monevList);

        koorMonevKategoriViewAdapter.setLayoutType(R.layout.content_list_koor_kategori_monev);
        koorMonevKategoriViewAdapter.setMonev(monevs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(koorMonevKategoriViewAdapter);
        swipeRefreshLayout.setRefreshing(false);

        if (monevs.size() == 0){
            empty_view.setVisibility(View.VISIBLE);
        }else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListMonev() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSucces() {
        monevPresenter.getMonev();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
