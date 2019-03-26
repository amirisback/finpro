package org.d3ifcool.service.networks.api;

import org.d3ifcool.service.models.Monev;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_DELETE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_MONEV;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.PARAMETER_UPDATE;
import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_MONEV;

public interface ApiInterfaceMonev {

    @FormUrlEncoded
    @POST(URL_MONEV)
    Call<Monev> createMonev(
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST(URL_MONEV + PARAMETER_UPDATE + PARAMETER_MONEV)
    Call<Monev> updateMonev(
            @Path("monev") int monev_id,
            @Field("monev_kategori") String kategori
    );

    @GET(URL_MONEV)
    Call<Monev> getMonev();

    @POST(URL_MONEV + PARAMETER_DELETE + PARAMETER_MONEV)
    Call<Monev> deleteMonev(@Path("monev") int monev_id);
}
