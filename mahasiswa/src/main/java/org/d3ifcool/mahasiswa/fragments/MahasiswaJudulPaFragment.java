package org.d3ifcool.mahasiswa.fragments;


import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.adapters.MahasiswaJudulPaPagerAdapter;
import org.d3ifcool.service.helpers.SessionManager;


/**
 * A simple {@link Fragment} subclass.
 */
public class MahasiswaJudulPaFragment extends Fragment {

    private SessionManager sessionManager;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private View mDisableView;

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
        mTabLayout = rootView.findViewById(R.id.frg_mhs_judul_pa_tablayout);
        mViewPager = rootView.findViewById(R.id.frg_mhs_judul_pa_viewpager);
        mDisableView = rootView.findViewById(R.id.disable_view);
        sessionManager = new SessionManager(getContext());
        // -----------------------------------------------------------------------------------------
        // Membuat ViewPager (SLIDER)
        MahasiswaJudulPaPagerAdapter adapter = new MahasiswaJudulPaPagerAdapter(getActivity(),getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
        // -----------------------------------------------------------------------------------------
        checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        checkStatusJudulMahasiswa(sessionManager.getSessionMahasiswaIdJudul());
    }

    private void checkStatusJudulMahasiswa(int judulId){
        if (judulId != 0){
            mDisableView.setVisibility(View.VISIBLE);
            mTabLayout.setVisibility(View.GONE);
            mViewPager.setVisibility(View.GONE);
        } else {
            mDisableView.setVisibility(View.GONE);
            mTabLayout.setVisibility(View.VISIBLE);
            mViewPager.setVisibility(View.VISIBLE);
        }
    }


}
