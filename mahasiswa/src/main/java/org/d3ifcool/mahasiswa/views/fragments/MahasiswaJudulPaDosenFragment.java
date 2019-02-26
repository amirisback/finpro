package org.d3ifcool.mahasiswa.views.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.models.JudulPaDosen;
import org.d3ifcool.mahasiswa.views.adapters.MahasiswaJudulPaDosenViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaDosenFragment extends Fragment {


    public MahasiswaJudulPaDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_judul_pa_dosen, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_mhs_judul_pa_dosen_recyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        MahasiswaJudulPaDosenViewAdapter adapter = new MahasiswaJudulPaDosenViewAdapter(getContext());

        ArrayList<JudulPaDosen> arrayList = new ArrayList<>();
        arrayList.add(new JudulPaDosen(getString(R.string.dummy_pa_judul), getString(R.string.dummy_pa_kategori), getString(R.string.dummy_pa_jumlah_pengajuan)));
        arrayList.add(new JudulPaDosen(getString(R.string.dummy_pa_judul), getString(R.string.dummy_pa_kategori), getString(R.string.dummy_pa_jumlah_pengajuan)));
        arrayList.add(new JudulPaDosen(getString(R.string.dummy_pa_judul), getString(R.string.dummy_pa_kategori), getString(R.string.dummy_pa_jumlah_pengajuan)));
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_judulpa_dosen);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

}
