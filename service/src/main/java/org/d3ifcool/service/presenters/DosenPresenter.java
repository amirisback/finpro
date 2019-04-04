package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.objects.DosenPembimbingView;
import org.d3ifcool.service.interfaces.objects.DosenReviewerView;
import org.d3ifcool.service.interfaces.objects.DosenView;
import org.d3ifcool.service.interfaces.works.DosenWorkView;
import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.networks.bridge.ApiClient;
import org.d3ifcool.service.networks.api.ApiInterfaceDosen;

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

    private DosenListView viewResult;
    private DosenWorkView viewEditor;
    private DosenView viewObject;
    private DosenPembimbingView viewObjectPembimbing;
    private DosenReviewerView viewObjectReviewer;


    public DosenPresenter(DosenListView viewResult) {
        this.viewResult = viewResult;
    }

    public DosenPresenter(DosenWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public DosenPresenter(DosenView viewObject) {
        this.viewObject = viewObject;
    }

    public DosenPresenter(DosenListView viewResult, DosenView viewObject) {
        this.viewResult = viewResult;
        this.viewObject = viewObject;
    }

    public DosenPresenter(DosenPembimbingView viewObjectPembimbing, DosenReviewerView viewObjectReviewer) {
        this.viewObjectPembimbing = viewObjectPembimbing;
        this.viewObjectReviewer = viewObjectReviewer;
    }

    public void getDosen(){
        viewResult.showProgress();

        ApiInterfaceDosen apiInterface = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<List<Dosen>> call = apiInterface.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                viewResult.hideProgress();
                viewResult.onGetListDosen(response.body());
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
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

    public void getDosenByParameter(String dsn_nip){
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewObject.hideProgress();
                viewObject.onGetObjectDosen(response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewObject.hideProgress();
                viewObject.onFailed(t.getLocalizedMessage());
            }
        });
    }


    public void getDosenPembimbing(String dsn_nip){
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewObjectPembimbing.hideProgress();
                viewObjectPembimbing.onGetObjectDosenPembimbing(response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewObjectPembimbing.hideProgress();
                viewObjectPembimbing.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void getDosenReviewer(String dsn_nip){
        ApiInterfaceDosen apiInterfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<Dosen> call = apiInterfaceDosen.getDosenByParameter(dsn_nip);
        call.enqueue(new Callback<Dosen>() {
            @Override
            public void onResponse(Call<Dosen> call, Response<Dosen> response) {
                viewObjectReviewer.hideProgress();
                viewObjectReviewer.onGetObjectDosenReviewer(response.body());
            }

            @Override
            public void onFailure(Call<Dosen> call, Throwable t) {
                viewObjectReviewer.hideProgress();
                viewObjectReviewer.onFailed(t.getLocalizedMessage());
            }
        });
    }
}
