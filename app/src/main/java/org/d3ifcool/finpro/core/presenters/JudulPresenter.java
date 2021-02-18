package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.models.Judul;
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
public class JudulPresenter {

    private JudulWorkView viewEditor;
    private JudulListView viewResult;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

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

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void updateJudul (int id, String judul_nama, int kategori_id, String judul_deskripsi){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterface = ApiClient.getApiClient().create(ApiService.class);
            Call<Judul> call = apiInterface.updateJudul(id, judul_nama,kategori_id,judul_deskripsi);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void getJudul() {

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceJudulPa = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Judul>> call = apiInterfaceJudulPa.getJudul();
            call.enqueue(new Callback<List<Judul>>() {
                @Override
                public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListJudul(response.body());
                    } else {
                        viewResult.isEmptyListJudul();
                    }
                }

                @Override
                public void onFailure(Call<List<Judul>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteJudul(int judul_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceJudulPa = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateStatusJudul(int judul_id, String status_judul) {

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceJudul = ApiClient.getApiClient().create(ApiService.class);
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
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }




    }

    public void searchJudulBy(String parameter, String query) {

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceJudul = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Judul>> call = apiInterfaceJudul.searchJudulBy(parameter,query);
            call.enqueue(new Callback<List<Judul>>() {
                @Override
                public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListJudul(response.body());
                    } else {
                        viewResult.isEmptyListJudul();
                    }

                }

                @Override
                public void onFailure(Call<List<Judul>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchJudulByTwo(String parameter1, String query1, String parameter2, String query2) {

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceJudul = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Judul>> call = apiInterfaceJudul.searchJudulByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<Judul>>() {
                @Override
                public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListJudul(response.body());
                    } else {
                        viewResult.isEmptyListJudul();
                    }

                }

                @Override
                public void onFailure(Call<List<Judul>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchJudulMahasiswaBy(String parameter, String query) {

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceJudul = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Judul>> call = apiInterfaceJudul.searchJudulMahasiswaBy(parameter,query);
            call.enqueue(new Callback<List<Judul>>() {
                @Override
                public void onResponse(Call<List<Judul>> call, Response<List<Judul>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListJudul(response.body());
                    } else {
                        viewResult.isEmptyListJudul();
                    }

                }

                @Override
                public void onFailure(Call<List<Judul>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }


}
