package org.d3ifcool.finpro.views.adapters.viewpagers;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.views.fragments.DosenMonevInfoFragment;
import org.d3ifcool.finpro.views.fragments.DosenMonevListBimbinganFragment;
import org.d3ifcool.finpro.views.fragments.DosenMonevListMonevFragment;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 06/02/2019.
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
public class DosenMonevPagerAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public DosenMonevPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new DosenMonevListMonevFragment();
            case 1:
                return new DosenMonevListBimbinganFragment();
            case 2:
                return new DosenMonevInfoFragment();
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
                return mContext.getString(R.string.title_list_monev);
            case 1:
                return mContext.getString(R.string.title_list_bimbingan);
            case 2:
                return mContext.getString(R.string.title_informasi);
            default:
                return null;
        }

    }
}