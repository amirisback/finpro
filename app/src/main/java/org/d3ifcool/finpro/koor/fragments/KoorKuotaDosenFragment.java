package org.d3ifcool.finpro.koor.fragments;


import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.finpro.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class KoorKuotaDosenFragment extends Fragment {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView ;
    private View empty_view;

    public KoorKuotaDosenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_koor_kuota_dosen, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        empty_view = rootView.findViewById(R.id.view_emptyview);

        return rootView;
    }


}
