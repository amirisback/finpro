package org.d3ifcool.finpro.dosen.fragments.parent;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.adapters.viewpager.DosenPaPagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class DosenPaFragment extends Fragment {


    public DosenPaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_dosen_pa, container, false);
        // -----------------------------------------------------------------------------------------
        // Deklarasi Element XML
        TabLayout mTabLayout = rootView.findViewById(R.id.frg_dsn_pa_home_tablayout);
        ViewPager mViewPager = rootView.findViewById(R.id.frg_dsn_pa_home_viewpager);
        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        DosenPaPagerAdapter adapter = new DosenPaPagerAdapter(getActivity(),getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }

}
