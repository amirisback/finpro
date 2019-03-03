package org.d3ifcool.service.presenter;

import org.d3ifcool.service.models.Admin;
import org.d3ifcool.service.network.ApiClient;
import org.d3ifcool.service.network.ApiInterfaceAdmin;

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
public class AdminPresenter {


    public void CreateAdmin(String username, String password, String nip_admin, String nama_admin, String no_telp, String email){
        ApiInterfaceAdmin interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceAdmin.class);
        Call<Admin>call = interfaceAdmin.createAdmin(username,password,nip_admin,nama_admin,no_telp,email);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });
    }

    public void UpdateAdmin(String username, String password, String nip_admin, String nama_admin, String no_telp, String email){
        ApiInterfaceAdmin interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceAdmin.class);
        Call<Admin>call = interfaceAdmin.updateAdmin(username,password,nip_admin,nama_admin,no_telp,email);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });
    }

    public void GetAdmin(String username, String password, String nip_admin, String nama_admin, String no_telp, String email){
        ApiInterfaceAdmin interfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceAdmin.class);
        Call<Admin>call = interfaceAdmin.getAdmin(username,password,nip_admin,nama_admin,no_telp,email);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });
    }

    public void deleteAdmin(String username){
        ApiInterfaceAdmin apiInterfaceAdmin = ApiClient.getApiClient().create(ApiInterfaceAdmin.class);
        Call<Admin>call = apiInterfaceAdmin.deleteAdmin(username);
        call.enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {

            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {

            }
        });
    }
}

