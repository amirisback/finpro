package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Admin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
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
public interface ApiInterfaceAdmin {
    @FormUrlEncoded
    @POST("admin/getAdmin.php")
    Call<Admin> getAdmin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nip_admin") String nip_admin,
            @Field("nama_admin") String nama_admin,
            @Field("no_telepon") String no_telepon,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("admin/getAdmin.php")
    Call<Admin> updateAdmin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nip_admin") String nip_admin,
            @Field("nama_admin") String nama_admin,
            @Field("no_telepon") String no_telepon,
            @Field("email") String email
    );

    @FormUrlEncoded
    @GET("admin/getAdmin.php")
    Call<Admin> createAdmin(
            @Field("username") String username,
            @Field("password") String password,
            @Field("nip_admin") String nip_admin,
            @Field("nama_admin") String nama_admin,
            @Field("no_telepon") String no_telepon,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("admin/getAdmin.php")
    Call<Admin> deleteAdmin(
            @Field("username") String username
    );
}
