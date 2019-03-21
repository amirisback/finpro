package org.d3ifcool.superuser.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.interfaces.DosenViewResult;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorDosenTambahActivity;
import org.d3ifcool.superuser.adapters.KoorDosenViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorDosenFragment extends Fragment implements DosenViewResult {

    private RecyclerView recyclerView;
    private ArrayList<Dosen> data_dosen = new ArrayList<>();
    private FloatingActionButton floatingActionButton;
    private KoorDosenViewAdapter adapter;
    private ProgressDialog dialog;
    private DosenPresenter presenter;
    public KoorDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_koor_dosen, container, false);
        presenter = new DosenPresenter(this, getContext());
        recyclerView = view.findViewById(R.id.frg_koor_dosen_home_recyclerview);
        floatingActionButton = view.findViewById(R.id.frg_koor_dosen_home_fab);
        dialog = new ProgressDialog(getContext());
        dialog.show();
        presenter.getDosen();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorDosenTambahActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void onGetResultDataDosen(List<Dosen> dosen) {
        adapter = new KoorDosenViewAdapter(getContext());
        data_dosen.clear();
        data_dosen.addAll(dosen);
        adapter.setDosens(data_dosen);
        adapter.setLayoutType(R.layout.content_item_koor_dosen);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
