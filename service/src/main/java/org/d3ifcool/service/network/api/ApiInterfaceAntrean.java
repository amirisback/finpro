package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.Antrean;

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
public interface ApiInterfaceAntrean {
    @FormUrlEncoded
    @GET("antrean/getAntrean")
    Call<Antrean> getAntrean(
            @Field("id_antrian") int id_antrian,
            @Field("status_antrian") String status_antrian,
            @Field("id_judul") int id_judul,
            @Field("nim_mhs") String nim_mhs
    );

    @FormUrlEncoded
    @POST("antrean/updateAntrean")
    Call<Antrean> updateAntrean(
            @Field("id_antrian") int id_antrian,
            @Field("status_antrian") String status_antrian,
            @Field("id_judul") int id_judul,
            @Field("nim_mhs") String nim_mhs
    );

    @FormUrlEncoded
    @POST("antrean/createAntrean")
    Call<Antrean> createAntrean(
            @Field("status_antrian") String status_antrian,
            @Field("id_judul") int id_judul,
            @Field("nim_mhs") String nim_mhs


    );

    @FormUrlEncoded
    @POST("antrean/updateAntrean")
    Call<Antrean> updateAntrean(
            @Field("id_antrian") int id_antrian
    );
}
