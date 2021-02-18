package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.KegiatanListView;
import org.d3ifcool.finpro.core.interfaces.objects.KegiatanView;
import org.d3ifcool.finpro.core.interfaces.works.KegiatanWorkView;
import org.d3ifcool.finpro.core.models.Kegiatan;
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
 * Copyright (C) 23/06/2019.
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
public class KegiatanPresenter {

    private KegiatanListView viewResult;
    private KegiatanWorkView viewEditor;
    private KegiatanView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }


    public KegiatanPresenter(KegiatanListView viewResult, KegiatanWorkView viewEditor, KegiatanView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public KegiatanPresenter(KegiatanListView viewResult) {
        this.viewResult = viewResult;
    }

    public KegiatanPresenter(KegiatanWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public KegiatanPresenter(KegiatanListView viewResult, KegiatanWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public void createKegiatan(String kegiatan, String tanggal_mulai, String tanggal_berakhir, String pelaku, String keterangan){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKegiatan = ApiClient.getApiClient().create(ApiService.class);
            Call<Kegiatan> call = apiInterfaceKegiatan.createKegiatan(kegiatan, tanggal_mulai, tanggal_berakhir, pelaku, keterangan);
            call.enqueue(new Callback<Kegiatan>() {
                @Override
                public void onResponse(Call<Kegiatan> call, Response<Kegiatan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Kegiatan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });


        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void getKegiatan(){
        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceKegiatan = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Kegiatan>> call = apiInterfaceKegiatan.getKegiatan();
            call.enqueue(new Callback<List<Kegiatan>>() {
                @Override
                public void onResponse(Call<List<Kegiatan>> call, Response<List<Kegiatan>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListKegiatan(response.body());
                    } else {
                        viewResult.isEmptyListKegiatan();
                    }
                }

                @Override
                public void onFailure(Call<List<Kegiatan>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });

        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void updateKegiatan(int kegiatan_id, String kegiatan, String tanggal_mulai, String tanggal_berakhir, String pelaku, String keterangan){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKegiatan = ApiClient.getApiClient().create(ApiService.class);
            Call<Kegiatan> call = apiInterfaceKegiatan.updateKegiatan(kegiatan_id, kegiatan, tanggal_mulai, tanggal_berakhir, pelaku, keterangan);
            call.enqueue(new Callback<Kegiatan>() {
                @Override
                public void onResponse(Call<Kegiatan> call, Response<Kegiatan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Kegiatan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });


        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteKegiatan(int kegiatan_id){
        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceKegiatan = ApiClient.getApiClient().create(ApiService.class);
            Call<Kegiatan> call = apiInterfaceKegiatan.deleteKegiatan(kegiatan_id);
            call.enqueue(new Callback<Kegiatan>() {
                @Override
                public void onResponse(Call<Kegiatan> call, Response<Kegiatan> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Kegiatan> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });


        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }
    }

}
