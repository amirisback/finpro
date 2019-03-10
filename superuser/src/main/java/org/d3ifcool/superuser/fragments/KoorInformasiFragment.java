package org.d3ifcool.superuser.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorInformasiTambahActivity;
import org.d3ifcool.superuser.adapters.KoorInformasiViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorInformasiFragment extends Fragment {

    private RecyclerView rv_informasi;
    private FloatingActionButton fab_informasi;
    private ArrayList<Informasi> data;
    private KoorInformasiViewAdapter adapter;
    public KoorInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_koor_informasi, container, false);
        rv_informasi = view.findViewById(R.id.frg_koor_info_home_recyclerview);
        fab_informasi = view.findViewById(R.id.frg_koor_info_home_fab);

        data = new ArrayList<>();
        data.add(new Informasi(1,"huhu","haha","ikhsan","ikhsan","R.drawable.ic_ajukan_judul"));
        data.add(new Informasi(1,"huhu","haha","ikhsan","ikhsan","R.drawable.ic_ajukan_judul"));
        data.add(new Informasi(1,"huhu","haha","ikhsan","ikhsan","R.drawable.ic_ajukan_judul"));
        adapter = new KoorInformasiViewAdapter(getContext());
        rv_informasi.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.addItem(data);
        adapter.setLayoutType(R.layout.content_item_koor_informasi);
        rv_informasi.setAdapter(adapter);

        fab_informasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorInformasiTambahActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
