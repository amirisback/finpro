package org.d3ifcool.dosen.adapters.viewpagers;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.fragments.DosenPaBimbinganFragment;
import org.d3ifcool.dosen.fragments.DosenPaMonevFragment;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DosenMahasiswaPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public DosenMahasiswaPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DosenPaBimbinganFragment();
            case 1:
                return new DosenPaMonevFragment();
            default:
                throw new IllegalArgumentException();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_bimbingan);
            case 1:
                return mContext.getString(R.string.title_monev);
            default:
                return null;
        }

    }
}