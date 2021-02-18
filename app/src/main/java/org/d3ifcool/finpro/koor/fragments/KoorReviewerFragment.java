package org.d3ifcool.finpro.koor.fragments;


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
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.koor.adapters.KoorReviewerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorReviewerFragment extends Fragment implements ProyekAkhirListView, DosenListView {

    private static final String PARAMS_DOSEN_REVIEWER_NAMA = "dosen.dsn_nama";

    private Spinner sp_dosen;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private View empty_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String spinnerItemPosition;
    private SpinnerHelper spinnerHelper;

    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;

    private KoorReviewerViewAdapter koorReviewerViewAdapter;

    public KoorReviewerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_koor_reviewer, container, false);

        sp_dosen = rootView.findViewById(R.id.spinner_dosen);
        recyclerView = rootView.findViewById(R.id.frg_koor_judul_dsn_recyclerview);

        progressDialog = new ProgressDialog(getContext());
        koorReviewerViewAdapter = new KoorReviewerViewAdapter(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        spinnerHelper = new SpinnerHelper(getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        dosenPresenter = new DosenPresenter(this);

        dosenPresenter.initContext(getContext());
        proyekAkhirPresenter.initContext(getContext());

        dosenPresenter.getDosen();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        koorReviewerViewAdapter.setLayoutType(R.layout.content_list_koor_dosen_reviewer);

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemPosition = parent.getItemAtPosition(position).toString();
                proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAMS_DOSEN_REVIEWER_NAMA, spinnerItemPosition);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAMS_DOSEN_REVIEWER_NAMA, spinnerItemPosition);
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
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        spinnerHelper.initSpinnerDosen(arrayListDosen, sp_dosen);
    }

    @Override
    public void isEmptyListDosen() {

    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {

        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);
        koorReviewerViewAdapter.addItemPa(arrayListProyekAkhir);

        recyclerView.setAdapter(koorReviewerViewAdapter);
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
