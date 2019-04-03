package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.Sidang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_SIDANG;

public interface ApiInterfaceSidang {

    @GET(URL_SIDANG)
    Call<List<Sidang>> getSidang();

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
            @Path("sidang_id") int sidang_id,
            @Field("sidang_review") String sidang_review,
            @Field("sidang_tanggal") String sidang_tanggal,
            @Field("sidang_nilai") int sidang_nilai,
            @Field("sidang_status") String sidang_status,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> deleteSidang(
            @Path("sidang_id") int sidang_id
    );
}
