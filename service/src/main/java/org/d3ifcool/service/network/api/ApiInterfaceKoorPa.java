package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.KoordinatorPa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

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
    @POST("admin/getAdmin.php")
    Call<KoordinatorPa> getAdmin(
            @Field("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @FormUrlEncoded
    @POST("admin/getAdmin.php")
    Call<KoordinatorPa> updateAdmin(
            @Field("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @FormUrlEncoded
    @GET("admin/getAdmin.php")
    Call<KoordinatorPa> createAdmin(
            @Field("koor_nip") String koor_nip,
            @Field("koor_nama") String koor_nama,
            @Field("koor_kontak") String koor_kontak,
            @Field("koor_foto") String koor_foto,
            @Field("koor_email") String koor_email
    );

    @FormUrlEncoded
    @POST("admin/getAdmin.php")
    Call<KoordinatorPa> deleteAdmin(
            @Field("koor_nip") String koor_nip
    );
}
