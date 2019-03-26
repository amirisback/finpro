package org.d3ifcool.superuser.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.d3ifcool.service.models.Kategori;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.adapters.KoorJudulPaKategoriViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorJudulPaKategoriFragment extends Fragment {
    private ArrayList<Kategori> kategori = new ArrayList<>();
    private RecyclerView recyclerView ;
    private ImageButton btn_edit;

    public KoorJudulPaKategoriFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_koor_judul_pa_kategori, container, false);
        View content = inflater.inflate(R.layout.content_item_kategori_judul_pa, container, false);
        recyclerView = rootView.findViewById(R.id.frg_koor_judul_kategori_rv);
        btn_edit = content.findViewById(R.id.ctn_koor_button_edit);
        KoorJudulPaKategoriViewAdapter adapter = new KoorJudulPaKategoriViewAdapter(getContext());
        kategori.add(new Kategori(1,"Android"));
        kategori.add(new Kategori(2,"Website"));
        kategori.add(new Kategori(3,"Game"));
        kategori.add(new Kategori(4,"IOT"));
        adapter.setJudul(kategori);
        adapter.setLayoutType(R.layout.content_item_kategori_judul_pa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        return rootView;
    }

}
