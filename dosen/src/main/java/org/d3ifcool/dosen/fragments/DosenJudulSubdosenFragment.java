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
import org.d3ifcool.service.interfaces.lists.JudulListView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenJudulPaSubdosenViewAdapter;
import org.d3ifcool.service.presenters.JudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.FIELD_DOSEN_NAMA;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulSubdosenFragment extends Fragment implements JudulListView {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private DosenJudulPaSubdosenViewAdapter adapter;
    private JudulPresenter presenter;
    private ProgressDialog progressDialog;
    private ArrayList<Judul> arrayList = new ArrayList<>();
    private SessionManager sessionManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;


    public DosenJudulSubdosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa_subdosen, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_judul_dsn_recyclerview);
        floatingActionButton = rootView.findViewById(R.id.frg_dsn_judul_dsn_fab);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_judul_dsn_swiperefresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        presenter = new JudulPresenter(this);
        progressDialog = new ProgressDialog(getContext());
        adapter = new DosenJudulPaSubdosenViewAdapter(getContext());
        sessionManager = new SessionManager(getContext());

        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        presenter.getJudulSearch(FIELD_DOSEN_NAMA, sessionManager.getSessionDosenNama());

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenJudulPaSubdosenTambahActivity.class);
                startActivity(i);
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getJudulSearch(FIELD_DOSEN_NAMA, sessionManager.getSessionDosenNama());
            }
        });

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.getJudulSearch(FIELD_DOSEN_NAMA, sessionManager.getSessionDosenNama());
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
    public void onGetListJudul(List<Judul> judulpa) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        arrayList.clear();
        arrayList.addAll(judulpa);

        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_judul_pa_subdosen);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
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
