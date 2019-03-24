package org.d3ifcool.service.presenters;

import org.d3ifcool.service.models.KoordinatorPa;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.service.network.api.ApiInterfaceKoorPa;

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


    public void createAdmin(String koor_nip , String koor_nama , String koor_kontak, String koor_foto, String koor_email){
        ApiInterfaceKoorPa interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceKoorPa.class);
        Call<KoordinatorPa>call = interfaceAdmin.createAdmin(koor_nip,koor_nama,koor_kontak,koor_foto,koor_email);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {

            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {

            }
        });
    }

    public void updateAdmin(String koor_nip , String koor_nama , String koor_kontak, String koor_foto, String koor_email){
        ApiInterfaceKoorPa interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceKoorPa.class);
        Call<KoordinatorPa>call = interfaceAdmin.updateAdmin(koor_nip,koor_nama,koor_kontak,koor_foto,koor_email);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {

            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {

            }
        });
    }

    public void getAdmin(){
        ApiInterfaceKoorPa interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceKoorPa.class);
        Call<List<KoordinatorPa>> call = interfaceAdmin.getAdmin();
        call.enqueue(new Callback<List<KoordinatorPa>>() {
            @Override
            public void onResponse(Call<List<KoordinatorPa>> call, Response<List<KoordinatorPa>> response) {

            }

            @Override
            public void onFailure(Call<List<KoordinatorPa>> call, Throwable t) {

            }
        });

    }

    public void deleteAdmin(String koor_nip){
        ApiInterfaceKoorPa apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiInterfaceKoorPa.class);
        Call<KoordinatorPa>call = apiInterfaceKoorPa.deleteAdmin(koor_nip);
        call.enqueue(new Callback<KoordinatorPa>() {
            @Override
            public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {

            }

            @Override
            public void onFailure(Call<KoordinatorPa> call, Throwable t) {

            }
        });
    }
}

