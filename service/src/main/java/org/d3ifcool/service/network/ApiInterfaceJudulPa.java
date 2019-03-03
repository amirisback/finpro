package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.models.JudulPa;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
    @POST("judul/createJudul.php")
    Call<JudulPa> createJudul(
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") String kategori,
            @Field("nip_dosen") String nip_dosen

    );


    @FormUrlEncoded
    @POST("judul/updateJudul.php")
    Call<JudulPa> updateJudul(
            @Field("id_judul") int id_judul,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") String kategori,
            @Field("status") String status,
            @Field("nip_dosen") String nip_dosen

    );

    @FormUrlEncoded
    @POST("judul/getJudul.php")
    Call<JudulPa> selectJudul(
            @Field("id_judul") int id_judul,
            @Field("judul") String judul,
            @Field("deskripsi") String deskripsi,
            @Field("kategori") String kategori,
            @Field("status") String status,
            @Field("nip_dosen") String nip_dosen

    );

    @FormUrlEncoded
    @POST("judul/deleteJudul.php")
    Call<JudulPa> deleteJudul(@Field("id_judul") int id_judul);
//
//    @GET("notes.php")
//    Call<List<Note>> getNotes();
//
//    @FormUrlEncoded
//    @POST("update.php")
//    Call<Note> updateNote(
//            @Field("id") int id,
//            @Field("title") String title,
//            @Field("note") String note,
//            @Field("color") int color
//    );
//
//    @FormUrlEncoded
//    @POST("delete.php")
//    Call<Note> deleteNote(@Field("id") int id);


}
