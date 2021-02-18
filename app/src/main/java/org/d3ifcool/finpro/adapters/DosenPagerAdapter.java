package org.d3ifcool.finpro.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.d3ifcool.finpro.dosen.fragments.parent.DosenInformasiFragment;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenJudulFragment;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenPaFragment;
import org.d3ifcool.finpro.R;

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
public class DosenPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public DosenPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DosenInformasiFragment();
            case 1:
                return new DosenPaFragment();
            case 2:
                return new DosenJudulFragment();
            default:
                throw new IllegalArgumentException();
        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.title_informasi);
            case 1:
                return mContext.getString(R.string.title_proyekakhir);
            case 2:
                return mContext.getString(R.string.title_judulpa);
            default:
                return null;
        }

    }
}