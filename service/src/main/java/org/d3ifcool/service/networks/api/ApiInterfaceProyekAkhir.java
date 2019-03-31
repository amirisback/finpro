package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.ProyekAkhir;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_ALL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_JUDUL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_PROYEK_AKHIR;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_SEARCH;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_PROYEK_AKHIR;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_PARAMS;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_PROYEK_AKHIR;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_QUERY;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 24/03/2019.
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
public interface ApiInterfaceProyekAkhir {

    @FormUrlEncoded
    @POST(URL_PROYEK_AKHIR)
    Call<ProyekAkhir> createProyekAkhir(
            @Field("judul_id") int id_judul,
            @Field("mhs_nim") String mhs_nim,
            @Field("nama_tim") String nama_tim
    );


    @FormUrlEncoded
    @POST(URL_PROYEK_AKHIR + PATH_UPDATE + PARAMETER_JUDUL)
    Call<ProyekAkhir> updateProyekAkhir(
            @Path(VAR_PROYEK_AKHIR) int proyek_akhir_id,
            @Field("judul_id") int id_judul,
            @Field("mhs_nim") String mhs_nim,
            @Field("dsn_nip") String dsn_nip,
            @Field("nama_tim") String nama_tim
    );

    @GET(URL_PROYEK_AKHIR)
    Call<List<ProyekAkhir>> getProyekAkhir();

    @GET(URL_PROYEK_AKHIR + PATH_SEARCH + PATH_ALL +BASE_PARAMETER + PARAMETER_QUERY)
    Call<List<ProyekAkhir>> getProyekAkhirSearch(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

    @POST(URL_PROYEK_AKHIR + PATH_DELETE + PARAMETER_PROYEK_AKHIR)
    Call<ProyekAkhir> deleteProyekAkhir(@Path(VAR_PROYEK_AKHIR) int proyek_akhir);

}
