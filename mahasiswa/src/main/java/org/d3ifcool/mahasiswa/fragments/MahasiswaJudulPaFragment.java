package org.d3ifcool.mahasiswa.fragments;


import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.activities.MahasiswaJudulPaPengajuanTambahActivity;
import org.d3ifcool.mahasiswa.adapters.MahasiswaJudulPaPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaFragment extends Fragment {


    public MahasiswaJudulPaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mahasiswa_judul_pa, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TabLayout mTabLayout = rootView.findViewById(R.id.frg_mhs_judul_pa_tablayout);
        ViewPager mViewPager = rootView.findViewById(R.id.frg_mhs_judul_pa_viewpager);
        FloatingActionButton button = rootView.findViewById(R.id.frg_mhs_pa_pengajuan_fab);
        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        MahasiswaJudulPaPagerAdapter adapter = new MahasiswaJudulPaPagerAdapter(getActivity(),getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // -----------------------------------------------------------------------------------------


        return rootView;
    }

}
