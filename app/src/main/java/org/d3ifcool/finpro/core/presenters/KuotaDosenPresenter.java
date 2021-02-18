package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.KuotaDosenListView;
import org.d3ifcool.finpro.core.interfaces.works.KuotaDosenWorkView;
import org.d3ifcool.finpro.core.models.KuotaDosen;
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
 * Copyright (C) 27/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.base.presenters
 */
public class KuotaDosenPresenter {

    private KuotaDosenListView viewResult;
    private KuotaDosenWorkView viewEditor;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public KuotaDosenPresenter(KuotaDosenListView viewResult, KuotaDosenWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public KuotaDosenPresenter(KuotaDosenListView viewResult) {
        this.viewResult = viewResult;
    }

    public KuotaDosenPresenter(KuotaDosenWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public void createKuota(String kuota_variable, String kuota_value){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKuotaDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<KuotaDosen> call = apiInterfaceKuotaDosen.createKuotaDosen(kuota_variable, kuota_value);
            call.enqueue(new Callback<KuotaDosen>() {
                @Override
                public void onResponse(Call<KuotaDosen> call, Response<KuotaDosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KuotaDosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getKuota(){
        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceKuotaDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<List<KuotaDosen>> call = apiInterfaceKuotaDosen.getKuotaDosen();
            call.enqueue(new Callback<List<KuotaDosen>>() {
                @Override
                public void onResponse(Call<List<KuotaDosen>> call, Response<List<KuotaDosen>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListKuota(response.body());
                    } else {
                        viewResult.isEmptyListKuota();
                    }
                    viewResult.onGetListKuota(response.body());
                }

                @Override
                public void onFailure(Call<List<KuotaDosen>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });


        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void updateKuota(int kuota_id, String kuota_variable, String kuota_value){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKuotaDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<KuotaDosen> call = apiInterfaceKuotaDosen.updateKuotaDosen(kuota_id,kuota_variable, kuota_value);
            call.enqueue(new Callback<KuotaDosen>() {
                @Override
                public void onResponse(Call<KuotaDosen> call, Response<KuotaDosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KuotaDosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteKuota(int kuota_id){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKuotaDosen = ApiClient.getApiClient().create(ApiService.class);
            Call<KuotaDosen> call = apiInterfaceKuotaDosen.deleteKuotaDosen(kuota_id);
            call.enqueue(new Callback<KuotaDosen>() {
                @Override
                public void onResponse(Call<KuotaDosen> call, Response<KuotaDosen> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KuotaDosen> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
