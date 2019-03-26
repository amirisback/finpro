package org.d3ifcool.mahasiswa.fragments;


import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        TextView tv_judul_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_judulpa);
        TextView tv_kelompok_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_kelompokpa);
        TextView tv_nama_anggota1_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nama_1);
        TextView tv_nama_anggota2_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nama_2);
        TextView tv_nim_anggota1_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nim_1);
        TextView tv_nim_anggota2_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_nim_2);
        TextView tv_dosen_pembimbing_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_dsn_pembimbing);
        TextView tv_jumlah_bimbingan_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_jml_bimbingan);
        TextView tv_dosen_reviewer_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_dsn_reviewer);
        TextView tv_jumlah_monev_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_jml_monev);
        TextView tv_status_sidang_pa = rootView.findViewById(R.id.frg_mhs_pa_textview_sidang);


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
