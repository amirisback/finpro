package org.d3ifcool.finpro.mahasiswa.fragments.child;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaJudulPaMandiriPengajuanActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaMandiriFragment extends Fragment {

    public MahasiswaJudulPaMandiriFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_judul_pa_mandiri, container, false);

        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_mhs_pa_pengajuan_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MahasiswaJudulPaMandiriPengajuanActivity.class);
                getContext().startActivity(intent);
            }
        });

        return rootView;
    }

}