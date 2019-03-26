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

import org.d3ifcool.service.interfaces.lists.InformasiListView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenters.InformasiPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorInformasiTambahActivity;
import org.d3ifcool.superuser.adapters.KoorInformasiViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorInformasiFragment extends Fragment implements InformasiListView {

    private RecyclerView rv_informasi;
    private FloatingActionButton fab_informasi;
    private ArrayList<Informasi> data = new ArrayList<>();
    private KoorInformasiViewAdapter adapter;
    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    public KoorInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_koor_informasi, container, false);
        rv_informasi = view.findViewById(R.id.frg_koor_info_home_recyclerview);
        fab_informasi = view.findViewById(R.id.frg_koor_info_home_fab);
        presenter = new InformasiPresenter(this);
        swipeRefreshLayout = view.findViewById(R.id.frg_koor_info_home_swiperefresh);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        presenter.getInformasi();

        fab_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorInformasiTambahActivity.class);
                startActivity(intent);
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getInformasi();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.getInformasi();
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
    public void onGetListInformasi(List<Informasi> informasi) {
        adapter = new KoorInformasiViewAdapter(getContext());
        rv_informasi.setLayoutManager(new LinearLayoutManager(getContext()));
        data.clear();
        data.addAll(informasi);
        adapter.addItem(data);
        adapter.setLayoutType(R.layout.content_item_koor_informasi);
        rv_informasi.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
