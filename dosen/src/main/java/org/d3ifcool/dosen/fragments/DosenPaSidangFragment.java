package org.d3ifcool.dosen.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenMahasiswaSidangViewAdapter;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.models.ProyekAkhir;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosenPaSidangFragment extends Fragment {


    public DosenPaSidangFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dosen_pa_sidang, container, false);
        ArrayList<ProyekAkhir> proyekAkhir = new ArrayList<>();
//        proyekAkhir.add(new ProyekAkhir("Seven Primavera", "finpro"));
        ArrayList<KategoriJudul> kategoriJudul = new ArrayList<>();
        kategoriJudul.add(new KategoriJudul(1,"android"));
        RecyclerView recyclerView = view.findViewById(R.id.frg_dsn_pa_sidang_recyclerview);
        DosenMahasiswaSidangViewAdapter adapter = new DosenMahasiswaSidangViewAdapter(getContext());
        adapter.addItem(proyekAkhir);
        adapter.addItemkategori(kategoriJudul);
        adapter.setLayoutType(R.layout.content_item_dosen_pa_sidang);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

}
