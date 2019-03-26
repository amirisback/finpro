package org.d3ifcool.service.presenters;

import android.content.Context;
import org.d3ifcool.service.interfaces.works.InformasiWorkView;
import org.d3ifcool.service.interfaces.lists.InformasiListView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.networks.bridge.ApiClient;
import org.d3ifcool.service.networks.api.ApiInterfaceInformasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/1/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class InformasiPresenter {
    private InformasiWorkView viewEditor;
    private InformasiListView viewResult;

    public InformasiPresenter(InformasiWorkView view) {
        this.viewEditor = view;
    }

    public InformasiPresenter(InformasiListView viewMain) {
        this.viewResult = viewMain;
    }

    public void createInformasi (String informasi_judul, String informasi_isi, String penerbit) {
        viewEditor.showProgress();

        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<Informasi> call = apiInterface.createInformasi(informasi_judul, informasi_isi, penerbit);
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });

    }


    public void updateInformasi (int informasi_id, String informasi_judul, String informasi_isi) {
        viewEditor.showProgress();
        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<Informasi> call = apiInterface.updateInformasi(informasi_id, informasi_judul, informasi_isi);
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                viewEditor.showProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void deleteInformasi(int informasi_id) {
        viewEditor.showProgress();
        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<Informasi> call = apiInterface.deleteInformasi(informasi_id);
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                viewEditor.showProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getInformasi (){
        viewResult.showProgress();
        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<List<Informasi>> call = apiInterface.getInformasi();
        call.enqueue(new Callback<List<Informasi>>() {
            @Override
            public void onResponse(Call<List<Informasi>> call, Response<List<Informasi>> response) {
                viewResult.hideProgress();
                viewResult.onGetListInformasi(response.body());
            }

            @Override
            public void onFailure(Call<List<Informasi>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getLocalizedMessage());
            }
        });
    }

}
