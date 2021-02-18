package org.d3ifcool.finpro.mahasiswa.fragments.parent;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaInformasiViewAdapter;
import org.d3ifcool.finpro.core.interfaces.lists.InformasiListView;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaInformasiFragment extends Fragment implements InformasiListView {

    private ArrayList<Informasi> arrayList = new ArrayList<>();
    private MahasiswaInformasiViewAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialog;
    private InformasiPresenter informasiPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View empty_view;

    public MahasiswaInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_informasi, container, false);

        adapter = new MahasiswaInformasiViewAdapter(requireContext());
        informasiPresenter = new InformasiPresenter(this);
        informasiPresenter.initContext(requireContext());

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));


        return rootView;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.frg_mhs_info_recyclerview);
        swipeRefreshLayout = view.findViewById(R.id.frg_mhs_info_swiperefresh);
        empty_view = view.findViewById(R.id.view_emptyview);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        informasiPresenter.getInformasi();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                informasiPresenter.getInformasi();
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        informasiPresenter.getInformasi();
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
    public void onGetListInformasi(List<Informasi> informasi) {
        arrayList.clear();
        arrayList.addAll(informasi);

        adapter.addItem(arrayList);
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListInformasi() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show();
    }
}
