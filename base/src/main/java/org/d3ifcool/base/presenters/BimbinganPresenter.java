package org.d3ifcool.base.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.base.R;
import org.d3ifcool.base.helpers.ConnectionHelper;
import org.d3ifcool.base.interfaces.lists.BimbinganListView;
import org.d3ifcool.base.interfaces.objects.BimbinganView;
import org.d3ifcool.base.interfaces.works.BimbinganWorkView;
import org.d3ifcool.base.models.Bimbingan;
import org.d3ifcool.base.networks.api.ApiInterfaceBimbingan;
import org.d3ifcool.base.networks.bridge.ApiClient;

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
    private BimbinganWorkView viewEditor;
    private BimbinganView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganWorkView viewEditor, BimbinganView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public BimbinganPresenter(BimbinganListView viewResult) {
        this.viewResult = viewResult;
    }

    public BimbinganPresenter(BimbinganWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public BimbinganPresenter(BimbinganView viewObject) {
        this.viewObject = viewObject;
    }

    public BimbinganPresenter(BimbinganListView viewResult, BimbinganWorkView viewEditor) {
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
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
            viewResult.showProgress();
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
            Call<List<Bimbingan>> call = apiInterfaceBimbingan.searchBimbinganAllByTwo(parameter1, query1, parameter2, query2);
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



    public void updateBimbinganStatus(String bimbingan_id, String bimbingan_status){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiInterfaceBimbingan apiInterfaceBimbingan = ApiClient.getApiClient().create(ApiInterfaceBimbingan.class);
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
}
