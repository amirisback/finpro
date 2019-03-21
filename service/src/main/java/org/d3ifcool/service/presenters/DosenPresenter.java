package org.d3ifcool.service.presenters;

import android.content.Context;

import org.d3ifcool.service.interfaces.DosenViewResult;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.service.network.api.ApiInterfaceDosen;

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

    private DosenViewResult viewResult;
    private Context context;

    public DosenPresenter(DosenViewResult viewResult, Context context) {
        this.viewResult = viewResult;
        this.context = context;
    }

    public void getDosen(){
        viewResult.showProgress();

        ApiInterfaceDosen apiInterface = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<List<Dosen>> call = apiInterface.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                viewResult.hideProgress();
                viewResult.onGetResultDataDosen(response.body());
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onErrorLoading(t.getMessage());
            }
        });
    }


}
