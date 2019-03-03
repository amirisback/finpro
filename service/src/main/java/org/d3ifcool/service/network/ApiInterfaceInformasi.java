package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Informasi;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
public interface ApiInterfaceInformasi {

    @FormUrlEncoded
    @POST("informasi/createInformasi.php")
    Call<Informasi> createInformasi (
            @Field("info_judul") String info_judul,
            @Field("info_deskripsi") String info_deskripsi

    );


    @FormUrlEncoded
    @POST("informasi/updateInformasi.php")
    Call<Informasi> updateInformasi (
            @Field("id") int id,
            @Field("info_judul") String info_judul,
            @Field("info_deskripsi") String info_deskripsi

    );

    @FormUrlEncoded
    @POST("informasi/getInformasi.php")
    Call<Informasi> getInformasi (
            @Field("id") int id,
            @Field("info_judul") String info_judul,
            @Field("info_deskripsi") String info_deskripsi

    );

    @FormUrlEncoded
    @POST("informasi/deleteInformasi.php")
    Call<Informasi> deleteInformasi (@Field("id")int id);
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
