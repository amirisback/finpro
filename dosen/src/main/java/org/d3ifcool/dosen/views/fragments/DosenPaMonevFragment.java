package org.d3ifcool.dosen.views.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class DosenPaMonevFragment extends Fragment {


    public DosenPaMonevFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dosen_pa_monev, container, false);
    }

}
