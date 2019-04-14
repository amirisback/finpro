package org.d3ifcool.superuser.fragments;


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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.lists.JudulListView;
import org.d3ifcool.service.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorJudulPaArsipViewAdapter;
import org.d3ifcool.superuser.adapters.KoorJudulPaSubdosenViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_ARSIP;

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

        adapter = new KoorJudulPaArsipViewAdapter(getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        judulPresenter = new JudulPresenter(this);

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

    private void initSpinner(ArrayList<KategoriJudul> arrayKategori, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayKategori.size(); i++) {
            spinnerItem.add(arrayKategori.get(i).getKategori_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
        initSpinner(arrayListKategoriJudul, sp_kategori);
    }

    @Override
    public void onGetListJudul(List<Judul> judulpa) {
        arrayListJudul.clear();
        arrayListJudul.addAll(judulpa);
        adapter.additem(arrayListJudul);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListJudul.size() == 0) {
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