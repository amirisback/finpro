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
            @Field("nilai_proposal") int nilai_proposal,
            @Field("nilai_penguji_1") int nilai_penguji_1,
            @Field("nilai_penguji_2") int nilai_penguji_2,
            @Field("nilai_pembimbing") int nilai_pembimbing,
            @Field("nilai_sidang") int nilai_sidang,
            @Field("nilai_sidang") int nilai_total,
            @Field("sidang_status") String sidang_status,
            @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> updateSidang(
            @Path("sidang_id") int sidang_id,
            @Field("sidang_review") String sidang_review,
            @Field("sidang_tanggal") String sidang_tanggal,
            @Field("nilai_proposal") int nilai_proposal,
            @Field("nilai_penguji_1") int nilai_penguji_1,
            @Field("nilai_penguji_2") int nilai_penguji_2,
            @Field("nilai_pembimbing") int nilai_pembimbing,
            @Field("nilai_sidang") int nilai_sidang,
            @Field("nilai_sidang") int nilai_total,
            @Field("sidang_status") String sidang_status
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> deleteSidang(
            @Path("sidang_id") int sidang_id
    );
}
