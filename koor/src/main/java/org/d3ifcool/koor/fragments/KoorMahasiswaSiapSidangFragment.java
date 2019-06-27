package org.d3ifcool.koor.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.base.interfaces.lists.SiapSidangListView;
import org.d3ifcool.base.models.Mahasiswa;
import org.d3ifcool.base.models.SiapSidang;
import org.d3ifcool.base.presenters.BimbinganPresenter;
import org.d3ifcool.base.presenters.MahasiswaPresenter;
import org.d3ifcool.koor.R;
import org.d3ifcool.koor.activities.editor.create.KoorMahasiswaTambahActivity;
import org.d3ifcool.koor.adapters.KoorMahasiswaSiapSidangViewAdapter;
import org.d3ifcool.koor.adapters.KoorMahasiswaViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.base.helpers.Constant.ObjectConstanta.JUMLAH_BIMBINGAN_SIDANG;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorMahasiswaSiapSidangFragment extends Fragment implements SiapSidangListView {

    private RecyclerView recyclerView;
    private ArrayList<SiapSidang> arrayList = new ArrayList<>();
    private KoorMahasiswaSiapSidangViewAdapter adapter;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout refreshLayout;
    private View empty_view;
    private BimbinganPresenter presenter;

    public KoorMahasiswaSiapSidangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        presenter = new BimbinganPresenter(this);
        presenter.initContext(getContext());

        return inflater.inflate(R.layout.fragment_koor_mahasiswa_siap_sidang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.frg_koor_mahasiswa_home_recyclerview);
        refreshLayout = view.findViewById(R.id.frg_koor_mhs_swiperefresh);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        empty_view = view.findViewById(R.id.view_emptyview);


        presenter.searchSiapSidang(JUMLAH_BIMBINGAN_SIDANG);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.searchSiapSidang(JUMLAH_BIMBINGAN_SIDANG);
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        presenter.searchSiapSidang(JUMLAH_BIMBINGAN_SIDANG);
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
    public void onGetListSiapSidang(List<SiapSidang> siapSidangList) {
        arrayList.clear();
        arrayList.addAll(siapSidangList);
        adapter = new KoorMahasiswaSiapSidangViewAdapter(getContext());
        adapter.setmMahasiswa(arrayList);
        adapter.setLayouyType(R.layout.content_list_koor_mahasiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        refreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }
    }

    @Override
    public void isEmptyListSiapSidang() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
