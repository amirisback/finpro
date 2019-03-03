package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Mahasiswa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    @POST("mahasiswa/createMahasiswa.php")
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
    @POST("mahasiswa/updateMahasiswa.php")
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

    @FormUrlEncoded
    @POST("mahasiswa/getMahasiswa.php")
    Call<Mahasiswa> getMahasiswa(
            @Field("nim_mhs") String nim_mhs,
            @Field("nama_m") String nama,
            @Field("foto_m") String foto_m,
            @Field("email_m") String email_m,
            @Field("kontak_m") String kontak_m,
            @Field("angkatan") String angkatan,
            @Field("status") String status,
            @Field("username_mhs") String username_mhs
    );

    @FormUrlEncoded
    @POST("mahasiswa/deleteMahasiswa.php")
    Call<Mahasiswa> deleteMahasiswa(
            @Field("nim_mhs") String nim_mhs);
}
