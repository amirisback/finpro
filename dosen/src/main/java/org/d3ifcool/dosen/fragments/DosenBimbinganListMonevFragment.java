package org.d3ifcool.dosen.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.Detail_Monev;
import org.d3ifcool.service.models.Monev;
import org.d3ifcool.mahasiswa.adapters.MahasiswaPaMonevViewAdapter;

import java.util.ArrayList;


public class DosenBimbinganListMonevFragment extends Fragment {


    public DosenBimbinganListMonevFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_bimbingan_list_monev, container, false);

        TextView textViewDosen = rootView.findViewById(R.id.act_mhs_pa_monev_detail_textview_dosen);
        TextView textViewJumlah = rootView.findViewById(R.id.act_mhs_pa_monev_detail_textview_jumlah);
        RecyclerView recyclerView = rootView.findViewById(R.id.act_mhs_pa_monev_detail_recyclerview);

        ArrayList<Detail_Monev> arrayList = new ArrayList<>();


        MahasiswaPaMonevViewAdapter adapter = new MahasiswaPaMonevViewAdapter(getContext());
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_pa_monev);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

}
