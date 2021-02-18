package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganSearchListView;
import org.d3ifcool.finpro.core.interfaces.lists.SiapSidangListView;
import org.d3ifcool.finpro.core.interfaces.objects.BimbinganView;
import org.d3ifcool.finpro.core.interfaces.works.ICreate;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.SiapSidang;
import org.d3ifcool.finpro.core.api.ApiService;
import org.d3ifcool.finpro.core.api.ApiClient;

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
public class BimbinganPresenter {
    private BimbinganListView viewResult;
    private BimbinganSearchListView viewResultSearch;
    private SiapSidangListView siapSidangListView;
    private ICreate viewEditor;
    private BimbinganView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public BimbinganPresenter(SiapSidangListView siapSidangListView) {
        this.siapSidangListView = siapSidangListView;
    }

    public BimbinganPresenter(BimbinganListView viewResult, ICreate viewEditor, BimbinganView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public BimbinganPresenter(BimbinganSearchListView viewResultSearch) {
        this.viewResultSearch = viewResultSearch;
    }

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganSearchListView viewResultSearch, ICreate viewEditor) {
        this.viewResult = viewResult;
        this.viewResultSearch = viewResultSearch;
        this.viewEditor = viewEditor;
    }

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganSearchListView viewResultSearch) {
        this.viewResult = viewResult;
        this.viewResultSearch = viewResultSearch;
    }

    public BimbinganPresenter(BimbinganListView viewResult) {
        this.viewResult = viewResult;
    }

    public BimbinganPresenter(ICreate viewEditor) {
        this.viewEditor = viewEditor;
    }

    public BimbinganPresenter(BimbinganView viewObject) {
        this.viewObject = viewObject;
    }

    public BimbinganPresenter(BimbinganListView viewResult, ICreate viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganView viewObject) {
        this.viewResult = viewResult;
        this.viewObject = viewObject;
    }

    public void createBimbingan(String bimbingan_review, String bimbingan_kehadiran, String bimbingan_tanggal, String bimbingan_status, int proyek_akhir_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.createBimbingan(bimbingan_review, bimbingan_kehadiran, bimbingan_tanggal, bimbingan_status ,proyek_akhir_id);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getBimbingan(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.getBimbingan();
            call.enqueue(new Callback<List<Bimbingan>>() {
                @Override
                public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListBimbingan(response.body());
                    } else {
                        viewResult.isEmptyListBimbingan();
                    }
                }

                @Override
                public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void updateBimbingan(String bimbingan_id, String bimbingan_review, String bimbingan_tanggal){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.updateBimbingan(bimbingan_id, bimbingan_review, bimbingan_tanggal);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteBimbingan(String bimbingan_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.deleteBimbingan(bimbingan_id);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }


    public void searchBimbinganAllBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.searchBimbinganAllBy(parameter, query);
            call.enqueue(new Callback<List<Bimbingan>>() {
                @Override
                public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListBimbingan(response.body());
                    } else {
                        viewResult.isEmptyListBimbingan();
                    }

                }

                @Override
                public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchBimbinganObjectBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.searchBimbinganBy(parameter, query);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewObject.onGetObjectBimbingan(response.body());
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewObject.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void searchBimbinganAllByTwo(String parameter1, String query1, String parameter2, String query2){

        if (connectionHelper.isConnected(context)){
            viewResultSearch.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.searchBimbinganAllByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<Bimbingan>>() {
                @Override
                public void onResponse(Call<List<Bimbingan>> call, Response<List<Bimbingan>> response) {
                    viewResultSearch.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResultSearch.onGetListBimbinganSearch(response.body());
                    } else {
                        viewResultSearch.isEmptyListBimbingan();
                    }

                }

                @Override
                public void onFailure(Call<List<Bimbingan>> call, Throwable t) {
                    viewResultSearch.hideProgress();
                    viewResultSearch.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }



    public void updateBimbinganStatus(String bimbingan_id, String bimbingan_status){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<Bimbingan> call = apiInterfaceBimbingan.updateBimbinganStatus(bimbingan_id, bimbingan_status);
            call.enqueue(new Callback<Bimbingan>() {
                @Override
                public void onResponse(Call<Bimbingan> call, Response<Bimbingan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Bimbingan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }


    public void searchSiapSidang(int jumlah_bimbingan){

        if (connectionHelper.isConnected(context)){
            siapSidangListView.showProgress();
            ApiService apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<SiapSidang>> call = apiInterfaceBimbingan.searchSiapSidang(jumlah_bimbingan);
            call.enqueue(new Callback<List<SiapSidang>>() {
                @Override
                public void onResponse(Call<List<SiapSidang>> call, Response<List<SiapSidang>> response) {
                    siapSidangListView.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        siapSidangListView.onGetListSiapSidang(response.body());
                    } else {
                        siapSidangListView.isEmptyListSiapSidang();
                    }
                }

                @Override
                public void onFailure(Call<List<SiapSidang>> call, Throwable t) {
                    siapSidangListView.hideProgress();
                    siapSidangListView.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

}
