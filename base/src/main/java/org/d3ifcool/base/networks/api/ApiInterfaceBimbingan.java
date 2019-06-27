package org.d3ifcool.base.networks.api;

import org.d3ifcool.base.models.Bimbingan;
import org.d3ifcool.base.models.SiapSidang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER_1;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER_2;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_BIMBINGAN;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_1;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_2;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_ALL;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_SEARCH;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_SIAP_SIDANG;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_STATUS;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_BIMBINGAN;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_BIMBINGAN;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_PARAMS;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_QUERY;

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
public interface ApiInterfaceBimbingan {

    @FormUrlEncoded
    @POST(URL_BIMBINGAN)
    Call<Bimbingan> createBimbingan(
            @Field("bimbingan_review") String bimbingan_review,
            @Field("bimbingan_kehadiran") String bimbingan_kehadiran,
            @Field("bimbingan_tanggal") String bimbingan_tanggal,
            @Field("bimbingan_status") String bimbingan_status,
            @Field("proyek_akhir_id") int proyek_akhir_id

    );

    @FormUrlEncoded
    @POST(URL_BIMBINGAN + PATH_UPDATE + PARAMETER_BIMBINGAN)
    Call<Bimbingan> updateBimbingan(@Path(VAR_BIMBINGAN) String bimbingan_id,
                                    @Field("bimbingan_review") String bimbingan_review,
                                    @Field("bimbingan_tanggal") String bimbingan_tanggal
    );

    @GET(URL_BIMBINGAN)
    Call<List<Bimbingan>> getBimbingan();

    @POST(URL_BIMBINGAN + PATH_DELETE + PARAMETER_BIMBINGAN)
    Call<Bimbingan> deleteBimbingan(@Path(VAR_BIMBINGAN) String bimbingan_id);

    @GET(URL_BIMBINGAN + PATH_SEARCH + PATH_ALL + BASE_PARAMETER + PARAMETER_QUERY)
    Call<List<Bimbingan>> searchBimbinganAllBy(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

    @GET(URL_BIMBINGAN + PATH_SEARCH + PATH_ALL + BASE_PARAMETER + PARAMETER_QUERY)
    Call<Bimbingan> searchBimbinganBy(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

    @GET(URL_BIMBINGAN + PATH_SEARCH + PATH_ALL + BASE_PARAMETER_1 + PARAMETER_QUERY_1 + BASE_PARAMETER_2 + PARAMETER_QUERY_2)
    Call<List<Bimbingan>> searchBimbinganAllByTwo(
            @Path(VAR_PARAMS+"1") String parameter1,
            @Path(VAR_QUERY+"1") String query1,
            @Path(VAR_PARAMS+"2") String parameter2,
            @Path(VAR_QUERY+"2") String query2
    );

    @GET(URL_BIMBINGAN + PATH_SEARCH + PATH_SIAP_SIDANG + PARAMETER_BIMBINGAN)
    Call<List<SiapSidang>> searchSiapSidang(@Path(VAR_BIMBINGAN) int jumlah_bimbingan);

    @FormUrlEncoded
    @POST(URL_BIMBINGAN + PATH_UPDATE + PATH_STATUS + PARAMETER_BIMBINGAN)
    Call<Bimbingan> updateBimbinganStatus(@Path(VAR_BIMBINGAN) String bimbingan_id, @Field("bimbingan_status") String bimbingan_status);

}
