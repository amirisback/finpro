package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.Bimbingan;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_BIMBINGAN;

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
    @GET(URL_BIMBINGAN)
    Call<Bimbingan> createBimbingan(
            @Field("bimbingan_id") int id_bimbingan,
            @Field("bimbingan_review") String keterangan,
            @Field("bimbingan_judul") String lokasi,
            @Field("bimbingan_tanggal") String data_mhs,
            @Field("proyek_akhir_id") String id_judul
    );

    @GET(URL_BIMBINGAN)
    Call<Bimbingan> getBimbingan(
            @Field("bimbingan_id") int id_bimbingan,
            @Field("bimbingan_review") String bimbingan_review,
            @Field("bimbingan_judul") String bimbingan_judul,
            @Field("bimbingan_tanggal") String bimbingan_tanggal,
            @Field("proyek_akhir_id") String proyek_akhir_id
    );

    @POST(URL_BIMBINGAN)
    Call<Bimbingan> deleteBimbingan(@Field("bimbingan_review") String keterangan);

}
