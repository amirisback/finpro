package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Mahasiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_MAHASISWA_CREATE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_MAHASISWA_DELETE;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_MAHASISWA_READ;
import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_MAHASISWA_UPDATE;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/2/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public interface ApiInterfaceMahasiswa {

    @FormUrlEncoded
    @POST(URL_MAHASISWA_CREATE)
    Call<Mahasiswa> createMahasiswa(
            @Field("nama_m") String nama,
            @Field("foto_m") String foto_m,
            @Field("email_m") String email_m,
            @Field("kontak_m") String kontak_m,
            @Field("angkatan") String angkatan,
            @Field("status") String status,
            @Field("username_mhs") String username_mhs
    );

    @FormUrlEncoded
    @POST(URL_MAHASISWA_UPDATE)
    Call<Mahasiswa> updateMahasiswa(
            @Field("nim_mhs") String nim_mhs,
            @Field("nama_m") String nama,
            @Field("foto_m") String foto_m,
            @Field("email_m") String email_m,
            @Field("kontak_m") String kontak_m,
            @Field("angkatan") String angkatan,
            @Field("status") String status,
            @Field("username_mhs") String username_mhs
    );

    @GET(URL_MAHASISWA_READ)
    Call<Mahasiswa> getMahasiswa();

    @FormUrlEncoded
    @POST(URL_MAHASISWA_DELETE)
    Call<Mahasiswa> deleteMahasiswa(@Field("nim_mhs") String nim_mhs);
}
