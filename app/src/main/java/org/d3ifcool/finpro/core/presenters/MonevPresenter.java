package org.d3ifcool.finpro.core.presenters;


import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.MonevListView;
import org.d3ifcool.finpro.core.interfaces.objects.MonevView;
import org.d3ifcool.finpro.core.interfaces.works.MonevWorkView;
import org.d3ifcool.finpro.core.models.Monev;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

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
public class MonevPresenter {

    private MonevListView viewResult;
    private MonevWorkView viewEditor;
    private MonevView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public MonevPresenter(MonevListView viewResult, MonevWorkView viewEditor, MonevView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public MonevPresenter(MonevListView viewResult, MonevWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public MonevPresenter(MonevWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public MonevPresenter(MonevListView viewResult) {
        this.viewResult = viewResult;
    }

    public MonevPresenter(MonevView viewObject) {
        this.viewObject = viewObject;
    }

    public void createMonev(String monev_kategori, String jumlah_bimbingan) {

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMonev = ApiClient.getApiClient().create(ApiService.class);
            Call<Monev> call = apiInterfaceMonev.createMonev(monev_kategori, jumlah_bimbingan);
            call.enqueue(new Callback<Monev>() {
                @Override
                public void onResponse(Call<Monev> call, Response<Monev> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Monev> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getMonev(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceMonev = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Monev>> call = apiInterfaceMonev.getMonev();
            call.enqueue(new Callback<List<Monev>>() {
                @Override
                public void onResponse(Call<List<Monev>> call, Response<List<Monev>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListMonev(response.body());
                    } else {
                        viewResult.isEmptyListMonev();
                    }

                }

                @Override
                public void onFailure(Call<List<Monev>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }




    }

    public void deleteMonev(int monev_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMonev = ApiClient.getApiClient().create(ApiService.class);
            Call<Monev> call = apiInterfaceMonev.deleteMonev(monev_id);
            call.enqueue(new Callback<Monev>() {
                @Override
                public void onResponse(Call<Monev> call, Response<Monev> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Monev> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateMonev(int monev_id, String kategori, String jumlah_bimbingan){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceMonev = ApiClient.getApiClient().create(ApiService.class);
            Call<Monev> call = apiInterfaceMonev.updateMonev(monev_id, kategori, jumlah_bimbingan);
            call.enqueue(new Callback<Monev>() {
                @Override
                public void onResponse(Call<Monev> call, Response<Monev> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Monev> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

}