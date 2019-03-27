package org.d3ifcool.dosen.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenPaBimbinganViewAdapter;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.models.ProyekAkhir;

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

        ArrayList<ProyekAkhir> arrayListpa = new ArrayList<>();
        ArrayList<KategoriJudul> arrayListkategori = new ArrayList<>();

        arrayListpa.add(new ProyekAkhir("seven primavera","finpro"));

        arrayListkategori.add(new KategoriJudul(2, "website"));
        adapter.addItemPa(arrayListpa);
        adapter.addItemKategori(arrayListkategori);
        adapter.setLayoutType(R.layout.content_item_dosen_pa_bimbingan);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;

    }

}
