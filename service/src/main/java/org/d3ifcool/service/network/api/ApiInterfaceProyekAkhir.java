package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.ProyekAkhir;

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
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_PROYEK_AKHIR;

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
            @Field("judul_nama") String judul_nama,
            @Field("judul_kategori") String judul_kategori,
            @Field("judul_deskripsi") String judul_deskripsi,
            @Field("nip_dosen") String nip_dosen
    );


    @FormUrlEncoded
    @POST(URL_PROYEK_AKHIR + PARAMETER_UPDATE + PARAMETER_JUDUL_PA)
    Call<ProyekAkhir> updateProyekAkhir(
            @Path("judul") int judul_id,
            @Field("judul_nama") String judul_nama,
            @Field("judul_kategori") String judul_kategori,
            @Field("judul_deskripsi") String judul_deskripsi
    );

    @GET(URL_PROYEK_AKHIR)
    Call<List<ProyekAkhir>> getProyekAkhir();

    @GET(URL_PROYEK_AKHIR + PARAMETER_SORTBY + PARAMETER_DOSEN)
    Call<List<ProyekAkhir>> getProyekAkhirSortByDosen(@Path("dosen") String nip_dosen);

    @POST(URL_PROYEK_AKHIR + PARAMETER_DELETE + PARAMETER_JUDUL_PA)
    Call<ProyekAkhir> deleteProyekAkhir(@Path("judul") int judul_id);

}
