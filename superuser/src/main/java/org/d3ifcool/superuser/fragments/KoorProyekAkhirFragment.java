package org.d3ifcool.superuser.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorProyekAkhirViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class KoorProyekAkhirFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private KoorProyekAkhirViewAdapter adapter;
    private ArrayList<ProyekAkhir> proyekAkhirs;

    public KoorProyekAkhirFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_koor_proyek_akhir, container, false);
        adapter = new KoorProyekAkhirViewAdapter(getContext());
        dialog = new ProgressDialog(getContext());
        dialog.setMessage(getString(R.string.text_progress_dialog));

        proyekAkhirs = new ArrayList<>();

        recyclerView = view.findViewById(R.id.frg_koor_pa_recyclerview);

        adapter.setProyekAkhirs(proyekAkhirs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.setLayoutType(R.layout.content_item_koor_pa);
        recyclerView.setAdapter(adapter);

        return view;
    }

}
