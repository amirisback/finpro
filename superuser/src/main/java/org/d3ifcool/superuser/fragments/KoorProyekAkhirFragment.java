package org.d3ifcool.superuser.fragments;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.interfaces.lists.JudulListView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorProyekAkhirViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAM_DOSEN_NAMA;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAM_JUDUL_STATUS;


/**
 * A simple {@link Fragment} subclass.
 */
public class KoorProyekAkhirFragment extends Fragment implements DosenListView, JudulListView {

    private Spinner sp_dosen;
    private RecyclerView recyclerView;
    private KoorProyekAkhirViewAdapter adapter;
    private ProgressDialog progressDialog;
    private View empty_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    private String spinnerItemPosition;

    private ArrayList<Judul> arrayListJudul = new ArrayList<>();
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();

    private DosenPresenter dosenPresenter;
    private JudulPresenter judulPresenter;

    public KoorProyekAkhirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_koor_proyek_akhir, container, false);
        sp_dosen = view.findViewById(R.id.spinner_dosen);
        recyclerView = view.findViewById(R.id.frg_koor_judul_dsn_recyclerview);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);
        empty_view = view.findViewById(R.id.view_emptyview);

        dosenPresenter = new DosenPresenter(this);
        judulPresenter = new JudulPresenter(this);

        dosenPresenter.getDosen();

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemPosition = parent.getItemAtPosition(position).toString();
                judulPresenter.searchJudulByTwo(PARAM_DOSEN_NAMA, spinnerItemPosition, PARAM_JUDUL_STATUS, JUDUL_STATUS_DIGUNAKAN);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                judulPresenter.searchJudulByTwo(PARAM_DOSEN_NAMA, spinnerItemPosition, PARAM_JUDUL_STATUS, JUDUL_STATUS_DIGUNAKAN);
            }
        });

        return view;
    }

    private void initSpinner(ArrayList<Dosen> arrayDosen, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayDosen.size(); i++) {
            spinnerItem.add(arrayDosen.get(i).getDsn_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, spinnerItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

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
        adapter = new KoorProyekAkhirViewAdapter(getContext());
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
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        initSpinner(arrayListDosen, sp_dosen);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
