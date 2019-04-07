package org.d3ifcool.service.presenters;


import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.objects.ProyekAkhirView;
import org.d3ifcool.service.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.networks.api.ApiInterfaceProyekAkhir;
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
 * Copyright (C) 24/03/2019.
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
public class ProyekAkhirPresenter {

    private ProyekAkhirView viewObject;
    private ProyekAkhirWorkView viewEditor;
    private ProyekAkhirListView viewResult;

    public ProyekAkhirPresenter(ProyekAkhirView viewObject, ProyekAkhirWorkView viewEditor, ProyekAkhirListView viewResult) {
        this.viewObject = viewObject;
        this.viewEditor = viewEditor;
        this.viewResult = viewResult;
    }

    public ProyekAkhirPresenter(ProyekAkhirWorkView viewEditor, ProyekAkhirListView viewResult) {
        this.viewEditor = viewEditor;
        this.viewResult = viewResult;
    }

    public ProyekAkhirPresenter(ProyekAkhirView viewObject) {
        this.viewObject = viewObject;
    }

    public ProyekAkhirPresenter(ProyekAkhirWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public ProyekAkhirPresenter(ProyekAkhirListView viewResult) {
        this.viewResult = viewResult;
    }

    public void createProyekAkhir(int judul_id, String mhs_nim, String nama_tim ){
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<ProyekAkhir> call = apiInterfaceProyekAkhir.createProyekAkhir(judul_id, mhs_nim, nama_tim);
        call.enqueue(new Callback<ProyekAkhir>() {
            @Override
            public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });

    }

    public void getProyekAkhir(){
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.getProyekAkhir();
        call.enqueue(new Callback<List<ProyekAkhir>>() {
            @Override
            public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                viewResult.hideProgress();
                viewResult.onGetListProyekAkhir(response.body());
            }

            @Override
            public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void updateProyekAkhir(int proyek_akhir_id, int id_judul, String mhs_nim, String dsn_nip, String nama_tim){
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<ProyekAkhir> call = apiInterfaceProyekAkhir.updateProyekAkhir(proyek_akhir_id, id_judul, mhs_nim, dsn_nip, nama_tim);
        call.enqueue(new Callback<ProyekAkhir>() {
            @Override
            public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void deleteProyekAkhir(int proyek_akhir_id){
        viewEditor.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<ProyekAkhir> call = apiInterfaceProyekAkhir.deleteProyekAkhir(proyek_akhir_id);
        call.enqueue(new Callback<ProyekAkhir>() {
            @Override
            public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void updateNilai(int proyek_akhir_id, int nilai_total){
        viewEditor.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<ProyekAkhir> call = apiInterfaceProyekAkhir.updateNilai(proyek_akhir_id, nilai_total);
        call.enqueue(new Callback<ProyekAkhir>() {
            @Override
            public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void updateReviewerFinish(int proyek_akhir_id,String nip_dosen){
        viewEditor.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<ProyekAkhir> call = apiInterfaceProyekAkhir.updateDosenReviewer(proyek_akhir_id, nip_dosen);
        call.enqueue(new Callback<ProyekAkhir>() {
            @Override
            public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });

    }

    public void updateReviewer(int proyek_akhir_id,String nip_dosen){
        viewEditor.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<ProyekAkhir> call = apiInterfaceProyekAkhir.updateDosenReviewer(proyek_akhir_id, nip_dosen);
        call.enqueue(new Callback<ProyekAkhir>() {
            @Override
            public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                viewEditor.hideProgress();
            }

            @Override
            public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });

    }

    public void searchAllProyekAkhirBy(String parameter, String query){
        viewResult.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchAllProyekAkhirBy(parameter, query);
        call.enqueue(new Callback<List<ProyekAkhir>>() {
            @Override
            public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                viewResult.hideProgress();
                viewResult.onGetListProyekAkhir(response.body());
            }

            @Override
            public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void searchAllProyekAkhirByTwo(String parameter1, String query1, String parameter2, String query2){
        viewResult.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchAllProyekAkhirByTwo(parameter1, query1, parameter2, query2);
        call.enqueue(new Callback<List<ProyekAkhir>>() {
            @Override
            public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                viewResult.hideProgress();
                viewResult.onGetListProyekAkhir(response.body());
            }

            @Override
            public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void searchDistinctProyekAkhirBy(String parameter, String query){
        viewResult.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchDistinctProyekAkhirBy(parameter, query);
        call.enqueue(new Callback<List<ProyekAkhir>>() {
            @Override
            public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                viewResult.hideProgress();
                viewResult.onGetListProyekAkhir(response.body());
            }

            @Override
            public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void searchDistinctProyekAkhirByTwo(String parameter1, String query1, String parameter2, String query2){
        viewResult.showProgress();
        ApiInterfaceProyekAkhir apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiInterfaceProyekAkhir.class);
        Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchDistinctProyekAkhirByTwo(parameter1, query1, parameter2, query2);
        call.enqueue(new Callback<List<ProyekAkhir>>() {
            @Override
            public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                viewResult.hideProgress();
                viewResult.onGetListProyekAkhir(response.body());
            }

            @Override
            public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }







}
