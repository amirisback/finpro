package org.d3ifcool.superuser.fragments;


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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.interfaces.lists.MonevListView;
import org.d3ifcool.service.interfaces.works.MonevWorkView;
import org.d3ifcool.service.models.Monev;
import org.d3ifcool.service.presenters.MonevPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorMonevKategoriViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorKategoriMonevFragment extends Fragment implements MonevWorkView , MonevListView {

    private RecyclerView recyclerView;
    private KoorMonevKategoriViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<Monev> monevs = new ArrayList<>();
    private View empty_view;
    private MonevPresenter presenter;
    private ProgressDialog dialog;
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
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.text_progress_dialog));

        final View mDialogView = getLayoutInflater().inflate(R.layout.content_item_add_kategori_monev,null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(mDialogView.getContext());

        presenter = new MonevPresenter(this, this);
        adapter = new KoorMonevKategoriViewAdapter(getContext(), presenter);

        empty_view = view.findViewById(R.id.view_emptyview);
        recyclerView = view.findViewById(R.id.frg_koor_kategori_monev_rv);
        adapter.initDialog(builder, mDialogView);
        presenter.getMonev();

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setView(mDialogView)
                        .setPositiveButton(R.string.tambah, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                EditText et_kategori_monev = mDialogView.findViewById(R.id.dialog_kategori_monev);
                                String kategori = et_kategori_monev.getText().toString();
                                if (kategori.isEmpty()){
                                    et_kategori_monev.setError("Kategori Harus di isi");
                                }else {
                                    presenter.createMonev(kategori);
                                    presenter.getMonev();
                                    et_kategori_monev.setText("");
                                    dialogInterface.dismiss();

                                    if (mDialogView.getParent() != null) {
                                        ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                                    }
                                }
                            }
                        })
                        .setNegativeButton(R.string.batal, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                                if (mDialogView.getParent() != null) {
                                    ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                                }
                            }
                        });
                builder.show();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getMonev();
            }
        });


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        dialog.show();
        presenter.getMonev();
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void onGetListMonev(List<Monev> monevList) {
        monevs.clear();
        monevs.addAll(monevList);

        adapter.setLayoutType(R.layout.content_item_kategori_monev);
        adapter.setMonev(monevs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (monevs.size() == 0){
            empty_view.setVisibility(View.VISIBLE);
        }else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void onSucces() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
