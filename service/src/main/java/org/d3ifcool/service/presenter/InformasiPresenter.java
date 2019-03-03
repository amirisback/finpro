package org.d3ifcool.service.presenter;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.InformasiView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.network.ApiClient;
import org.d3ifcool.service.network.ApiInterfaceInformasi;

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
    private InformasiView view;
    private Context context;

    public InformasiPresenter(InformasiView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void createInformasi (String info_judul, String info_deskripsi) {
        view.showProgress();

        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<Informasi> call = apiInterface.createInformasi(info_judul, info_deskripsi);
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                view.showProgress();
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateInformasi (int id, String info_judul, String info_deskripsi) {
        view.showProgress();

        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<Informasi> call = apiInterface.updateInformasi(id, info_judul, info_deskripsi);
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                view.showProgress();
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void deleteInformasi (int id) {
        view.showProgress();

        ApiInterfaceInformasi apiInterface = ApiClient.getApiClient().create(ApiInterfaceInformasi.class);
        Call<Informasi> call = apiInterface.deleteInformasi(id);
        call.enqueue(new Callback<Informasi>() {
            @Override
            public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<Informasi> call, Throwable t) {
                view.showProgress();
                Toast.makeText(context, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
