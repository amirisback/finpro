package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.Notifikasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_NOTIFIKASI;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_NOTIFIKASI;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_NOTIFIKASI;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/04/2019.
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
public interface ApiInterfaceNotifikasi {

    @FormUrlEncoded
    @POST(URL_NOTIFIKASI)
    Call<Notifikasi> createNotifikasi(
            @Field("token_pesan") String token_pesan,
            @Field("dari") String dari,
            @Field("untuk") String untuk,
            @Field("info_kategori") String info_kategori,
            @Field("deskripsi") String deskripsi,
            @Field("status") String status
    );

    @FormUrlEncoded
    @POST(URL_NOTIFIKASI + PATH_UPDATE + PARAMETER_NOTIFIKASI)
    Call<Notifikasi> updateNotifikasi(
            @Path(VAR_NOTIFIKASI) int notifikasi_id,
            @Field("token_pesan") String token_pesan,
            @Field("dari") String dari,
            @Field("untuk") String untuk,
            @Field("info_kategori") String info_kategori,
            @Field("deskripsi") String deskripsi,
            @Field("status") String status
    );

    @GET(URL_NOTIFIKASI)
    Call<List<Notifikasi>> getNotifikasi();

    @POST(URL_NOTIFIKASI + PATH_DELETE + PARAMETER_NOTIFIKASI)
    Call<Notifikasi> deleteNotifikasi(@Path(VAR_NOTIFIKASI) int notifikasi_id);

}
