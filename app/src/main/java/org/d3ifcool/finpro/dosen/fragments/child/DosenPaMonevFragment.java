package org.d3ifcool.finpro.dosen.fragments.child;


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

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenProyekAkhirMonevViewAdapter;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosenPaMonevFragment extends Fragment implements ProyekAkhirListView {

    private static final String PARAMS_DOSEN_REVIEWER_NIP = "proyek_akhir.dsn_nip";

    private RecyclerView recyclerView;
    private DosenProyekAkhirMonevViewAdapter adapter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private ProgressDialog progressDialog;
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private SessionManager sessionManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;


    public DosenPaMonevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_pa_monev, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_pa_monev_recyclerview);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_pa_monev_swiperefresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);
        sessionManager = new SessionManager(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        adapter = new DosenProyekAkhirMonevViewAdapter(getContext());
        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        proyekAkhirPresenter.initContext(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setLayoutType(R.layout.content_list_dosen_pa_monev);

//        proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAMS_DOSEN_REVIEWER_NIP, sessionManager.getSessionDosenNip());
        proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAMS_DOSEN_REVIEWER_NIP, sessionManager.getSessionUsername());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAMS_DOSEN_REVIEWER_NIP, sessionManager.getSessionDosenNip());
                proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAMS_DOSEN_REVIEWER_NIP, sessionManager.getSessionUsername());
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
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {

        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);
        adapter.addItemPa(arrayListProyekAkhir);

        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListProyekAkhir.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListProyekAkhir() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
