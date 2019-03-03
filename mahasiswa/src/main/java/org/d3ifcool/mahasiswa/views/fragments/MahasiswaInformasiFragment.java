package org.d3ifcool.mahasiswa.views.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.mahasiswa.views.adapters.MahasiswaInformasiViewAdapter;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaInformasiFragment extends Fragment {


    public MahasiswaInformasiFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_informasi, container, false);

        RecyclerView recyclerView = rootView.findViewById(R.id.frg_mhs_info_recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        MahasiswaInformasiViewAdapter adapter = new MahasiswaInformasiViewAdapter(getContext());

        ArrayList<Informasi> arrayList = new ArrayList<>();

//        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
//        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
//        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));
//        arrayList.add(new Informasi("a", "Judul", getString(R.string.dummyLong), getString(R.string.dummy_tanggal), getString(R.string.dummy_dosen_pembimbing)));

        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_informasi);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        return rootView;
    }

}
