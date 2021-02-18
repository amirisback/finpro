package org.d3ifcool.finpro.adapters;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.fragments.parent.MahasiswaInformasiFragment;
import org.d3ifcool.finpro.mahasiswa.fragments.parent.MahasiswaJudulPaFragment;
import org.d3ifcool.finpro.mahasiswa.fragments.parent.MahasiswaPaFragment;

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
public class MahasiswaPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public MahasiswaPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MahasiswaInformasiFragment();
            case 1:
                return new MahasiswaPaFragment();
            case 2:
                return new MahasiswaJudulPaFragment();
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
