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

import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorMahasiswaTambahActivity;
import org.d3ifcool.superuser.adapters.KoorMahasiswaViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorMahasiswaFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Mahasiswa> mhs;
    private KoorMahasiswaViewAdapter adapter;
    private FloatingActionButton floatingActionButton;

    public KoorMahasiswaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =inflater.inflate(R.layout.fragment_koor_mahasiswa, container, false);
        recyclerView = view.findViewById(R.id.frg_koor_mahasiswa_home_recyclerview);
        floatingActionButton = view.findViewById(R.id.frg_koor_mahasiswa_home_fab);
        adapter = new KoorMahasiswaViewAdapter(getContext());
        mhs = new ArrayList<>();
//        mhs.add(new Mahasiswa("putri","6703160065","R.drawable.ic_dummy_photo","putriainunzariyah@gmail.com",
//                "081313198291","2016","aktif","putriainunz"));
//        mhs.add(new Mahasiswa("putri","6703160065","R.drawable.ic_dummy_photo","putriainunzariyah@gmail.com",
//                "081313198291","2016","aktif","putriainunz"));
//        mhs.add(new Mahasiswa("putri","6703160065","R.drawable.ic_dummy_photo","putriainunzariyah@gmail.com",
//                "081313198291","2016","aktif","putriainunz"));
//        mhs.add(new Mahasiswa("putri","6703160065","R.drawable.ic_dummy_photo","putriainunzariyah@gmail.com",
//                "081313198291","2016","aktif","putriainunz"));
//        mhs.add(new Mahasiswa("putri","6703160065","R.drawable.ic_dummy_photo","putriainunzariyah@gmail.com",
//                "081313198291","2016","aktif","putriainunz"));

        adapter.setmMahasiswa(mhs);
        adapter.setLayouyType(R.layout.content_item_koor_mahasiswa);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), KoorMahasiswaTambahActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

}
