package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.KoorListView;
import org.d3ifcool.finpro.core.interfaces.objects.KoorView;
import org.d3ifcool.finpro.core.interfaces.works.KoorWorkView;
import org.d3ifcool.finpro.core.models.KoordinatorPa;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

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
public class KoorPresenter {

    private KoorView viewObject;
    private KoorListView viewResult;
    private KoorWorkView viewEditor;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public KoorPresenter(KoorView viewObject) {
        this.viewObject = viewObject;
    }

    public KoorPresenter(KoorListView viewResult) {
        this.viewResult = viewResult;
    }

    public KoorPresenter(KoorWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public void createKoor(String koor_nip , String koor_nama , String koor_kontak, String koor_foto, String koor_email){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService interfaceAdmin = ApiClient.getApiClient().create(ApiService.class);
            Call<KoordinatorPa>call = interfaceAdmin.createKoor(koor_nip,koor_nama,koor_kontak,koor_foto,koor_email);
            call.enqueue(new Callback<KoordinatorPa>() {
                @Override
                public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KoordinatorPa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateKoor(String username, String koor_nip, String koor_nama, String koor_kode, String koor_kontak, String koor_email){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService interfaceAdmin = ApiClient.getApiClient().create(ApiService.class);
            Call<KoordinatorPa>call = interfaceAdmin.updateKoor(username, koor_nip, koor_nama, koor_kode, koor_kontak,koor_email);
            call.enqueue(new Callback<KoordinatorPa>() {
                @Override
                public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KoordinatorPa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getKoor(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService interfaceAdmin = ApiClient.getApiClient().create(ApiService.class);
            Call<List<KoordinatorPa>> call = interfaceAdmin.getKoor();
            call.enqueue(new Callback<List<KoordinatorPa>>() {
                @Override
                public void onResponse(Call<List<KoordinatorPa>> call, Response<List<KoordinatorPa>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListKoor(response.body());
                    } else {
                        viewResult.isEmptyListKoor();
                    }

                }

                @Override
                public void onFailure(Call<List<KoordinatorPa>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void deleteKoor(String koor_nip){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiService.class);
            Call<KoordinatorPa>call = apiInterfaceKoorPa.deleteKoor(koor_nip);
            call.enqueue(new Callback<KoordinatorPa>() {
                @Override
                public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KoordinatorPa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getKoorByParameter(String koor_nip){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceKoorPa = ApiClient.getApiClient().create(ApiService.class);
            Call<KoordinatorPa> call = apiInterfaceKoorPa.getKoorByParameter(koor_nip);
            call.enqueue(new Callback<KoordinatorPa>() {
                @Override
                public void onResponse(Call<KoordinatorPa> call, Response<KoordinatorPa> response) {
                    viewObject.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewObject.onGetObjectKoor(response.body());
                    } else {
                        viewObject.isEmptyObjectKoor();
                    }

                }

                @Override
                public void onFailure(Call<KoordinatorPa> call, Throwable t) {
                    viewObject.hideProgress();
                    viewObject.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}

