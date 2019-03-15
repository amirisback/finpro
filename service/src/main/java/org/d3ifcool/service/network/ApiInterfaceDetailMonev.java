package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Detail_Monev;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceDetailMonev {
    @FormUrlEncoded
    @POST("")
    Call<Detail_Monev> createDetailMonev(
        @Field("monev_nilai") int monev_nilai,
        @Field("monev_tanggal") String monev_tanggal,
        @Field("monev_id") int monev_id,
        @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST("")
    Call<Detail_Monev> getDetailMonev(
            @Field("monev_nilai") int monev_nilai,
            @Field("monev_tanggal") String monev_tanggal,
            @Field("monev_id") int monev_id,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST("")
    Call<Detail_Monev> deleteDetailMonev(
            @Field("monev_id") int monev_id,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST("")
    Call<Detail_Monev> updateDetailMonev(
            @Field("monev_nilai") int monev_nilai,
            @Field("monev_tanggal") String monev_tanggal,
            @Field("monev_id") int monev_id,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

}
