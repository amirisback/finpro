package org.d3ifcool.service.presenters;

import android.content.Context;

import org.d3ifcool.service.interfaces.DosenViewEditor;
import org.d3ifcool.service.interfaces.DosenViewResult;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.service.network.api.ApiInterfaceDosen;

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
public class DosenPresenter {

    private DosenViewResult viewResult;
    private DosenViewEditor viewEditor;
    private Context context;

    public DosenPresenter(DosenViewResult viewResult, Context context) {
        this.viewResult = viewResult;
        this.context = context;
    }

    public DosenPresenter(DosenViewEditor viewEditor, Context context) {
        this.viewEditor = viewEditor;
        this.context = context;
    }

    public void getDosen(){
        viewResult.showProgress();

        ApiInterfaceDosen apiInterface = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<List<Dosen>> call = apiInterface.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                viewResult.hideProgress();
                viewResult.onGetResultDataDosen(response.body());
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onErrorLoading(t.getMessage());
            }
        });
    }

    public void createDosen(String nip, String nama, String kode, String kontak, String email){
        viewEditor.showProgress();

        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.createDosen(nip,nama,kode,kontak,email);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void deleteDosen(String nip){
        viewEditor.showProgress();

        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.deleteDosen(nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }


    public void UpdateDosen(String nip_lama, String nama_baru, String kode_baru, String kontak_baru, String email_baru) {
        viewEditor.showProgress();
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.updateDosen(nip_lama ,nama_baru, kode_baru, kontak_baru, email_baru);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }
}
