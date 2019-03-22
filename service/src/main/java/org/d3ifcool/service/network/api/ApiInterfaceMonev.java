package org.d3ifcool.service.network.api;

import org.d3ifcool.service.models.Monev;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_MONEV;

public interface ApiInterfaceMonev {

    @FormUrlEncoded
    @POST(URL_MONEV)
    Call<Monev> createMonev(
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST(URL_MONEV)
    Call<Monev> updateMonev(
            @Field("monev_id") int monev_id,
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST(URL_MONEV)
    Call<Monev> getMonev(
            @Field("monev_id") int monev_id,
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST(URL_MONEV)
    Call<Monev> deleteMonev(
            @Field("monev_id") int monev_id
    );
}
