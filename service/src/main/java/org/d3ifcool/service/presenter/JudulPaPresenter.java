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


    public JudulPaPresenter(JudulPaSubDosenViewEditor viewEditor, Context context) {
        this.viewEditor = viewEditor;
        this.context = context;
    }

    public JudulPaPresenter(JudulPaSubDosenViewResult viewResult, Context context) {
        this.viewResult = viewResult;
        this.context = context;
    }
    public void createJudul (String judul, String deskripsi, String kategori, String nip_dosen){

        ApiInterfaceJudulPa apiInterface = ApiClient.getApiClient().create(ApiInterfaceJudulPa.class);
        Call<JudulPa> call = apiInterface.createJudul(judul,deskripsi,kategori,nip_dosen);
        call.enqueue(new Callback<JudulPa>() {
            @Override
            public void onResponse(Call<JudulPa> call, Response<JudulPa> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<JudulPa> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });

    }

    public void getJudul() {
        ApiInterfaceJudulPa apiInterfaceJudulPa = ApiClient.getApiClient().create(ApiInterfaceJudulPa.class);
        Call<List<JudulPa>> call = apiInterfaceJudulPa.getJudul();
        call.enqueue(new Callback<List<JudulPa>>() {
            @Override
            public void onResponse(Call<List<JudulPa>> call, Response<List<JudulPa>> response) {
                viewResult.hideProgress();
                viewResult.onGetResult(response.body());
            }

            @Override
            public void onFailure(Call<List<JudulPa>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }

    public void deleteJudul(int id_judul){
        ApiInterfaceJudulPa apiInterfaceJudulPa = ApiClient.getApiClient().create(ApiInterfaceJudulPa.class);
        Call<JudulPa> call = apiInterfaceJudulPa.deleteJudul(id_judul);
        call.enqueue(new Callback<JudulPa>() {
            @Override
            public void onResponse(Call<JudulPa> call, Response<JudulPa> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<JudulPa> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }


}
