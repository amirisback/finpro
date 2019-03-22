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
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.models.Judul;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorJudulPaSubdosenTambahActivity;
import org.d3ifcool.superuser.adapters.KoorJudulPaSubdosenViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorJudulPaSubdosenFragment extends Fragment {
    private Spinner spinner;
    private RecyclerView recyclerView;
    private KoorJudulPaSubdosenViewAdapter adapter;
    private FloatingActionButton actionButton;
    private ProgressDialog dialog;
    private ArrayList<Judul> judul;

    public KoorJudulPaSubdosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_koor_judul_pa_subdosen, container, false);
        spinner = view.findViewById(R.id.spinner_dosen);
        recyclerView = view.findViewById(R.id.frg_koor_judul_dsn_recyclerview);
        actionButton = view.findViewById(R.id.frg_koor_judul_dsn_fab);
        dialog = new ProgressDialog(getContext());
        adapter = new KoorJudulPaSubdosenViewAdapter(getContext());

        dialog.setMessage(getString(R.string.progress_dialog));
        dialog.show();

        judul = new ArrayList<>();
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
//        judul.add(new Judul("huhu","haha"));
        adapter.additem(judul);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorJudulPaSubdosenTambahActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
