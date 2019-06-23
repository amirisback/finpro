package org.d3ifcool.base.networks.api;

import org.d3ifcool.base.models.Kegiatan;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_KEGIATAN;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_KEGIATAN;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_KEGIATAN;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 23/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.base.networks.api
 */
public interface ApiInterfaceKegiatan {

    @FormUrlEncoded
    @POST(URL_KEGIATAN)
    Call<Kegiatan> createKegiatan(
            @Field("kegiatan") String kegiatan,
            @Field("tanggal_mulai") String tanggal_mulai,
            @Field("tanggal_berakhir") String tanggal_berakhir,
            @Field("pelaku") String pelaku,
            @Field("keterangan") String keterangan
    );

    @FormUrlEncoded
    @POST(URL_KEGIATAN + PATH_UPDATE + PARAMETER_KEGIATAN)
    Call<Kegiatan> updateKegiatan(
            @Path(VAR_KEGIATAN) int kegiatan_id,
            @Field("kegiatan") String kegiatan,
            @Field("tanggal_mulai") String tanggal_mulai,
            @Field("tanggal_berakhir") String tanggal_berakhir,
            @Field("pelaku") String pelaku,
            @Field("keterangan") String keterangan
    );

    @GET(URL_KEGIATAN)
    Call<List<Kegiatan>> getKegiatan();

    @POST(URL_KEGIATAN + PATH_DELETE + PARAMETER_KEGIATAN)
    Call<Kegiatan> deleteKegiatan(@Path(VAR_KEGIATAN) int kegiatan_id);


}
