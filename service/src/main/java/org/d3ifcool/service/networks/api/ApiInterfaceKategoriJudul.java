package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.KategoriJudul;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_KATEGORI_JUDUL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_KATEGORI_JUDUL;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/03/2019.
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
public interface ApiInterfaceKategoriJudul {

    @FormUrlEncoded
    @POST(URL_KATEGORI_JUDUL)
    Call<KategoriJudul> createKategoriJudul(
            @Field("kategori_nama") String kategori_nama
    );


    @FormUrlEncoded
    @POST(URL_KATEGORI_JUDUL + PARAMETER_UPDATE + PARAMETER_KATEGORI_JUDUL)
    Call<KategoriJudul> updateKategoriJudul(
            @Path("kategori_judul") int kategori_judul,
            @Field("kategori_nama") String kategori_nama
    );

    @GET(URL_KATEGORI_JUDUL)
    Call<List<KategoriJudul>> getKategoriJudul();

    @POST(URL_KATEGORI_JUDUL + PARAMETER_DELETE + PARAMETER_KATEGORI_JUDUL)
    Call<KategoriJudul> deleteKategoriJudul(@Path("kategori_judul") int kategori_judul);

}
