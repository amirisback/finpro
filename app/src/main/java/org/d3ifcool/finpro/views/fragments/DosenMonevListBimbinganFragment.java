package org.d3ifcool.finpro.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.models.dataclass.Bimbingan;
import org.d3ifcool.finpro.views.adapters.recyclerviews.MahasiswaPaBimbinganViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenMonevListBimbinganFragment extends Fragment {


    public DosenMonevListBimbinganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_monev_list_bimbingan, container, false);

        TextView textViewDosen = rootView.findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_dosen);
        TextView textViewJumlah = rootView.findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);
        RecyclerView recyclerView = rootView.findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);

        ArrayList<Bimbingan> arrayList = new ArrayList<>();
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));

        MahasiswaPaBimbinganViewAdapter adapter = new MahasiswaPaBimbinganViewAdapter(getContext());
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_pa_bimbingan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);


        return rootView;
    }

}
