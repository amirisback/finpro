package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Informasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_INFORMASI_CREATE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_INFORMASI_DELETE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_INFORMASI_READ;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_INFORMASI_UPDATE;

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
public interface ApiInterfaceInformasi {

    @FormUrlEncoded
    @POST(URL_INFORMASI_CREATE)
    Call<Informasi> createInformasi (
            @Field("info_judul") String info_judul,
            @Field("info_deskripsi") String info_deskripsi
    );

    @FormUrlEncoded
    @POST(URL_INFORMASI_UPDATE)
    Call<Informasi> updateInformasi (
            @Field("id") int id,
            @Field("info_judul") String info_judul,
            @Field("info_deskripsi") String info_deskripsi

    );

    @GET(URL_INFORMASI_READ)
    Call<List<Informasi>> getInformasi();

    @FormUrlEncoded
    @POST(URL_INFORMASI_DELETE)
    Call<Informasi> deleteInformasi (
            @Field("id") int id
    );

}
