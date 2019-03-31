package org.d3ifcool.dosen.fragments;


import android.os.Bundle;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.viewpagers.DosenJudulPaPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenJudulFragment extends Fragment {


    public DosenJudulFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dosen_judul_pa, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TabLayout mTabLayout = rootView.findViewById(R.id.frg_dsn_judulpa_home_tablayout);
        ViewPager mViewPager = rootView.findViewById(R.id.frg_dsn_judulpa_home_viewpager);
        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        DosenJudulPaPagerAdapter adapter = new DosenJudulPaPagerAdapter(getActivity(),getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }

}
