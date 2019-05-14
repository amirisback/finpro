package org.d3ifcool.base.networks.api;

import org.d3ifcool.base.models.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_LOGIN;

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
public interface ApiInterfaceUser {

    @FormUrlEncoded
    @POST(URL_LOGIN)
    Call<User> setLogin(
            @Field("username") String username,
            @Field("password") String password);

}
