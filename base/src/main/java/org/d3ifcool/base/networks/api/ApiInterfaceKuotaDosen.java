package org.d3ifcool.base.networks.api;

import org.d3ifcool.base.models.KuotaDosen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PARAMETER_MONEV;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_DELETE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.PATH_UPDATE;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_KUOTA_DOSEN;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_MONEV;
import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.VAR_KUOTA_DOSEN;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 27/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.base.networks.api
 */
public interface ApiInterfaceKuotaDosen {

    @FormUrlEncoded
    @POST(URL_KUOTA_DOSEN)
    Call<KuotaDosen> createKuotaDosen(
            @Field("kuota_variable") String kuota_variable,
            @Field("kuota_value") String kuota_value
    );

    @FormUrlEncoded
    @POST(URL_MONEV + PATH_UPDATE + PARAMETER_MONEV)
    Call<KuotaDosen> updateKuotaDosen(
            @Path(VAR_KUOTA_DOSEN) int kuota_id,
            @Field("kuota_variable") String kuota_variable,
            @Field("kuota_value") String kuota_value
    );

    @GET(URL_MONEV)
    Call<List<KuotaDosen>> getKuotaDosen();

    @POST(URL_MONEV + PATH_DELETE + PARAMETER_MONEV)
    Call<KuotaDosen> deleteKuotaDosen(@Path(VAR_KUOTA_DOSEN) int kuota_id);

}
