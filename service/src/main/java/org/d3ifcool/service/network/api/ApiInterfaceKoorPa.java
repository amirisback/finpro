package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.KoordinatorPa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_DELETE;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_KOOR;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_UPDATE;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_KOORDINATOR_PA;

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
public interface ApiInterfaceKoorPa {
    @FormUrlEncoded
    @POST(URL_KOORDINATOR_PA)
    Call<KoordinatorPa> createAdmin(
            @Field("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @FormUrlEncoded
    @POST(URL_KOORDINATOR_PA + PARAMETER_UPDATE + PARAMETER_KOOR)
    Call<KoordinatorPa> updateAdmin(
            @Path("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @GET(URL_KOORDINATOR_PA)
    Call<List<KoordinatorPa>> getAdmin();

    @POST(URL_KOORDINATOR_PA + PARAMETER_DELETE + PARAMETER_KOOR)
    Call<KoordinatorPa> deleteAdmin(@Path("koor") String koor_nip);
}
