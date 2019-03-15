package org.d3ifcool.service.network;

import org.d3ifcool.service.models.Monev;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterfaceMonev {

    @FormUrlEncoded
    @POST("")
    Call<Monev> createMonev(
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST("")
    Call<Monev> updateMonev(
            @Field("monev_id") int monev_id,
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST("")
    Call<Monev> getMonev(
            @Field("monev_id") int monev_id,
            @Field("monev_kategori") String kategori
    );

    @FormUrlEncoded
    @POST("")
    Call<Monev> deleteMonev(
            @Field("monev_id") int monev_id
    );
}
