package org.d3ifcool.service.presenters;

import org.d3ifcool.service.interfaces.lists.DetailMonevListView;
import org.d3ifcool.service.interfaces.objects.DetailMonevView;
import org.d3ifcool.service.interfaces.works.DetailMonevWorkView;
import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.networks.api.ApiInterfaceDetailMonev;
import org.d3ifcool.service.networks.bridge.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailMonevPresenter {

    private DetailMonevListView viewResult;
    private DetailMonevWorkView viewEditor;
    private DetailMonevView viewObject;

    public DetailMonevPresenter(DetailMonevListView viewResult, DetailMonevWorkView viewEditor, DetailMonevView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public DetailMonevPresenter(DetailMonevListView viewResult) {
        this.viewResult = viewResult;
    }

    public DetailMonevPresenter(DetailMonevWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public DetailMonevPresenter(DetailMonevView viewObject) {
        this.viewObject = viewObject;
    }

    public DetailMonevPresenter(DetailMonevListView viewResult, DetailMonevWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public void createDetailMonev(int monev_nilai, String monev_tanggal, String monev_ulasan, String monev_reviewer, int monev_id, int proyek_akhir_id
    ){
        viewEditor.showProgress();
        ApiInterfaceDetailMonev apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceDetailMonev.class);
        Call<DetailMonev> call = apiInterfaceDetailMonev.createDetailMonev(monev_nilai, monev_tanggal, monev_ulasan, monev_reviewer, monev_id, proyek_akhir_id);
        call.enqueue(new Callback<DetailMonev>() {
            @Override
            public void onResponse(Call<DetailMonev> call, Response<DetailMonev> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<DetailMonev> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void getDetailMonev(){
        viewResult.showProgress();
        ApiInterfaceDetailMonev apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceDetailMonev.class);
        Call<List<DetailMonev>> call = apiInterfaceDetailMonev.getDetailMonev();
        call.enqueue(new Callback<List<DetailMonev>>() {
            @Override
            public void onResponse(Call<List<DetailMonev>> call, Response<List<DetailMonev>> response) {
                viewResult.hideProgress();
                viewResult.onGetListDetailMonev(response.body());
            }

            @Override
            public void onFailure(Call<List<DetailMonev>> call, Throwable t) {
                viewResult.hideProgress();
                viewResult.onFailed(t.getMessage());
            }
        });
    }

    public void deleteDetailMonev(int monev_detail_id){
        viewEditor.showProgress();
        ApiInterfaceDetailMonev apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceDetailMonev.class);
        Call<DetailMonev> call = apiInterfaceDetailMonev.deleteDetailMonev(monev_detail_id);
        call.enqueue(new Callback<DetailMonev>() {
            @Override
            public void onResponse(Call<DetailMonev> call, Response<DetailMonev> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<DetailMonev> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });
    }

    public void updateDetailMonev(int monev_detail_id, int monev_nilai, String monev_tanggal, String monev_ulasan, String monev_reviewer){
        viewEditor.showProgress();
        ApiInterfaceDetailMonev apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceDetailMonev.class);
        Call<DetailMonev> call = apiInterfaceDetailMonev.updateDetailMonev(monev_detail_id, monev_nilai, monev_tanggal, monev_ulasan, monev_reviewer);
        call.enqueue(new Callback<DetailMonev>() {
            @Override
            public void onResponse(Call<DetailMonev> call, Response<DetailMonev> response) {
                viewEditor.hideProgress();
                viewEditor.onSucces();
            }

            @Override
            public void onFailure(Call<DetailMonev> call, Throwable t) {
                viewEditor.hideProgress();
                viewEditor.onFailed(t.getMessage());
            }
        });

    }
}