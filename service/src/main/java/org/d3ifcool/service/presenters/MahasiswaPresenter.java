package org.d3ifcool.service.presenters;

import android.content.Context;

import org.d3ifcool.service.interfaces.MahasiswaViewResult;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.service.network.api.ApiInterfaceMahasiswa;

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
public class MahasiswaPresenter {
    private MahasiswaViewResult result;
    private Context context;

    public MahasiswaPresenter(MahasiswaViewResult result, Context context) {
        this.result = result;
        this.context = context;
    }

    public void getDosen(){
        result.showProgress();
        ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
        Call<List<Mahasiswa>> call = apiInterfaceMahasiswa.getMahasiswa();
        call.enqueue(new Callback<List<Mahasiswa>>() {
            @Override
            public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                result.hideProgress();
                result.onGetResultDataMahasiswa(response.body());
            }

            @Override
            public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                result.hideProgress();
                result.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
