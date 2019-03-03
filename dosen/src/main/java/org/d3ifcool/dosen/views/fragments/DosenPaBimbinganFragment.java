package org.d3ifcool.dosen.views.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.views.adapters.recyclerviews.DosenJudulPaSubmahasiswaViewAdapter;
import org.d3ifcool.dosen.views.adapters.recyclerviews.DosenPaBimbinganViewAdapter;
import org.d3ifcool.service.models.JudulPa;

import java.util.ArrayList;

public class DosenPaBimbinganFragment extends Fragment {

    public DosenPaBimbinganFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_pa_bimbingan, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_dsn_pa_bimbingan_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        DosenPaBimbinganViewAdapter adapter = new DosenPaBimbinganViewAdapter(getContext());

        ArrayList<JudulPa> arrayList = new ArrayList<>();
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));

        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_pa_bimbingan);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;

    }

}
