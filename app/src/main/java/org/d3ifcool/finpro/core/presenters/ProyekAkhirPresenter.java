package org.d3ifcool.finpro.core.presenters;


import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.objects.ProyekAkhirView;
import org.d3ifcool.finpro.core.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
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

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

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

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
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
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void getProyekAkhir(){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.getProyekAkhir();
            call.enqueue(new Callback<List<ProyekAkhir>>() {
                @Override
                public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListProyekAkhir(response.body());
                    } else {
                        viewResult.isEmptyListProyekAkhir();
                    }

                }

                @Override
                public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateProyekAkhir(int proyek_akhir_id, int id_judul, String mhs_nim, String dsn_nip, String nama_tim){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
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
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteProyekAkhir(int proyek_akhir_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
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
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateNilai(int proyek_akhir_id, double nilai_total){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
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
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateReviewerFinish(int proyek_akhir_id,String nip_dosen){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
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
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateReviewer(int proyek_akhir_id,String nip_dosen){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            Call<ProyekAkhir> call = apiInterfaceProyekAkhir.updateDosenReviewer(proyek_akhir_id, nip_dosen);
            call.enqueue(new Callback<ProyekAkhir>() {
                @Override
                public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                    viewEditor.hideProgress();
                }

                @Override
                public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void searchAllProyekAkhirBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchAllProyekAkhirBy(parameter, query);
            call.enqueue(new Callback<List<ProyekAkhir>>() {
                @Override
                public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListProyekAkhir(response.body());
                    } else {
                        viewResult.isEmptyListProyekAkhir();
                    }

                }

                @Override
                public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchAllProyekAkhirByTwo(String parameter1, String query1, String parameter2, String query2){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchAllProyekAkhirByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<ProyekAkhir>>() {
                @Override
                public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListProyekAkhir(response.body());
                    } else {
                        viewResult.isEmptyListProyekAkhir();
                    }

                }

                @Override
                public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchDistinctProyekAkhirBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchDistinctProyekAkhirBy(parameter, query);
            call.enqueue(new Callback<List<ProyekAkhir>>() {
                @Override
                public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListProyekAkhir(response.body());
                    } else {
                        viewResult.isEmptyListProyekAkhir();
                    }

                }

                @Override
                public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchDistinctProyekAkhirByTwo(String parameter1, String query1, String parameter2, String query2){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            Call<List<ProyekAkhir>> call = apiInterfaceProyekAkhir.searchDistinctProyekAkhirByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<ProyekAkhir>>() {
                @Override
                public void onResponse(Call<List<ProyekAkhir>> call, Response<List<ProyekAkhir>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListProyekAkhir(response.body());
                    } else {
                        viewResult.isEmptyListProyekAkhir();
                    }

                }

                @Override
                public void onFailure(Call<List<ProyekAkhir>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }







}
