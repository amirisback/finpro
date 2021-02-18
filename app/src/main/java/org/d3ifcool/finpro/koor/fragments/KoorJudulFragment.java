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
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.koor.activities.editor.create.KoorJudulPaSubdosenTambahActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.koor.adapters.KoorJudulPaSubdosenViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.PARAM_DOSEN_NAMA;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorJudulFragment extends Fragment implements DosenListView, JudulListView {

    private Spinner sp_dosen;
    private RecyclerView recyclerView;
    private KoorJudulPaSubdosenViewAdapter adapter;
    private ProgressDialog progressDialog;
    private View empty_view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpinnerHelper spinnerHelper;

    private String spinnerItemPosition;
    private int positionSpinner;

    private ArrayList<Judul> arrayListJudul = new ArrayList<>();
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();

    private DosenPresenter dosenPresenter;
    private JudulPresenter judulPresenter;

    public KoorJudulFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_koor_judul, container, false);

        sp_dosen = rootView.findViewById(R.id.spinner_dosen);
        recyclerView = rootView.findViewById(R.id.frg_koor_judul_dsn_recyclerview);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_koor_judul_dsn_fab);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        spinnerHelper = new SpinnerHelper(getContext());
        swipeRefreshLayout = rootView.findViewById(R.id.swipe_refresh);
        empty_view = rootView.findViewById(R.id.view_emptyview);

        dosenPresenter = new DosenPresenter(this);
        judulPresenter = new JudulPresenter(this);

        judulPresenter.initContext(getContext());
        dosenPresenter.initContext(getContext());

        dosenPresenter.getDosen();

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemPosition = parent.getItemAtPosition(position).toString();
                positionSpinner = position;
                judulPresenter.searchJudulBy(PARAM_DOSEN_NAMA, spinnerItemPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                judulPresenter.searchJudulBy(PARAM_DOSEN_NAMA, spinnerItemPosition);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorJudulPaSubdosenTambahActivity.class);
                intent.putExtra(KoorJudulPaSubdosenTambahActivity.EXTRA_POSITION_SPINNER, positionSpinner);
                startActivity(intent);
            }
        });

        return rootView;
    }


    @Override
    public void onResume() {
        super.onResume();
        if (spinnerItemPosition != null) {
            judulPresenter.searchJudulBy(PARAM_DOSEN_NAMA, spinnerItemPosition);
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
        arrayListJudul.clear();
        arrayListJudul.addAll(judulpa);
        adapter = new KoorJudulPaSubdosenViewAdapter(getContext());
        adapter.additem(arrayListJudul);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListJudul.size() == 0) {
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
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        spinnerHelper.initSpinnerDosen(arrayListDosen, sp_dosen);
    }

    @Override
    public void isEmptyListDosen() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
