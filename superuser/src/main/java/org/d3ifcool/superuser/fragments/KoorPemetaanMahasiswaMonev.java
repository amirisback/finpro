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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorPemetaanMahasiswaMonevTambahActivity;
import org.d3ifcool.superuser.adapters.KoorPemetaanMahasiswaMonevViewAdapter;
import org.d3ifcool.superuser.adapters.KoorProyekAkhirViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorPemetaanMahasiswaMonev extends Fragment {

    private RecyclerView recyclerView;
    private KoorPemetaanMahasiswaMonevViewAdapter adapter;
    private ProgressDialog dialog;
    private ArrayList<ProyekAkhir> proyekAkhirs = new ArrayList<>();
    private ArrayList<DetailMonev> detailMonevs = new ArrayList<>();
    public KoorPemetaanMahasiswaMonev() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_koor_pemetaan_mahasiswa_monev, container, false);
        recyclerView = view.findViewById(R.id.frg_koor_pemetaan_home_recyclerview);
        adapter = new KoorPemetaanMahasiswaMonevViewAdapter(getContext());
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.text_progress_dialog));

//        proyekAkhirs.add(new ProyekAkhir("seven primavera","finpro"));
        detailMonevs.add(new DetailMonev("ikhsan ramadhan"));

        adapter.addItemProyekAkhir(proyekAkhirs);
        adapter.addItemDetailMonev(detailMonevs);
        adapter.setLayoutType(R.layout.content_item_pemetaan_monev);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        FloatingActionButton actionButton = view.findViewById(R.id.frg_koor_pemetaan_home_fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KoorPemetaanMahasiswaMonevTambahActivity.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }



}
