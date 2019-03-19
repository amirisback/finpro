package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Informasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_INFORMASI;

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
    @POST(URL_INFORMASI)
    Call<Informasi> createInformasi (
            @Field("informasi_judul") String informasi_judul,
            @Field("informasi_isi") String informasi_isi,
            @Field("penerbit") String penerbit,
            @Field("informasi_waktu") String informasi_waktu
    );

    @FormUrlEncoded
    @POST(URL_INFORMASI)
    Call<Informasi> updateInformasi (
            @Field("informasi_id") int informasi_id,
            @Field("informasi_judul") String informasi_judul,
            @Field("informasi_isi") String informasi_isi
    );

    @GET(URL_INFORMASI)
    Call<List<Informasi>> getInformasi();

    @FormUrlEncoded
    @POST(URL_INFORMASI)
    Call<Informasi> deleteInformasi (
            @Field("informasi_id") int informasi_id
    );

}
