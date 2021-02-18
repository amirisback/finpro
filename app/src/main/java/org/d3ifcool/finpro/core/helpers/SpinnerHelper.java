package org.d3ifcool.finpro.core.helpers;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.models.Monev;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 19/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */
public class SpinnerHelper {

    private Context context;

    public SpinnerHelper(Context context) {
        this.context = context;
    }


    public void initSpinnerKategoriJudul(ArrayList<KategoriJudul> kategoriJudulArrayList, Spinner spinner) {
        ArrayList<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < kategoriJudulArrayList.size(); i++) {
            spinnerItem.add(kategoriJudulArrayList.get(i).getKategori_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.content_list_all_spinner, spinnerItem);
        adapter.setDropDownViewResource(R.layout.content_list_all_spinner);
        spinner.setAdapter(adapter);
    }

    public void initSpinnerDosen(ArrayList<Dosen> dosenArrayList, Spinner spinner) {
        ArrayList<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < dosenArrayList.size(); i++) {
            spinnerItem.add(dosenArrayList.get(i).getDsn_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.content_list_all_spinner, spinnerItem);
        adapter.setDropDownViewResource(R.layout.content_list_all_spinner);
        spinner.setAdapter(adapter);

    }

    public void initSpinnerMonev(ArrayList<Monev> monevArrayList, Spinner spinner) {
        ArrayList<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < monevArrayList.size(); i++) {
            spinnerItem.add(monevArrayList.get(i).getKategori());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, R.layout.content_list_all_spinner, spinnerItem);
        adapter.setDropDownViewResource(R.layout.content_list_all_spinner);
        spinner.setAdapter(adapter);

    }

    public void initSpinnerStatus( Spinner spinner) {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(context, R.array.array_status_sidang,R.layout.content_list_all_spinner);
        adapter.setDropDownViewResource(R.layout.content_list_all_spinner);
        spinner.setAdapter(adapter);

    }

}
