package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.Judul;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_JUDUL_PA;


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
public interface ApiInterfaceJudulPa {

    @FormUrlEncoded
    @POST(URL_JUDUL_PA)
    Call<Judul> createJudul(
            @Field("judul_nama") String judul_nama,
            @Field("judul_kategori") String judul_kategori,
            @Field("judul_deskripsi") String judul_deskripsi,
            @Field("judul_status") String judul_status,
            @Field("nip_dosen") String nip_dosen
    );


    @FormUrlEncoded
    @POST(URL_JUDUL_PA)
    Call<Judul> updateJudul(
            @Field("judul_id") int judul_id,
            @Field("judul_nama") String judul_nama,
            @Field("judul_kategori") String judul_kategori,
            @Field("judul_deskripsi") String judul_deskripsi,
            @Field("judul_status") String judul_status,
            @Field("nip_dosen") String nip_dosen
    );

    @GET(URL_JUDUL_PA)
    Call<List<Judul>> getJudul();


//    @GET(URL_KATEGORI_JUDUL_PA_READ)
//    Call<Judul> getKategoriJudul(
//            @Field("kategori") String kategori
//    );

    @FormUrlEncoded
    @POST(URL_JUDUL_PA)
    Call<Judul> deleteJudul(@Field("judul_id") int judul_id);

}
