package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.lists.KoorListView;
import org.d3ifcool.service.interfaces.objects.KoorView;
import org.d3ifcool.service.interfaces.works.KoorWorkView;
import org.d3ifcool.service.models.KoordinatorPa;
import org.d3ifcool.service.networks.bridge.ApiClient;
import org.d3ifcool.service.networks.api.ApiInterfaceKoor;

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
public class KoorPresenter {

    private KoorView viewObject;
    private KoorListView viewResult;
    private KoorWorkView viewEditor;

    public KoorPresenter(KoorView viewObject) {
        this.viewObject = viewObject;
    }

    public KoorPresenter(KoorListView viewResult) {
        this.viewResult = viewResult;
    }

    public KoorPresenter(KoorWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public void createKoor(String koor_nip , String koor_nama , String koor_kontak, String koor_foto, String koor_email){
        ApiInterfaceKoor interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceKoor.class);
        Call<KoordinatorPa>call = interfaceAdmin.createKoor(koor_nip,koor_nama,koor_kontak,koor_foto,koor_email);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {

            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {

            }
        });
    }

    public void updateKoor(String koor_nip , String koor_nama , String koor_kontak, String koor_foto, String koor_email){
        ApiInterfaceKoor interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceKoor.class);
        Call<KoordinatorPa>call = interfaceAdmin.updateKoor(koor_nip,koor_nama,koor_kontak,koor_foto,koor_email);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {

            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {

            }
        });
    }

    public void getKoor(){
        ApiInterfaceKoor interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceKoor.class);
        Call<List<KoordinatorPa>> call = interfaceAdmin.getKoor();
        call.enqueue(new Callback<List<KoordinatorPa>>() {
            @Override
            public void onResponse(Call<List<KoordinatorPa>> call, Response<List<KoordinatorPa>> response) {

            }

            @Override
            public void onFailure(Call<List<KoordinatorPa>> call, Throwable t) {

            }
        });

    }

    public void deleteKoor(String koor_nip){
        ApiInterfaceKoor apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiInterfaceKoor.class);
        Call<KoordinatorPa>call = apiInterfaceKoorPa.deleteKoor(koor_nip);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {

            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {

            }
        });
    }

    public void getKoorByParameter(String koor_nip){
        ApiInterfaceKoor apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiInterfaceKoor.class);
        Call<KoordinatorPa> call = apiInterfaceKoorPa.getKoorByParameter(koor_nip);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {
                viewObject.hideProgress();
                viewObject.onGetObjectKoor(response.body());
            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {
                viewObject.hideProgress();
                viewObject.onFailed(t.getLocalizedMessage());
            }
        });
    }

}

