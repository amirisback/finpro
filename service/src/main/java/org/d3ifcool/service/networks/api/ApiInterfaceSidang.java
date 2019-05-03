package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.models.Sidang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER_1;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.BASE_PARAMETER_2;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_1;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_2;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_ALL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_SEARCH;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_PROYEK_AKHIR;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_SIDANG;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_PARAMS;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_QUERY;

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
            @Field("nilai_total") double nilai_total,
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
            @Field("nilai_total") double nilai_total,
            @Field("sidang_status") String sidang_status
    );

    @FormUrlEncoded
    @POST(URL_SIDANG)
    Call<Sidang> deleteSidang(
            @Path("sidang_id") int sidang_id
    );

    @GET(URL_SIDANG + PATH_SEARCH + PATH_ALL + BASE_PARAMETER + PARAMETER_QUERY)
    Call<List<Sidang>> searchAllSidangBy(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

    @GET(URL_SIDANG + PATH_SEARCH + PATH_ALL + BASE_PARAMETER_1 + PARAMETER_QUERY_1 + BASE_PARAMETER_2 + PARAMETER_QUERY_2)
    Call<List<Sidang>> searchAllSidangByTwo(
            @Path(VAR_PARAMS+"1") String parameter1,
            @Path(VAR_QUERY+"1") String query1,
            @Path(VAR_PARAMS+"2") String parameter2,
            @Path(VAR_QUERY+"2") String query2
    );
}
