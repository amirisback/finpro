package org.d3ifcool.service.presenter;

import android.content.Context;

import org.d3ifcool.service.interfaces.JudulPaSubDosenViewEditor;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewResult;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.models.JudulPa;
import org.d3ifcool.service.network.ApiClient;
import org.d3ifcool.service.network.ApiInterfaceInformasi;
import org.d3ifcool.service.network.ApiInterfaceJudulPa;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/2/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class JudulPaPresenter {

    private JudulPaSubDosenViewEditor viewEditor;
    private JudulPaSubDosenViewResult viewResult;
    private Context context;
    public ArrayList<String> spinner;


    public JudulPaPresenter(JudulPaSubDosenViewEditor viewEditor, Context context) {
        this.viewEditor = viewEditor;
        this.context = context;
    }

    public JudulPaPresenter(JudulPaSubDosenViewResult viewResult, Context context) {
        this.viewResult = viewResult;
        this.context = context;
    }
    public void getJudul (){

        ApiInterfaceJudulPa apiInterface = ApiClient.getApiClient().create(ApiInterfaceJudulPa.class);
        Call<List<JudulPa>> call = apiInterface.getJudul();
        call.enqueue(new Callback<List<JudulPa>>() {
            @Override
            public void onResponse(Call<List<JudulPa>> call, Response<List<JudulPa>> response) {
                viewResult.hideProgress();
                viewResult.onGetResult(response.body());

            }

            @Override
            public void onFailure(Call<List<JudulPa>> call, Throwable t) {
                viewResult.hideProgress();
            }
        });

    }

}
