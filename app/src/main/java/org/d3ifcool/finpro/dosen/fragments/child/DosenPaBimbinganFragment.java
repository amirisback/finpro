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
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenProyekAkhirBimbinganViewAdapter;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;

public class DosenPaBimbinganFragment extends Fragment implements JudulListView {

    private static final String PARAMS_1 = "judul.judul_status";
    private static final String PARAMS_2 = "judul.dsn_nip";

    private RecyclerView recyclerView;
    private DosenProyekAkhirBimbinganViewAdapter adapter;
    private JudulPresenter judulPresenter;
    private ProgressDialog progressDialog;
    private ArrayList<Judul> arrayList = new ArrayList<>();
    private SessionManager sessionManager;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public DosenPaBimbinganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_pa_bimbingan, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_pa_bimbingan_recyclerview);
        empty_view = rootView.findViewById(R.id.view_emptyview);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_dsn_pa_bimbingan_swiperefresh);
        adapter = new DosenProyekAkhirBimbinganViewAdapter(getContext());
        sessionManager = new SessionManager(getContext());
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        judulPresenter = new JudulPresenter(this);
        judulPresenter.initContext(getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setLayoutType(R.layout.content_list_all_pa_judul);

//        judulPresenter.searchJudulByTwo(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionDosenNip());
        judulPresenter.searchJudulByTwo(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionUsername());

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                judulPresenter.searchJudulByTwo(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionDosenNip());
                judulPresenter.searchJudulByTwo(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionUsername());
            }
        });

        return rootView;

    }

    @Override
    public void onResume() {
        super.onResume();
        if (sessionManager.getSessionUsername() != null) {
            judulPresenter.searchJudulByTwo(PARAMS_1, JUDUL_STATUS_DIGUNAKAN, PARAMS_2, sessionManager.getSessionUsername());
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
    public void onGetListJudul(List<Judul> judulpa) {

        arrayList.clear();
        arrayList.addAll(judulpa);
        adapter.addItemPa(arrayList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
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
