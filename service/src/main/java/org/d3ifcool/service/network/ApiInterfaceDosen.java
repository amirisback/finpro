package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Informasi;

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
public interface ApiInterfaceDosen {

    @FormUrlEncoded
    @POST("dosen/createDosen.php")
    Call<Dosen> createDosen(
            @Field("nama_d") String nama_d,
            @Field("foto") String foto,
            @Field("email_d") String email_d,
            @Field("kontak_d") String kontak_d,
            @Field("limit") int limit,
            @Field("username_dosen") String username_dosen

    );


    @FormUrlEncoded
    @POST("dosen/updateDosen.php")
    Call<Dosen> updateDosen(
            @Field("nip_dosen") int nip_dosen,
            @Field("nama_d") String nama_d,
            @Field("foto") String foto,
            @Field("email_d") String email_d,
            @Field("kontak_d") String kontak_d,
            @Field("limit") int limit,
            @Field("username_dosen") String username_dosen

    );

    @FormUrlEncoded
    @POST("dosen/deleteDosen.php")
    Call<Dosen> deleteDosen(@Field("nip_dosen") int nip_dosen);

    @FormUrlEncoded
    @POST("dosen/getDosen.php")
    Call<Dosen> getDosen(
            @Field("nip_dosen") int nip_dosen,
            @Field("nama_d") String nama_d,
            @Field("foto") String foto,
            @Field("email_d") String email_d,
            @Field("kontak_d") String kontak_d,
            @Field("limit") int limit,
            @Field("username_dosen") String username_dosen

    );
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
