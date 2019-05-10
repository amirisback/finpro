package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.DetailMonev;

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
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_MONEV_DETAIL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_1;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_QUERY_2;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_SEARCH;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_MONEV_DETAIL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_MONEV_DETAIL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_PARAMS;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_QUERY;

public interface ApiInterfaceMonevDetail {

    @FormUrlEncoded
    @POST(URL_MONEV_DETAIL)
    Call<DetailMonev> createDetailMonev(
        @Field("monev_nilai") int monev_nilai,
        @Field("monev_tanggal") String monev_tanggal,
        @Field("monev_ulasan") String monev_ulasan,
        @Field("monev_id") int monev_id,
        @Field("proyek_akhir_id") int proyek_akhir_id
    );

    @GET(URL_MONEV_DETAIL)
    Call<List<DetailMonev>> getDetailMonev();

    @POST(URL_MONEV_DETAIL + PATH_DELETE + PARAMETER_MONEV_DETAIL)
    Call<DetailMonev> deleteDetailMonev(@Path(VAR_MONEV_DETAIL) int monev_detail_id);

    @FormUrlEncoded
    @POST(URL_MONEV_DETAIL + PATH_UPDATE + PARAMETER_MONEV_DETAIL)
    Call<DetailMonev> updateDetailMonev(@Path(VAR_MONEV_DETAIL) int monev_detail_id,
            @Field("monev_nilai") int monev_nilai,
            @Field("monev_tanggal") String monev_tanggal,
            @Field("monev_ulasan") String monev_ulasan
    );

    @GET(URL_MONEV_DETAIL + PATH_SEARCH + BASE_PARAMETER_1 + PARAMETER_QUERY_1 + BASE_PARAMETER_2 + PARAMETER_QUERY_2)
    Call<List<DetailMonev>> searchDetailMonevByTwo(
            @Path(VAR_PARAMS+"1") String parameter1,
            @Path(VAR_QUERY+"1") String query1,
            @Path(VAR_PARAMS+"2") String parameter2,
            @Path(VAR_QUERY+"2") String query2
    );

    @GET(URL_MONEV_DETAIL + PATH_SEARCH + BASE_PARAMETER + PARAMETER_QUERY)
    Call<List<DetailMonev>> searchDetailMonevBy(
            @Path(VAR_PARAMS) String parameter,
            @Path(VAR_QUERY) String query
    );

}
