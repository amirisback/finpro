package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Bimbingan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
public interface ApiInterfaceBimbingan {

    @FormUrlEncoded
    @POST("bimbingan/getBimbingan.php")
    Call<Bimbingan> getBimbingan(
            @Field("id_bimbingan") int id_bimbingan,
            @Field("keterangan") String keterangan,
            @Field("lokasi") String lokasi,
            @Field("data_mahasiswa") String data_mhs,
            @Field("id_judul") String id_judul
    );

    @FormUrlEncoded
    @POST("bimbingan/updateBimbingan.php")
    Call<Bimbingan> updateBimbingan(
            @Field("id_bimbingan") int id_bimbingan,
            @Field("keterangan") String keterangan,
            @Field("lokasi") String lokasi,
            @Field("data_mahasiswa") String data_mhs,
            @Field("id_judul") String id_judul
    );

    @FormUrlEncoded
    @POST("bimbingan/createBimbingan.php")
    Call<Bimbingan> createBimbingan(
            @Field("keterangan") String keterangan,
            @Field("lokasi") String lokasi,
            @Field("data_mahasiswa") String data_mhs,
            @Field("id_judul") String id_judul
    );

    @FormUrlEncoded
    @POST("bimbingan/getBimbingan.php")
    Call<Bimbingan> getBimbingan(@Field("id_bimbingan") String id_bimbignan);
}
