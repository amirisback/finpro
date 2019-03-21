package org.d3ifcool.service.presenters;

import android.content.Context;

import org.d3ifcool.service.interfaces.DosenLoginView;
import org.d3ifcool.service.interfaces.MahasiswaLoginView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.service.network.api.ApiInterfaceDosen;
import org.d3ifcool.service.network.api.ApiInterfaceMahasiswa;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 05/03/2019.
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
public class DataLoginPresenter {

    DosenLoginView viewDosen;
    MahasiswaLoginView viewMahasiswa;
    Context context;

    public DataLoginPresenter(MahasiswaLoginView viewMahasiswa, Context context) {
        this.viewMahasiswa = viewMahasiswa;
        this.context = context;
    }

    public DataLoginPresenter(DosenLoginView viewDosen, Context context) {
        this.viewDosen = viewDosen;
        this.context = context;
    }

    public void getDataDosenLogin(String dsn_nip){
        viewDosen.showProgress();
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDataDosenLogin(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewDosen.hideProgress();
                viewDosen.onRequestSuccess(response.body().getDsn_nama(), response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewDosen.hideProgress();
                viewDosen.onRequestError(t.getLocalizedMessage());
            }
        });
    }


    public void getDataMahasiswaLogin(String username){
        viewMahasiswa.showProgress();
        ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
        Call<Mahasiswa> call = apiInterfaceMahasiswa.getDataMahasiswaLogin(username);
        call.enqueue(new Callback<Mahasiswa>() {
            @Override
            public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                viewMahasiswa.hideProgress();
                viewMahasiswa.onRequestSuccess(response.message(), response.body());
            }

            @Override
            public void onFailure(Call<Mahasiswa> call, Throwable t) {
                viewMahasiswa.hideProgress();
                viewMahasiswa.onRequestError(t.getLocalizedMessage());
            }
        });

    }

}
