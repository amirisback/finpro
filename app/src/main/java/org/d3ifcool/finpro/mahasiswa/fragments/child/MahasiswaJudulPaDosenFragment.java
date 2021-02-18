package org.d3ifcool.finpro.mahasiswa.fragments.child;


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

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaJudulPaDosenViewAdapter;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.PARAM_DOSEN_NAMA;

/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaDosenFragment extends Fragment implements DosenListView, JudulListView {

    private Spinner sp_dosen;
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private MahasiswaJudulPaDosenViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SpinnerHelper spinnerHelper;

    private ArrayList<Judul> arrayListJudul = new ArrayList<>();
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();

    private DosenPresenter dosenPresenter;
    private JudulPresenter judulPresenter;

    private View empty_view;

    public MahasiswaJudulPaDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_judul_pa_dosen, container, false);

        recyclerView = rootView.findViewById(R.id.frg_mhs_judul_pa_dosen_recyclerview);
        sp_dosen = rootView.findViewById(R.id.spinner_dosen);
        swipeRefreshLayout = rootView.findViewById(R.id.frg_mhs_judul_pa_dosen_swiperefresh);

        spinnerHelper = new SpinnerHelper(getContext());

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        dosenPresenter = new DosenPresenter(this);
        judulPresenter = new JudulPresenter(this);

        dosenPresenter.initContext(getContext());
        judulPresenter.initContext(getContext());

        empty_view = rootView.findViewById(R.id.view_emptyview);

        adapter = new MahasiswaJudulPaDosenViewAdapter(getContext());
        dosenPresenter.getDosen();

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String spinnerItemPosition = parent.getItemAtPosition(position).toString();
                judulPresenter.searchJudulMahasiswaBy(PARAM_DOSEN_NAMA, spinnerItemPosition);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dosenPresenter.getDosen();
                judulPresenter.searchJudulMahasiswaBy(PARAM_DOSEN_NAMA, sp_dosen.getSelectedItem().toString());
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

        arrayListJudul.clear();
        arrayListJudul.addAll(judulpa);
        adapter.setLayoutType(R.layout.content_list_mahasiswa_judul_pa_dosen);
        adapter.addItemJudul(arrayListJudul);
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
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void isEmptyListDosen() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
