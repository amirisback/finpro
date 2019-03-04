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

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.adds.DosenJudulPaSubdosenTambahActivity;
import org.d3ifcool.service.models.JudulPa;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenJudulPaSubdosenViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulPaSubdosenFragment extends Fragment {


    public DosenJudulPaSubdosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa_subdosen, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_dsn_judul_dsn_recyclerview);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_dsn_judul_dsn_fab);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        DosenJudulPaSubdosenViewAdapter adapter = new DosenJudulPaSubdosenViewAdapter(getContext());

        ArrayList<JudulPa> arrayList = new ArrayList<>();
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));
        arrayList.add(new JudulPa("Judul", "Kategori"));

        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_dosen_judul_pa_subdosen);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), DosenJudulPaSubdosenTambahActivity.class);
                startActivity(i);
            }
        });

        return rootView;
    }

}
