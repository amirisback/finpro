package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.lists.NotifikasiListView;
import org.d3ifcool.service.interfaces.objects.NotifikasiView;
import org.d3ifcool.service.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.service.models.Notifikasi;
import org.d3ifcool.service.networks.api.ApiInterfaceNotifikasi;
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
public class NotifikasiPresenter {

    private NotifikasiListView viewResult;
    private NotifikasiWorkView viewEditor;
    private NotifikasiView viewObject;

    public NotifikasiPresenter(NotifikasiListView viewResult, NotifikasiWorkView viewEditor, NotifikasiView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public NotifikasiPresenter(NotifikasiListView viewResult) {
        this.viewResult = viewResult;
    }

    public NotifikasiPresenter(NotifikasiWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public NotifikasiPresenter(NotifikasiView viewObject) {
        this.viewObject = viewObject;
    }

    public NotifikasiPresenter(NotifikasiListView viewResult, NotifikasiWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public void createNotifikasi(String notifikasi_tanggal, String notifikasi_kategori, String notifikasi_deskripsi, String notifikasi_dari, String notifikasi_untuk, Boolean notifikasi_baca){
        viewEditor.showProgress();
        ApiInterfaceNotifikasi apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiInterfaceNotifikasi.class);
        Call<Notifikasi> call = apiInterfaceNotifikasi.createNotifikasi(notifikasi_tanggal, notifikasi_kategori, notifikasi_deskripsi, notifikasi_dari, notifikasi_untuk, notifikasi_baca);
        call.enqueue(new Callback<Notifikasi>() {
            @Override
            public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Notifikasi> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void getNotifikasi(){
        viewResult.showProgress();
        ApiInterfaceNotifikasi apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiInterfaceNotifikasi.class);
        Call<List<Notifikasi>> call = apiInterfaceNotifikasi.getNotifikasi();
        call.enqueue(new Callback<List<Notifikasi>>() {
            @Override
            public void onResponse(Call<List<Notifikasi>> call, Response<List<Notifikasi>> response) {
                viewResult.hideProgress();
                viewResult.onGetListNotifikasi(response.body());
            }

            @Override
            public void onFailure(Call<List<Notifikasi>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void deleteNotifikasi(int notifikasi_id){
        viewEditor.showProgress();
        ApiInterfaceNotifikasi apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiInterfaceNotifikasi.class);
        Call<Notifikasi> call = apiInterfaceNotifikasi.deleteNotifikasi(notifikasi_id);
        call.enqueue(new Callback<Notifikasi>() {
            @Override
            public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Notifikasi> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }


    public void updateNotifikasi(int notifikasi_id, String notifikasi_tanggal, String notifikasi_kategori, String notifikasi_deskripsi, String notifikasi_dari, String notifikasi_untuk, Boolean notifikasi_baca){
        viewEditor.showProgress();
        ApiInterfaceNotifikasi apiInterfaceNotifikasi = ApiClient.getApiClient().create(ApiInterfaceNotifikasi.class);
        Call<Notifikasi> call = apiInterfaceNotifikasi.updateNotifikasi(notifikasi_id, notifikasi_tanggal, notifikasi_kategori, notifikasi_deskripsi, notifikasi_dari, notifikasi_untuk, notifikasi_baca);
        call.enqueue(new Callback<Notifikasi>() {
            @Override
            public void onResponse(Call<Notifikasi> call, Response<Notifikasi> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Notifikasi> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }
        });
    }

}
