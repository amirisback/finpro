package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.Sidang;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_SIDANG;

public interface ApiInterfaceSidang {
    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> getSidang(
            @Field("sidang_id") int sidang_id,
            @Field("sidang_review") String sidang_review,
            @Field("sidang_tanggal") String sidang_tanggal,
            @Field("sidang_nilai") int sidang_nilai,
            @Field("sidang_status") String sidang_status,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> createSidang(
            @Field("sidang_review") String sidang_review,
            @Field("sidang_tanggal") String sidang_tanggal,
            @Field("sidang_nilai") int sidang_nilai,
            @Field("sidang_status") String sidang_status,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> updateSidang(
            @Field("sidang_id") int sidang_id,
            @Field("sidang_review") String sidang_review,
            @Field("sidang_tanggal") String sidang_tanggal,
            @Field("sidang_nilai") int sidang_nilai,
            @Field("sidang_status") String sidang_status,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> deleteSidang(
            @Field("sidang_id") int sidang_id
    );
}
