package org.d3ifcool.finpro.core.helpers;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.adapters.AnggotaViewAdapter;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 30/05/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */
public class ViewAdapterHelper {

    private RecyclerView recyclerView;
    private Context context;

    public ViewAdapterHelper(Context context) {
        this.context = context;
    }

    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
    }

    public void setAdapterAnggota(AnggotaViewAdapter anggotaViewAdapter){
        recyclerView.setAdapter(anggotaViewAdapter);
    }

}
