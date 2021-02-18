package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.works.ProyekMahasiswaWorkView;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 19/04/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * FrogoBox Software Industries
 */
public class ProyekMahasiswaPresenter {

    ProyekMahasiswaWorkView viewEditor;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public ProyekMahasiswaPresenter(ProyekMahasiswaWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public void createProyekAkhir(int judul_id, String mhs_nim, String nama_tim){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);

            Call<ProyekAkhir> callProyek = apiInterfaceProyekAkhir.createProyekAkhir(judul_id, mhs_nim, nama_tim);
            final Call<Mahasiswa> callMahasiswa = apiInterfaceMahasiswa.updateJudulMahasiswa(mhs_nim, judul_id);

            viewEditor.showProgress();
            callProyek.enqueue(new Callback<ProyekAkhir>() {
                @Override
                public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                    callMahasiswa.enqueue(new Callback<Mahasiswa>() {
                        @Override
                        public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                            viewEditor.hideProgress();
                            viewEditor.onSucces();
                        }

                        @Override
                        public void onFailure(Call<Mahasiswa> call, Throwable t) {
                            viewEditor.hideProgress();
                            viewEditor.onFailed(t.getMessage());
                        }
                    });
                }

                @Override
                public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createProyekAkhir2(int judul_id, String mhs_nim1, String mhs_nim2, String nama_tim){

        if (connectionHelper.isConnected(context)){
            ApiService apiInterfaceProyekAkhir = ApiClient.getApiClient().create(ApiService.class);
            ApiService apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiService.class);

            Call<ProyekAkhir> callProyek1 = apiInterfaceProyekAkhir.createProyekAkhir(judul_id, mhs_nim1, nama_tim);
            final Call<ProyekAkhir> callProyek2 = apiInterfaceProyekAkhir.createProyekAkhir(judul_id, mhs_nim2, nama_tim);

            final Call<Mahasiswa> callMahasiswa1 = apiInterfaceMahasiswa.updateJudulMahasiswa(mhs_nim1, judul_id);
            final Call<Mahasiswa> callMahasiswa2 = apiInterfaceMahasiswa.updateJudulMahasiswa(mhs_nim2, judul_id);

            viewEditor.showProgress();

            callProyek1.enqueue(new Callback<ProyekAkhir>() {
                @Override
                public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                    callMahasiswa1.enqueue(new Callback<Mahasiswa>() {
                        @Override
                        public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                            callProyek2.enqueue(new Callback<ProyekAkhir>() {
                                @Override
                                public void onResponse(Call<ProyekAkhir> call, Response<ProyekAkhir> response) {
                                    callMahasiswa2.enqueue(new Callback<Mahasiswa>() {
                                        @Override
                                        public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                                            viewEditor.hideProgress();
                                            viewEditor.onSucces();
                                        }

                                        @Override
                                        public void onFailure(Call<Mahasiswa> call, Throwable t) {
                                            viewEditor.hideProgress();
                                            viewEditor.onFailed(t.getMessage());
                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                                    viewEditor.hideProgress();
                                    viewEditor.onFailed(t.getMessage());
                                }
                            });
                        }

                        @Override
                        public void onFailure(Call<Mahasiswa> call, Throwable t) {
                            viewEditor.hideProgress();
                            viewEditor.onFailed(t.getMessage());
                        }
                    });
                }

                @Override
                public void onFailure(Call<ProyekAkhir> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }
}