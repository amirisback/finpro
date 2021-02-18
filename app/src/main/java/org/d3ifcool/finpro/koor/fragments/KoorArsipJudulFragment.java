package org.d3ifcool.finpro.koor.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.koor.adapters.KoorJudulPaArsipViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_ARSIP;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorArsipJudulFragment extends Fragment implements JudulListView, KategoriJudulListView {

    private final String PARAM_KATEGORI = "judul.kategori_id";
    private final String PARAM_STATUS = "judul.judul_status";

    private Spinner sp_kategori;
    private RecyclerView recyclerView;
    private KoorJudulPaArsipViewAdapter adapter;
    private ProgressDialog progressDialog;
    private View empty_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Judul> arrayListJudul = new ArrayList<>();
    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();

    private JudulPresenter judulPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;

    private SpinnerHelper spinnerHelper;

    private String kategori_id;

    public KoorArsipJudulFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_koor_arsip_judul, container, false);

        sp_kategori = rootView.findViewById(R.id.spinner_kategori);
        recyclerView = rootView.findViewById(R.id.frg_koor_judul_arsip_recyclerview);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        spinnerHelper = new SpinnerHelper(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new KoorJudulPaArsipViewAdapter(getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        judulPresenter = new JudulPresenter(this);

        kategoriJudulPresenter.initContext(getContext());
        judulPresenter.initContext(getContext());

        kategoriJudulPresenter.getKategori();

        sp_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kategori_id = String.valueOf(arrayListKategoriJudul.get(position).getId());

                judulPresenter.searchJudulByTwo(PARAM_KATEGORI, kategori_id, PARAM_STATUS, JUDUL_STATUS_ARSIP);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                judulPresenter.searchJudulByTwo(PARAM_KATEGORI, kategori_id, PARAM_STATUS, JUDUL_STATUS_ARSIP);
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (kategori_id != null) {
            judulPresenter.searchJudulByTwo(PARAM_KATEGORI, kategori_id, PARAM_STATUS, JUDUL_STATUS_ARSIP);
        }
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategoriJudul.clear();
        arrayListKategoriJudul.addAll(kategori);
        spinnerHelper.initSpinnerKategoriJudul(arrayListKategoriJudul, sp_kategori);
    }

    @Override
    public void isEmptyListKategori() {

    }

    @Override
    public void onGetListJudul(List<Judul> judulpa) {
        arrayListJudul.clear();
        arrayListJudul.addAll(judulpa);

        adapter.additem(arrayListJudul);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListJudul.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListJudul() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}