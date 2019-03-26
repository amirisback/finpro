package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.KoordinatorPa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_KOOR;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_KOORDINATOR_PA;

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
public interface ApiInterfaceKoor {
    @FormUrlEncoded
    @POST(URL_KOORDINATOR_PA)
    Call<KoordinatorPa> createKoor(
            @Field("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @FormUrlEncoded
    @POST(URL_KOORDINATOR_PA + PARAMETER_UPDATE + PARAMETER_KOOR)
    Call<KoordinatorPa> updateKoor(
            @Path("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @GET(URL_KOORDINATOR_PA)
    Call<List<KoordinatorPa>> getKoor();

    @POST(URL_KOORDINATOR_PA + PARAMETER_DELETE + PARAMETER_KOOR)
    Call<KoordinatorPa> deleteKoor(@Path("koor") String koor_nip);

    @GET(URL_KOORDINATOR_PA + PARAMETER_KOOR)
    Call<KoordinatorPa> getKoorByParameter(@Path("koor") String username_koor);

}
