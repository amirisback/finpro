package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.ProyekAkhir;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_JUDUL_PA;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_PROYEK_AKHIR;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_SEARCH;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_PROYEK_AKHIR;

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
            @Field("id_judul") String id_judul,
            @Field("mhs_nim") String mhs_nim,
            @Field("dsn_nip") String dsn_nip,
            @Field("nama_tim") String nama_tim
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

    @GET(URL_PROYEK_AKHIR + PARAMETER_SEARCH + PARAMETER_PROYEK_AKHIR)
    Call<List<ProyekAkhir>> getProyekAkhirSortByDosen(@Path("proyek_akhir") String nip_dosen);

    @POST(URL_PROYEK_AKHIR + PARAMETER_DELETE + PARAMETER_PROYEK_AKHIR)
    Call<ProyekAkhir> deleteProyekAkhir(@Path("proyek_akhir") int judul_id);

}
