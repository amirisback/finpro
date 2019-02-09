package org.d3ifcool.finpro.views.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.models.dataclass.Monev;
import org.d3ifcool.finpro.views.activities.adds.DosenMonevTambahActivity;
import org.d3ifcool.finpro.views.adapters.recyclerviews.DosenMonevViewAdapter;
import org.d3ifcool.finpro.views.adapters.recyclerviews.MahasiswaPaMonevViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenMonevListMonevFragment extends Fragment {


    public DosenMonevListMonevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_monev_list_monev, container, false);

        TextView textViewDosen = rootView.findViewById(R.id.act_mhs_pa_monev_detail_textview_dosen);
        TextView textViewJumlah = rootView.findViewById(R.id.act_mhs_pa_monev_detail_textview_jumlah);
        RecyclerView recyclerView = rootView.findViewById(R.id.act_mhs_pa_monev_detail_recyclerview);

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_dsn_monev_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenMonevTambahActivity.class);
                startActivity(i);
            }
        });

        ArrayList<Monev> arrayList = new ArrayList<>();
        arrayList.add(new Monev(90, "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Monev(90, "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Monev(90, "01 Februari 2019", "Revisi Bab 4"));

        DosenMonevViewAdapter adapter = new DosenMonevViewAdapter(getContext());
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_pa_monev);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

}
