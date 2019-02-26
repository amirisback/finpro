package org.d3ifcool.dosen.views.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.JudulPa;
import org.d3ifcool.dosen.views.adapters.recyclerviews.DosenJudulPaSubmahasiswaViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulPaSubmahasiswaFragment extends Fragment {


    public DosenJudulPaSubmahasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa_submahasiswa, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_dsn_judul_mhs_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        DosenJudulPaSubmahasiswaViewAdapter adapter = new DosenJudulPaSubmahasiswaViewAdapter(getContext());

        ArrayList<JudulPa> arrayList = new ArrayList<>();
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));

        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_judul_pa_submahasiswa);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

}
