package org.d3ifcool.finpro.koor.fragments;


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

import org.d3ifcool.finpro.core.interfaces.lists.MahasiswaListView;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.koor.activities.editor.create.KoorMahasiswaTambahActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.koor.adapters.KoorMahasiswaViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorMahasiswaFragment extends Fragment implements MahasiswaListView {

    private RecyclerView recyclerView;
    private ArrayList<Mahasiswa> arrayList = new ArrayList<>();
    private KoorMahasiswaViewAdapter adapter;
    private ProgressDialog progressDialog;
    private SwipeRefreshLayout refreshLayout;
    private MahasiswaPresenter mahasiswaPresenter;
    private FloatingActionButton floatingActionButton;
    private View empty_view;

    public KoorMahasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_koor_mahasiswa, container, false);
        recyclerView = view.findViewById(R.id.frg_koor_mahasiswa_home_recyclerview);
        floatingActionButton = view.findViewById(R.id.frg_koor_mahasiswa_home_fab);
        refreshLayout = view.findViewById(R.id.frg_koor_mhs_swiperefresh);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        empty_view = view.findViewById(R.id.view_emptyview);

        mahasiswaPresenter = new MahasiswaPresenter(this);
        mahasiswaPresenter.initContext(getContext());
        mahasiswaPresenter.getMahasiswa();

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
                mahasiswaPresenter.getMahasiswa();
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mahasiswaPresenter.getMahasiswa();
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
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswa) {

        arrayList.clear();
        arrayList.addAll(mahasiswa);
        adapter = new KoorMahasiswaViewAdapter(getContext());
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
    public void isEmptyListMahasiswa() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
