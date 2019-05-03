package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.lists.SidangListView;
import org.d3ifcool.service.interfaces.objects.SidangView;
import org.d3ifcool.service.interfaces.works.SidangWorkView;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.models.Sidang;
import org.d3ifcool.service.networks.api.ApiInterfaceProyekAkhir;
import org.d3ifcool.service.networks.api.ApiInterfaceSidang;
import org.d3ifcool.service.networks.bridge.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SidangPresenter {

    private SidangListView viewResult;
    private SidangWorkView viewEditor;
    private SidangView viewObject;

    public SidangPresenter(SidangListView viewResult, SidangWorkView viewEditor, SidangView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public SidangPresenter(SidangListView viewResult) {
        this.viewResult = viewResult;
    }

    public SidangPresenter(SidangWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public SidangPresenter(SidangView viewObject) {
        this.viewObject = viewObject;
    }

    public SidangPresenter(SidangListView viewResult, SidangWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public void createSidang(String sidang_review, String sidang_tanggal, int nilai_proposal, int nilai_penguji_1, int nilai_penguji_2, int nilai_pembimbing, double nilai_sidang, String status,int proyek_akhir_id){
        viewEditor.showProgress();
        ApiInterfaceSidang apiInterfaceSidang = ApiClient.getApiClient().create(ApiInterfaceSidang.class);
        Call<Sidang> call = apiInterfaceSidang.createSidang(sidang_review, sidang_tanggal, nilai_proposal , nilai_penguji_1,nilai_penguji_2,nilai_pembimbing, nilai_sidang, status, proyek_akhir_id);
        call.enqueue(new Callback<Sidang>() {
            @Override
            public void onResponse(Call<Sidang> call, Response<Sidang> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Sidang> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void getSidang(){
        viewResult.showProgress();
        ApiInterfaceSidang apiInterfaceSidang = ApiClient.getApiClient().create(ApiInterfaceSidang.class);
        Call<List<Sidang>> call = apiInterfaceSidang.getSidang();
        call.enqueue(new Callback<List<Sidang>>() {
            @Override
            public void onResponse(Call<List<Sidang>> call, Response<List<Sidang>> response) {
                viewResult.hideProgress();
                viewResult.onGetListSidang(response.body());
            }

            @Override
            public void onFailure(Call<List<Sidang>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void deleteSidang(int sidang_id){
        viewEditor.showProgress();
        ApiInterfaceSidang apiInterfaceSidang = ApiClient.getApiClient().create(ApiInterfaceSidang.class);
        Call<Sidang> call = apiInterfaceSidang.deleteSidang(sidang_id);
        call.enqueue(new Callback<Sidang>() {
            @Override
            public void onResponse(Call<Sidang> call, Response<Sidang> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Sidang> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }


    public void updateSidang(int sidang_id, String sidang_review, String sidang_tanggal, int nilai_proposal, int nilai_penguji_1, int nilai_penguji_2, int nilai_pembimbing, double nilai_sidang, String sidang_status){
        viewEditor.showProgress();
        ApiInterfaceSidang apiInterfaceSidang = ApiClient.getApiClient().create(ApiInterfaceSidang.class);
        Call<Sidang> call = apiInterfaceSidang.updateSidang(sidang_id,sidang_review, sidang_tanggal, nilai_proposal , nilai_penguji_1,nilai_penguji_2,nilai_pembimbing, nilai_sidang, sidang_status);
        call.enqueue(new Callback<Sidang>() {
            @Override
            public void onResponse(Call<Sidang> call, Response<Sidang> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<Sidang> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void searchAllSidangBy(String parameter, String query){
        viewResult.showProgress();
        ApiInterfaceSidang apiInterfaceSidang = ApiClient.getApiClient().create(ApiInterfaceSidang.class);
        Call<List<Sidang>> call = apiInterfaceSidang.searchAllSidangBy(parameter, query);
        call.enqueue(new Callback<List<Sidang>>() {
            @Override
            public void onResponse(Call<List<Sidang>> call, Response<List<Sidang>> response) {
                viewResult.hideProgress();
                viewResult.onGetListSidang(response.body());
            }

            @Override
            public void onFailure(Call<List<Sidang>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getLocalizedMessage());
            }
        });
    }

    public void searchAllSidangByTwo(String parameter1, String query1, String parameter2, String query2){
        viewResult.showProgress();
        ApiInterfaceSidang apiInterfaceSidang = ApiClient.getApiClient().create(ApiInterfaceSidang.class);
        Call<List<Sidang>> call = apiInterfaceSidang.searchAllSidangByTwo(parameter1, query1, parameter2, query2);
        call.enqueue(new Callback<List<Sidang>>() {
            @Override
            public void onResponse(Call<List<Sidang>> call, Response<List<Sidang>> response) {
                viewResult.hideProgress();
                viewResult.onGetListSidang(response.body());
            }

            @Override
            public void onFailure(Call<List<Sidang>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getLocalizedMessage());
            }
        });
    }
}