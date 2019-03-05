package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Dosen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_DOSEN_CREATE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_DOSEN_DELETE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_DOSEN_READ;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_DOSEN_UPDATE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_LOGIN_DATA_DOSEN;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 26/02/2019.
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
public interface ApiInterfaceDosen {

    @FormUrlEncoded
    @POST(URL_DOSEN_CREATE)
    Call<Dosen> createDosen(
            @Field("nama_d") String nama_d,
            @Field("foto") String foto,
            @Field("email_d") String email_d,
            @Field("kontak_d") String kontak_d,
            @Field("limit") int limit,
            @Field("username_dosen") String username_dosen
    );

    @FormUrlEncoded
    @POST(URL_DOSEN_UPDATE)
    Call<Dosen> updateDosen(
            @Field("nip_dosen") int nip_dosen,
            @Field("nama_d") String nama_d,
            @Field("foto") String foto,
            @Field("email_d") String email_d,
            @Field("kontak_d") String kontak_d,
            @Field("limit") int limit,
            @Field("username_dosen") String username_dosen
    );

    @FormUrlEncoded
    @POST(URL_DOSEN_DELETE)
    Call<Dosen> deleteDosen(@Field("nip_dosen") int nip_dosen);

    @FormUrlEncoded
    @GET(URL_DOSEN_READ)
    Call<List<Dosen>> getDosen();

    @FormUrlEncoded
    @POST(URL_LOGIN_DATA_DOSEN)
    Call<Dosen> getDataDosenLogin(@Field("username_dosen") String username_dosen);

}