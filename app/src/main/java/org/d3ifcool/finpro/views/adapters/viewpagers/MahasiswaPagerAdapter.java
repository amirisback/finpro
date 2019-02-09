package org.d3ifcool.finpro.views.adapters.viewpagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.views.fragments.MahasiswaInformasiFragment;
import org.d3ifcool.finpro.views.fragments.MahasiswaJudulPaFragment;
import org.d3ifcool.finpro.views.fragments.MahasiswaPaFragment;

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
    public android.support.v4.app.Fragment getItem(int position) {
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
