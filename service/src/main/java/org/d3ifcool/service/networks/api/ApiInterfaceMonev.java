package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.Monev;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_MONEV;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_MONEV;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.VAR_MONEV;

public interface ApiInterfaceMonev {

    @FormUrlEncoded
    @POST(URL_MONEV)
    Call<Monev> createMonev(
            @Field("monev_kategori") String monev_kategori
    );

    @FormUrlEncoded
    @POST(URL_MONEV + PATH_UPDATE + PARAMETER_MONEV)
    Call<Monev> updateMonev(
            @Path(VAR_MONEV) int monev_id,
            @Field("monev_kategori") String kategori
    );

    @GET(URL_MONEV)
    Call<List<Monev>> getMonev();

    @POST(URL_MONEV + PATH_DELETE + PARAMETER_MONEV)
    Call<Monev> deleteMonev(@Path(VAR_MONEV) int monev_id);
}
