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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.service.interfaces.works.KategoriJudulWorkView;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorJudulPaKategoriViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorKategoriJudulFragment extends Fragment implements KategoriJudulListView, KategoriJudulWorkView {

    private KategoriJudulPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private KoorJudulPaKategoriViewAdapter adapter;
    private ArrayList<KategoriJudul> arrayList = new ArrayList<>();
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView ;
    private View empty_view;

    public KoorKategoriJudulFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_koor_kategori_judul, container, false);

        final AlertDialog.Builder mDialog = new AlertDialog.Builder(getContext());
        final View mDialogView = inflater.inflate(R.layout.dialog_add_kategori_judul, null);

        recyclerView = rootView.findViewById(R.id.frg_koor_judul_kategori_rv);
        final FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_koor_info_home_fab);
        presenter = new KategoriJudulPresenter(this, this);
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        adapter = new KoorJudulPaKategoriViewAdapter(getContext(), presenter);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        empty_view = rootView.findViewById(R.id.view_emptyview);

//        adapter.initDialog(mDialog, mDialogView);
        adapter.notifyDataSetChanged();
        presenter.getKategori();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mDialog.setView(mDialogView);
                mDialog.setCancelable(true);
                mDialog.setPositiveButton(getContext().getText(R.string.tambah), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TextView tv_title = mDialogView.findViewById(R.id.ctn_dialog_title);
                        tv_title.setText("Tambah Kategori Judul");
                        EditText et_kategori = mDialogView.findViewById(R.id.dialog_kategori_ubah);
                        String result = et_kategori.getText().toString();
                        presenter.createKategori(result);
                        presenter.getKategori();
                        et_kategori.setText("");
                        dialog.dismiss(); // Keluar Dari Dialog
                        if (mDialogView.getParent() != null) {
                            ((ViewGroup) mDialogView.getParent()).removeView(mDialogView);
                        }
                    }
                });

                mDialog.setNegativeButton(getText(R.string.batal), new DialogInterface.OnClickListener() {
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getKategori();
            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressDialog.show();
        adapter.notifyDataSetChanged();
        presenter.getKategori();
    }

    @Override
    public void showProgress() {
        progressDialog.show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onSucces() {
        presenter.getKategori();
    }

    @Override
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayList.clear();
        arrayList.addAll(kategori);
        adapter.setKategoriJudul(arrayList);
        adapter.setLayoutType(R.layout.content_item_koor_kategori_judul);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
