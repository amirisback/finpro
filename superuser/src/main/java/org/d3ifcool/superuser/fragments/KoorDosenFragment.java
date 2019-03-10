package org.d3ifcool.superuser.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.superuser.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorDosenFragment extends Fragment {


    public KoorDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_koor_dosen, container, false);
        return view;
    }

}
