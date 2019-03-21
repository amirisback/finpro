package org.d3ifcool.superuser.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.interfaces.MahasiswaViewResult;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.presenters.MahasiswaPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorMahasiswaTambahActivity;
import org.d3ifcool.superuser.adapters.KoorMahasiswaViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorMahasiswaFragment extends Fragment implements MahasiswaViewResult {
    private RecyclerView recyclerView;
    private ArrayList<Mahasiswa> mhs = new ArrayList<>();
    private KoorMahasiswaViewAdapter adapter;
    private ProgressDialog dialog;
    private SwipeRefreshLayout refreshLayout;
    private MahasiswaPresenter presenter;
    private FloatingActionButton floatingActionButton;

    public KoorMahasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_koor_mahasiswa, container, false);
        recyclerView = view.findViewById(R.id.frg_koor_mahasiswa_home_recyclerview);
        floatingActionButton = view.findViewById(R.id.frg_koor_mahasiswa_home_fab);
        refreshLayout = view.findViewById(R.id.frg_koor_mhs_swiperefresh);
        dialog = new ProgressDialog(getContext());
        presenter = new MahasiswaPresenter(this ,getContext());
        dialog.show();
        presenter.getDosen();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorMahasiswaTambahActivity.class);
                startActivity(intent);
            }
        });


        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDosen();
            }
        });
        return view;
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
    public void onGetResultDataMahasiswa(List<Mahasiswa> mahasiswa) {
        mhs.clear();
        mhs.addAll(mahasiswa);
        adapter = new KoorMahasiswaViewAdapter(getContext());
        adapter.setmMahasiswa(mhs);
        adapter.setLayouyType(R.layout.content_item_koor_mahasiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
