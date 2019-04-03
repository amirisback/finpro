package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.DetailMonev;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_MONEV_DETAIL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_MONEV_DETAIL;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_MONEV_DETAIL;

public interface ApiInterfaceDetailMonev {

    @FormUrlEncoded
    @POST(URL_MONEV_DETAIL)
    Call<DetailMonev> createDetailMonev(
        @Field("monev_nilai") int monev_nilai,
        @Field("monev_tanggal") String monev_tanggal,
        @Field("monev_ulasan") String monev_ulasan,
        @Field("monev_reviewer") String monev_reviewer,
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
            @Field("monev_ulasan") String monev_ulasan,
            @Field("monev_reviewer") String monev_reviewer
    );

}
