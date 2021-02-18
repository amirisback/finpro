package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.interfaces.lists.InformasiListView;
import org.d3ifcool.finpro.core.models.Informasi;
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
 * Copyright (C) 3/1/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class InformasiPresenter {
    private InformasiWorkView viewEditor;
    private InformasiListView viewResult;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public InformasiPresenter(InformasiWorkView view) {
        this.viewEditor = view;
    }

    public InformasiPresenter(InformasiListView viewMain) {
        this.viewResult = viewMain;
    }

    public void createInformasi (String informasi_judul, String informasi_isi, String penerbit) {

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Informasi> call = apiInterface.createInformasi(informasi_judul, informasi_isi, penerbit);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }


    public void updateInformasi (int informasi_id, String informasi_judul, String informasi_isi) {
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Informasi> call = apiInterface.updateInformasi(informasi_id, informasi_judul, informasi_isi);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewEditor.showProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteInformasi(int informasi_id) {
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Informasi> call = apiInterface.deleteInformasi(informasi_id);
            call.enqueue(new Callback<Informasi>() {
                @Override
                public void onResponse(Call<Informasi> call, Response<Informasi> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Informasi> call, Throwable t) {
                    viewEditor.showProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void getInformasi (){
        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Informasi>> call = apiInterface.getInformasi();
            call.enqueue(new Callback<List<Informasi>>() {
                @Override
                public void onResponse(Call<List<Informasi>> call, Response<List<Informasi>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListInformasi(response.body());
                    } else {
                        viewResult.isEmptyListInformasi();
                    }
                }

                @Override
                public void onFailure(Call<List<Informasi>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
