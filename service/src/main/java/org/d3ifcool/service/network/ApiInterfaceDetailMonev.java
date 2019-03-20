package org.d3ifcool.service.network;

import org.d3ifcool.service.models.DetailMonev;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceDetailMonev {
    @FormUrlEncoded
    @POST("")
    Call<DetailMonev> createDetailMonev(
        @Field("monev_nilai") int monev_nilai,
        @Field("monev_tanggal") String monev_tanggal,
        @Field("monev_id") int monev_id,
        @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST("")
    Call<DetailMonev> getDetailMonev(
            @Field("monev_nilai") int monev_nilai,
            @Field("monev_tanggal") String monev_tanggal,
            @Field("monev_id") int monev_id,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST("")
    Call<DetailMonev> deleteDetailMonev(
            @Field("monev_id") int monev_id,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST("")
    Call<DetailMonev> updateDetailMonev(
            @Field("monev_nilai") int monev_nilai,
            @Field("monev_tanggal") String monev_tanggal,
            @Field("monev_id") int monev_id,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

}
