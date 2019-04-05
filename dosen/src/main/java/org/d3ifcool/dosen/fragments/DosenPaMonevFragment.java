package org.d3ifcool.dosen.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenProyekAkhirMonevViewAdapter;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.models.ProyekAkhir;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosenPaMonevFragment extends Fragment {


    public DosenPaMonevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_pa_monev, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_dsn_pa_monev_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        DosenProyekAkhirMonevViewAdapter adapter = new DosenProyekAkhirMonevViewAdapter(getContext());

        ArrayList<ProyekAkhir> arrayList = new ArrayList<>();
//        arrayList.add(new ProyekAkhir("seven primavera","finpro"));
        ArrayList<KategoriJudul> kategoriJudulArrayList = new ArrayList<>();
        kategoriJudulArrayList.add(new KategoriJudul(1,"android"));

        adapter.addItemPa(arrayList);
        adapter.addItemKategori(kategoriJudulArrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_pa_monev);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;

    }

}
