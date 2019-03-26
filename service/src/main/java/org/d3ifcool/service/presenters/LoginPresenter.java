package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.objects.LoginView;
import org.d3ifcool.service.models.User;
import org.d3ifcool.service.networks.bridge.ApiClient;
import org.d3ifcool.service.networks.api.ApiInterfaceUser;

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
        ApiInterfaceUser apiInterface = ApiClient.getApiClient().create(ApiInterfaceUser.class);
        Call<User> call = apiInterface.setLogin(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body());
                    } else {
                        view.onFailed(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                view.hideProgress();
                view.onFailed(t.getLocalizedMessage());
            }
        });
    }

}
