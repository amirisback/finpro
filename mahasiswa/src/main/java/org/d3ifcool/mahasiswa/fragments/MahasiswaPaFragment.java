package org.d3ifcool.mahasiswa.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.activities.MahasiswaJudulPaPengajuanTambahActivity;
import org.d3ifcool.mahasiswa.activities.MahasiswaPaBimbinganDetailActivity;
import org.d3ifcool.mahasiswa.activities.MahasiswaPaMonevDetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaPaFragment extends Fragment {


    public MahasiswaPaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_pa, container, false);

        CardView cardViewBimbingan = rootView.findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewMonev = rootView.findViewById(R.id.frg_mhs_pa_cardview_monev);
        FloatingActionButton floatingActionButton = rootView.findViewById(R.id.frg_mhs_pa_pengajuan_fab);

        cardViewBimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MahasiswaPaBimbinganDetailActivity.class);
                startActivity(i);
            }
        });

        cardViewMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MahasiswaPaMonevDetailActivity.class);
                startActivity(i);
            }
        });



        return rootView;
    }

}
