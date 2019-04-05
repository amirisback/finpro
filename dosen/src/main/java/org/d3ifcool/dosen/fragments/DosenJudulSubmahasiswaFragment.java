package org.d3ifcool.dosen.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.lists.JudulListView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenJudulSubmahasiswaViewAdapter;
import org.d3ifcool.service.presenters.JudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERTUNDA;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulSubmahasiswaFragment extends Fragment implements JudulListView {

    private static final String PARAMS_1 = "judul.judul_status";
    private static final String PARAMS_2 = "judul.dsn_nip";
    private RecyclerView recyclerView;
    private DosenJudulSubmahasiswaViewAdapter adapter;
    private JudulPresenter presenter;
    private ProgressDialog progressDialog;
    private ArrayList<Judul> arrayList = new ArrayList<>();
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;
    private SessionManager sessionManager;

    public DosenJudulSubmahasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa_submahasiswa, container, false);

        sessionManager = new SessionManager(getContext());
        recyclerView = rootView.findViewById(R.id.frg_dsn_judul_mhs_recyclerview);
        adapter = new DosenJudulSubmahasiswaViewAdapter(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        presenter = new JudulPresenter(this);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_judul_mhs_swiperefresh);

        empty_view = rootView.findViewById(R.id.view_emptyview);

        presenter.getJudulSearchTwoParameter(PARAMS_1, JUDUL_STATUS_TERTUNDA, PARAMS_2, sessionManager.getSessionDosenNip());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getJudulSearchTwoParameter(PARAMS_1, JUDUL_STATUS_TERTUNDA, PARAMS_2, sessionManager.getSessionDosenNip());
            }
        });

        return rootView;
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
        arrayList.clear();
        arrayList.addAll(judulpa);
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_judul_pa_submahasiswa);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        swipeRefreshLayout.setRefreshing(false);

        if (arrayList.isEmpty()) {
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
