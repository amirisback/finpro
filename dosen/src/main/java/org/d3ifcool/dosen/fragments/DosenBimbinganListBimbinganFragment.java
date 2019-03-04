package org.d3ifcool.dosen.fragments;


import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.adds.DosenBimbinganTambahActivity;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenBimbinganViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenBimbinganListBimbinganFragment extends Fragment {


    public DosenBimbinganListBimbinganFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_bimbingan_list_bimbingan, container, false);

        TextView textViewDosen = rootView.findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_dosen);
        TextView textViewJumlah = rootView.findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);
        RecyclerView recyclerView = rootView.findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_dsn_bimbingan_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenBimbinganTambahActivity.class);
                startActivity(i);
            }
        });

        ArrayList<Bimbingan> arrayList = new ArrayList<>();
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));

        DosenBimbinganViewAdapter adapter = new DosenBimbinganViewAdapter(getContext());
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_pa_bimbingan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

}
