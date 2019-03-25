package org.d3ifcool.dosen.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenJudulPaSubdosenTambahActivity;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewResult;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenJudulPaSubdosenViewAdapter;
import org.d3ifcool.service.presenters.JudulPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulPaSubdosenFragment extends Fragment implements JudulPaSubDosenViewResult {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private DosenJudulPaSubdosenViewAdapter adapter;
    private JudulPresenter presenter;
    private ProgressDialog progressDialog;
    private ArrayList<Judul> juduls = new ArrayList<>();
    private SessionManager sessionManager;
    private SwipeRefreshLayout swipeRefreshLayout;


    public DosenJudulPaSubdosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa_subdosen, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_judul_dsn_recyclerview);
        floatingActionButton = rootView.findViewById(R.id.frg_dsn_judul_dsn_fab);

        presenter = new JudulPresenter(this, getContext());
        progressDialog = new ProgressDialog(getContext());

        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_judul_dsn_swiperefresh);

        adapter = new DosenJudulPaSubdosenViewAdapter(getContext());
        sessionManager = new SessionManager(getContext());

        presenter.getJudul();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenJudulPaSubdosenTambahActivity.class);
                startActivity(i);
            }
        });

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getJudulSortByDosen(sessionManager.getSessionDosenNip());
            }
        });

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.getJudulSortByDosen(sessionManager.getSessionDosenNip());
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
    public void onGetResult(List<Judul> judulpa) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        juduls.clear();
        juduls.addAll(judulpa);
        adapter.addItem(juduls);
        adapter.setLayoutType(R.layout.content_item_dosen_judul_pa_subdosen);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
