package org.d3ifcool.base.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.base.R;
import org.d3ifcool.base.helpers.ConnectionHelper;
import org.d3ifcool.base.interfaces.objects.MahasiswaView;
import org.d3ifcool.base.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.base.interfaces.lists.MahasiswaListView;
import org.d3ifcool.base.models.Mahasiswa;
import org.d3ifcool.base.networks.bridge.ApiClient;
import org.d3ifcool.base.networks.api.ApiInterfaceMahasiswa;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ikhsan ramadhan
 * =========================================
 * Finpro
 * Copyright (C) 3/2/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhamad Ikhsan Ramadhan
 * E-mail   : ikhsanramadhan28@gmail.com
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 */
public class MahasiswaPresenter {
    private MahasiswaListView viewResult;
    private MahasiswaWorkView viewEditor;
    private MahasiswaView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public MahasiswaPresenter(MahasiswaListView viewResult) {
        this.viewResult = viewResult;
    }

    public MahasiswaPresenter(MahasiswaListView viewResult, MahasiswaWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public MahasiswaPresenter(MahasiswaWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public MahasiswaPresenter(MahasiswaView viewObject) {
        this.viewObject = viewObject;
    }

    public MahasiswaPresenter(MahasiswaListView viewResult, MahasiswaView viewObject) {
        this.viewResult = viewResult;
        this.viewObject = viewObject;
    }

    public MahasiswaPresenter(MahasiswaWorkView viewEditor, MahasiswaView viewObject) {
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public void getMahasiswa(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<List<Mahasiswa>> call = apiInterfaceMahasiswa.getMahasiswa();
            call.enqueue(new Callback<List<Mahasiswa>>() {
                @Override
                public void onResponse(Call<List<Mahasiswa>> call, Response<List<Mahasiswa>> response) {
                    viewResult.hideProgress();
                    viewResult.onGetListMahasiswa(response.body());
                }

                @Override
                public void onFailure(Call<List<Mahasiswa>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void createMahasiswa(String nim, String nama){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.createMahasiswa(nim, nama);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String nim, String nama, String angkatan, String kontak,String mhs_foto, String email){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa(nim, nama, angkatan, kontak, mhs_foto,email);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswa(String nim, String nama, String angkatan, String kontak, String email){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateMahasiswa(nim, nama, angkatan, kontak, email);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void deleteMahasiswa(String nim){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.deleteMahasiswa(nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getMahasiswaByParameter(String mhs_nim){

        if (connectionHelper.isConnected(context)){
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.getMahasiswaByParameter(mhs_nim);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewObject.hideProgress();
                    viewObject.onGetObjectMahasiswa(response.body());
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewObject.hideProgress();
                    viewObject.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void updateMahasiswaJudul(String nim, int judul_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiInterfaceMahasiswa apiInterfaceMahasiswa = ApiClient.getApiClient().create(ApiInterfaceMahasiswa.class);
            Call<Mahasiswa> call = apiInterfaceMahasiswa.updateJudulMahasiswa(nim, judul_id);
            call.enqueue(new Callback<Mahasiswa>() {
                @Override
                public void onResponse(Call<Mahasiswa> call, Response<Mahasiswa> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<Mahasiswa> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getLocalizedMessage());
                }
            });

        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}
