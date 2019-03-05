package org.d3ifcool.dosen.fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenJudulPaSubdosenTambahActivity;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewResult;
import org.d3ifcool.service.models.JudulPa;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenJudulPaSubdosenViewAdapter;
import org.d3ifcool.service.presenter.JudulPaPresenter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulPaSubdosenFragment extends Fragment implements JudulPaSubDosenViewResult {
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private DosenJudulPaSubdosenViewAdapter adapter;
    private JudulPaPresenter presenter;
    private ProgressDialog dialog;
    private ArrayList<JudulPa> judulPas = new ArrayList<>();


    public DosenJudulPaSubdosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa_subdosen, container, false);

        recyclerView = rootView.findViewById(R.id.frg_dsn_judul_dsn_recyclerview);
        floatingActionButton = rootView.findViewById(R.id.frg_dsn_judul_dsn_fab);

        presenter = new JudulPaPresenter(this, getContext());
        dialog = new ProgressDialog(getContext());

        adapter = new DosenJudulPaSubdosenViewAdapter(getContext());


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenJudulPaSubdosenTambahActivity.class);
                startActivity(i);
            }
        });

        dialog.setMessage("tunggu sebentar");

        return rootView;
    }
    @Override
    public void onResume() {
        super.onResume();
        presenter.getJudul();
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
    public void onGetResult(List<JudulPa> judulpa) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        judulPas.clear();
        judulPas.addAll(judulpa);
        adapter.addItem(judulPas);
        adapter.setLayoutType(R.layout.content_item_dosen_judul_pa_subdosen);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
