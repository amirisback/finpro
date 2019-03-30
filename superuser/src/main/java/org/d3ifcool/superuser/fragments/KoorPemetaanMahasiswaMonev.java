package org.d3ifcool.superuser.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorPemetaanMahasiswaMonevTambahActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorPemetaanMahasiswaMonev extends Fragment {


    public KoorPemetaanMahasiswaMonev() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_koor_pemetaan_mahasiswa_monev, container, false);
        FloatingActionButton actionButton = view.findViewById(R.id.frg_koor_pemetaan_home_fab);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), KoorPemetaanMahasiswaMonevTambahActivity.class);
                getContext().startActivity(intent);
            }
        });

        return view;
    }

}
