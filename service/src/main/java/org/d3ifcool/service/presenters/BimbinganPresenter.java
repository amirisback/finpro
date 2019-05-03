package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.lists.BimbinganListView;
import org.d3ifcool.service.interfaces.objects.BimbinganView;
import org.d3ifcool.service.interfaces.works.BimbinganWorkView;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.networks.api.ApiInterfaceBimbingan;
import org.d3ifcool.service.networks.bridge.ApiClient;

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
public class BimbinganPresenter {
    private BimbinganListView viewResult;
    private BimbinganWorkView viewEditor;
    private BimbinganView viewObject;

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganWorkView viewEditor, BimbinganView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public BimbinganPresenter(BimbinganListView viewResult) {
        this.viewResult = viewResult;
    }

    public BimbinganPresenter(BimbinganWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public BimbinganPresenter(BimbinganView viewObject) {
        this.viewObject = viewObject;
    }

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public void createBimbingan(String bimbingan_review, String bimbingan_kehadiran, String bimbingan_tanggal, String bimbingan_status, int proyek_akhir_id){
        viewEditor.showProgress();
        ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
        Call<Bimbingan> call = apiInterfaceBimbingan.createBimbingan(bimbingan_review, bimbingan_kehadiran, bimbingan_tanggal, bimbingan_status ,proyek_akhir_id);
        call.enqueue(new Callback<Bimbingan>() {
            @Override
            public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Bimbingan> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void getBimbingan(){
        viewResult.showProgress();
        ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
        Call<List<Bimbingan>> call = apiInterfaceBimbingan.getBimbingan();
        call.enqueue(new Callback<List<Bimbingan>>() {
            @Override
            public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                viewResult.hideProgress();
                viewResult.onGetListBimbingan(response.body());
            }

            @Override
            public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void updateBimbingan(String bimbingan_id, String bimbingan_review, String bimbingan_judul, String bimbingan_tanggal){
        viewEditor.showProgress();
        ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
        Call<Bimbingan> call = apiInterfaceBimbingan.updateBimbingan(bimbingan_id, bimbingan_review, bimbingan_judul, bimbingan_tanggal);
        call.enqueue(new Callback<Bimbingan>() {
            @Override
            public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Bimbingan> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }
        });
    }

    public void deleteBimbingan(String bimbingan_id){
        viewEditor.showProgress();
        ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
        Call<Bimbingan> call = apiInterfaceBimbingan.deleteBimbingan(bimbingan_id);
        call.enqueue(new Callback<Bimbingan>() {
            @Override
            public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Bimbingan> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }


    public void searchBimbingan(String parameter, String query){
        viewResult.showProgress();
        ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
        Call<List<Bimbingan>> call = apiInterfaceBimbingan.getBimbinganSearch(parameter, query);
        call.enqueue(new Callback<List<Bimbingan>>() {
            @Override
            public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                viewResult.hideProgress();
                viewResult.onGetListBimbingan(response.body());
            }

            @Override
            public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void updateBimbinganStatus(String bimbingan_id, String bimbingan_status){
        viewEditor.showProgress();
        ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
        Call<Bimbingan> call = apiInterfaceBimbingan.updateBimbinganStatus(bimbingan_id, bimbingan_status);
        call.enqueue(new Callback<Bimbingan>() {
            @Override
            public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Bimbingan> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }
        });
    }

}
