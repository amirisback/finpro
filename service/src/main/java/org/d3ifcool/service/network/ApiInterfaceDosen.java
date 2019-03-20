package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Dosen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.PARAMETER_DOSEN;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_DOSEN;

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
public interface ApiInterfaceDosen {

    @FormUrlEncoded
    @POST(URL_DOSEN)
    Call<Dosen> createDosen(
            @Field("dsn_nama") String dsn_nama,
            @Field("dsn_kode") String dsn_kode,
            @Field("dsn_kontak") String dsn_kontak,
            @Field("dsn_foto") String dsn_foto,
            @Field("dsn_email") int dsn_email,
            @Field("dsn_status") String dsn_status
    );

    @FormUrlEncoded
    @POST(URL_DOSEN)
    Call<Dosen> updateDosen(
            @Field("dsn_nip") int dsn_nip,
            @Field("dsn_nama") String dsn_nama,
            @Field("dsn_kode") String dsn_kode,
            @Field("dsn_kontak") String dsn_kontak,
            @Field("dsn_foto") String dsn_foto,
            @Field("dsn_email") int dsn_email,
            @Field("dsn_status") String dsn_status
    );

    @FormUrlEncoded
    @POST(URL_DOSEN)
    Call<Dosen> deleteDosen(@Field("dsn_nip") int dsn_nip);


    @GET(URL_DOSEN)
    Call<List<Dosen>> getDosen();

    @GET(URL_DOSEN + PARAMETER_DOSEN)
    Call<Dosen> getDataDosenLogin(@Path("dosen") String dsn_nip);

}