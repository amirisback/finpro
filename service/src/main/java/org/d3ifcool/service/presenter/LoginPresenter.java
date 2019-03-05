package org.d3ifcool.service.presenter;

import org.d3ifcool.service.interfaces.LoginView;
import org.d3ifcool.service.models.Login;
import org.d3ifcool.service.network.ApiClient;
import org.d3ifcool.service.network.ApiInterfacesLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/03/2019.
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
public class LoginPresenter {

    private LoginView view;

    public LoginPresenter(LoginView view) {
        this.view = view;
    }

    public void getLogin(String username, String password){
        view.showProgress();

        ApiInterfacesLogin apiInterface = ApiClient.getApiClient().create(ApiInterfacesLogin.class);
        Call<Login> call = apiInterface.setLogin(username, password);

        call.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage(), response.body());
                    } else {
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

}