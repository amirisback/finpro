package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.interfaces.lists.JudulListView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.networks.bridge.ApiClient;
import org.d3ifcool.service.networks.api.ApiInterfaceJudul;

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
public class JudulPresenter {

    private JudulWorkView viewEditor;
    private JudulListView viewResult;

    public JudulPresenter(JudulWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public JudulPresenter(JudulListView viewResult) {
        this.viewResult = viewResult;
    }

    public JudulPresenter(JudulWorkView viewEditor, JudulListView viewResult) {
        this.viewEditor = viewEditor;
        this.viewResult = viewResult;
    }

    public void createJudul (String judul_nama, int kategori_id, String judul_deskripsi, String nip_dosen, String judul_status){
        viewEditor.showProgress();
        ApiInterfaceJudul apiInterface = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<Judul> call = apiInterface.createJudul(judul_nama,kategori_id,judul_deskripsi,nip_dosen, judul_status);
        call.enqueue(new Callback<Judul>() {
            @Override
            public void onResponse(Call<Judul> call, Response<Judul> response) {
                viewEditor.hideProgress();
                viewEditor.onSuccesWorkJudul();
            }

            @Override
            public void onFailure(Call<Judul> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });

    }

    public void updateJudul (int id, String judul_nama, String judul_kategori, String judul_deskripsi, String nip_dosen){
        viewEditor.showProgress();
        ApiInterfaceJudul apiInterface = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<Judul> call = apiInterface.updateJudul(id, judul_nama,judul_kategori,judul_deskripsi);
        call.enqueue(new Callback<Judul>() {
            @Override
            public void onResponse(Call<Judul> call, Response<Judul> response) {
                viewEditor.hideProgress();
                viewEditor.onSuccesWorkJudul();
            }

            @Override
            public void onFailure(Call<Judul> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });

    }

    public void getJudul() {
        viewResult.showProgress();
        ApiInterfaceJudul apiInterfaceJudulPa = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<List<Judul>> call = apiInterfaceJudulPa.getJudul();
        call.enqueue(new Callback<List<Judul>>() {
            @Override
            public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                viewResult.hideProgress();
                viewResult.onGetListJudul(response.body());
            }

            @Override
            public void onFailure(Call<List<Judul>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void deleteJudul(int judul_id){
        viewEditor.showProgress();
        ApiInterfaceJudul apiInterfaceJudulPa = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<Judul> call = apiInterfaceJudulPa.deleteJudul(judul_id);
        call.enqueue(new Callback<Judul>() {
            @Override
            public void onResponse(Call<Judul> call, Response<Judul> response) {
                viewEditor.hideProgress();
                viewEditor.onSuccesWorkJudul();
            }

            @Override
            public void onFailure(Call<Judul> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void updateStatusJudul(int judul_id, String status_judul) {
        viewEditor.showProgress();
        ApiInterfaceJudul apiInterfaceJudul = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<Judul> call = apiInterfaceJudul.updateStatusJudul(judul_id, status_judul);
        call.enqueue(new Callback<Judul>() {
            @Override
            public void onResponse(Call<Judul> call, Response<Judul> response) {
                viewEditor.hideProgress();
                viewEditor.onSuccesWorkJudul();
            }

            @Override
            public void onFailure(Call<Judul> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });


    }

    public void searchJudulBy(String parameter, String query) {
        viewResult.showProgress();
        ApiInterfaceJudul apiInterfaceJudul = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<List<Judul>> call = apiInterfaceJudul.searchJudulBy(parameter,query);
        call.enqueue(new Callback<List<Judul>>() {
            @Override
            public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                viewResult.hideProgress();
                viewResult.onGetListJudul(response.body());
            }

            @Override
            public void onFailure(Call<List<Judul>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void searchJudulByTwo(String parameter1, String query1, String parameter2, String query2) {
        viewResult.showProgress();
        ApiInterfaceJudul apiInterfaceJudul = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<List<Judul>> call = apiInterfaceJudul.searchJudulByTwo(parameter1, query1, parameter2, query2);
        call.enqueue(new Callback<List<Judul>>() {
            @Override
            public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                viewResult.hideProgress();
                viewResult.onGetListJudul(response.body());
            }

            @Override
            public void onFailure(Call<List<Judul>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void searchJudulMahasiswaBy(String parameter, String query) {
        viewResult.showProgress();
        ApiInterfaceJudul apiInterfaceJudul = ApiClient.getApiClient().create(ApiInterfaceJudul.class);
        Call<List<Judul>> call = apiInterfaceJudul.searchJudulMahasiswaBy(parameter,query);
        call.enqueue(new Callback<List<Judul>>() {
            @Override
            public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                viewResult.hideProgress();
                viewResult.onGetListJudul(response.body());
            }

            @Override
            public void onFailure(Call<List<Judul>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }


}
