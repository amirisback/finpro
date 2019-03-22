package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.Judul;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_DELETE;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_DOSEN;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_JUDUL_PA;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_SORTBY;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.PARAMETER_UPDATE;
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
public interface ApiInterfaceJudul {

    @FormUrlEncoded
    @POST(URL_JUDUL_PA)
    Call<Judul> createJudul(
            @Field("judul_nama") String judul_nama,
            @Field("judul_kategori") String judul_kategori,
            @Field("judul_deskripsi") String judul_deskripsi,
            @Field("nip_dosen") String nip_dosen
    );


    @FormUrlEncoded
    @POST(URL_JUDUL_PA + PARAMETER_UPDATE + PARAMETER_JUDUL_PA)
    Call<Judul> updateJudul(
            @Path("judul") int judul_id,
            @Field("judul_nama") String judul_nama,
            @Field("judul_kategori") String judul_kategori,
            @Field("judul_deskripsi") String judul_deskripsi
    );

    @GET(URL_JUDUL_PA)
    Call<List<Judul>> getJudul();

    @GET(URL_JUDUL_PA + PARAMETER_SORTBY + PARAMETER_DOSEN)
    Call<List<Judul>> getJudulSortByDosen(@Path("dosen") String nip_dosen);

    @POST(URL_JUDUL_PA + PARAMETER_DELETE + PARAMETER_JUDUL_PA)
    Call<Judul> deleteJudul(@Path("judul") int judul_id);

}
