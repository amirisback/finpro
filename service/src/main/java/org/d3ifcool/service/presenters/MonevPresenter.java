package org.d3ifcool.service.presenters;


import org.d3ifcool.service.interfaces.lists.MonevListView;
import org.d3ifcool.service.interfaces.objects.MonevView;
import org.d3ifcool.service.interfaces.works.MonevWorkView;
import org.d3ifcool.service.models.Monev;
import org.d3ifcool.service.networks.api.ApiInterfaceMonev;
import org.d3ifcool.service.networks.bridge.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/04/2019.
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
public class MonevPresenter {

    private MonevListView viewResult;
    private MonevWorkView viewEditor;
    private MonevView viewObject;

    public MonevPresenter(MonevListView viewResult, MonevWorkView viewEditor, MonevView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public MonevPresenter(MonevWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public MonevPresenter(MonevListView viewResult) {
        this.viewResult = viewResult;
    }

    public MonevPresenter(MonevView viewObject) {
        this.viewObject = viewObject;
    }

    public void createMonev(String monev_kategori) {
        viewEditor.showProgress();
        ApiInterfaceMonev apiInterfaceMonev = ApiClient.getApiClient().create(ApiInterfaceMonev.class);
        Call<Monev> call = apiInterfaceMonev.createMonev(monev_kategori);
        call.enqueue(new Callback<Monev>() {
            @Override
            public void onResponse(Call<Monev> call, Response<Monev> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Monev> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void getMonev(){
        viewResult.showProgress();
        ApiInterfaceMonev apiInterfaceMonev = ApiClient.getApiClient().create(ApiInterfaceMonev.class);
        Call<List<Monev>> call = apiInterfaceMonev.getMonev();
        call.enqueue(new Callback<List<Monev>>() {
            @Override
            public void onResponse(Call<List<Monev>> call, Response<List<Monev>> response) {
                viewResult.hideProgress();
                viewResult.onGetListMonev(response.body());
            }

            @Override
            public void onFailure(Call<List<Monev>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });


    }

    public void deleteMonev(int monev_id){
        viewEditor.showProgress();
        ApiInterfaceMonev apiInterfaceMonev = ApiClient.getApiClient().create(ApiInterfaceMonev.class);
        Call<Monev> call = apiInterfaceMonev.deleteMonev(monev_id);
        call.enqueue(new Callback<Monev>() {
            @Override
            public void onResponse(Call<Monev> call, Response<Monev> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Monev> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void updateMonev(int monev_id, String kategori){
        viewEditor.showProgress();
        ApiInterfaceMonev apiInterfaceMonev = ApiClient.getApiClient().create(ApiInterfaceMonev.class);
        Call<Monev> call = apiInterfaceMonev.updateMonev(monev_id, kategori);
        call.enqueue(new Callback<Monev>() {
            @Override
            public void onResponse(Call<Monev> call, Response<Monev> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Monev> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

}