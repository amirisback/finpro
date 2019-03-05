package org.d3ifcool.service.network;

import org.d3ifcool.service.models.JudulPa;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_JUDUL_PA_CREATE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_JUDUL_PA_DELETE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_JUDUL_PA_READ;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_JUDUL_PA_UPDATE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_KATEGORI_JUDUL_PA_READ;

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
    @POST(URL_JUDUL_PA_CREATE)
    Call<JudulPa> createJudul(
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") String kategori,
            @Field("nip_dosen") String nip_dosen
    );


    @FormUrlEncoded
    @POST(URL_JUDUL_PA_UPDATE)
    Call<JudulPa> updateJudul(
            @Field("id_judul") int id_judul,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") String kategori,
            @Field("status") String status,
            @Field("nip_dosen") String nip_dosen
    );

    @GET(URL_JUDUL_PA_READ)
    Call<List<JudulPa>> getJudul();


//    @GET(URL_KATEGORI_JUDUL_PA_READ)
//    Call<JudulPa> getKategoriJudul(
//            @Field("kategori") String kategori
//    );

    @FormUrlEncoded
    @POST(URL_JUDUL_PA_DELETE)
    Call<JudulPa> deleteJudul(@Field("id_judul") int id_judul);

}
