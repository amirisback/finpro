package org.d3ifcool.finpro.views.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaPengajuanFragment extends Fragment {


    public MahasiswaJudulPaPengajuanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_mahasiswa_judul_pa_pengajuan, container, false);
    }

}
