package org.d3ifcool.finpro.views.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenBimbinganInfoFragment extends Fragment {


    public DosenBimbinganInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dosen_bimbingan_info, container, false);
    }

}
