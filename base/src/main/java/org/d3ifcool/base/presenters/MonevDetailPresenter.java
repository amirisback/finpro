package org.d3ifcool.base.presenters;

import org.d3ifcool.base.interfaces.lists.MonevDetailListView;
import org.d3ifcool.base.interfaces.objects.MonevDetailView;
import org.d3ifcool.base.interfaces.works.MonevDetailWorkView;
import org.d3ifcool.base.models.DetailMonev;
import org.d3ifcool.base.networks.api.ApiInterfaceMonevDetail;
import org.d3ifcool.base.networks.bridge.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonevDetailPresenter {

    private MonevDetailListView viewResult;
    private MonevDetailWorkView viewEditor;
    private MonevDetailView viewObject;

    public MonevDetailPresenter(MonevDetailListView viewResult, MonevDetailWorkView viewEditor, MonevDetailView viewObject) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
        this.viewObject = viewObject;
    }

    public MonevDetailPresenter(MonevDetailListView viewResult) {
        this.viewResult = viewResult;
    }

    public MonevDetailPresenter(MonevDetailWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public MonevDetailPresenter(MonevDetailView viewObject) {
        this.viewObject = viewObject;
    }

    public MonevDetailPresenter(MonevDetailListView viewResult, MonevDetailWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public void createMonevDetail(int monev_nilai, String monev_tanggal, String monev_ulasan, int monev_id, int proyek_akhir_id){

        viewEditor.showProgress();
        ApiInterfaceMonevDetail apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceMonevDetail.class);
        Call<DetailMonev> call = apiInterfaceDetailMonev.createDetailMonev(monev_nilai, monev_tanggal, monev_ulasan, monev_id, proyek_akhir_id);
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

    public void getMonevDetail(){
        viewResult.showProgress();
        ApiInterfaceMonevDetail apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceMonevDetail.class);
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

    public void deleteMonevDetail(int monev_detail_id){
        viewEditor.showProgress();
        ApiInterfaceMonevDetail apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceMonevDetail.class);
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

    public void updateMonevDetail(int monev_detail_id, int monev_nilai, String monev_tanggal, String monev_ulasan){
        viewEditor.showProgress();
        ApiInterfaceMonevDetail apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceMonevDetail.class);
        Call<DetailMonev> call = apiInterfaceDetailMonev.updateDetailMonev(monev_detail_id, monev_nilai, monev_tanggal, monev_ulasan);
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

    public void searchMonevDetailByTwo(String parameter1, String query1, String parameter2, String query2) {
        viewResult.showProgress();
        ApiInterfaceMonevDetail apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceMonevDetail.class);
        Call<List<DetailMonev>> call = apiInterfaceDetailMonev.searchDetailMonevByTwo(parameter1, query1, parameter2, query2);
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

    public void searchMonevDetailAllBy(String parameter, String query){
        viewResult.showProgress();
        ApiInterfaceMonevDetail apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiInterfaceMonevDetail.class);
        Call<List<DetailMonev>> call = apiInterfaceDetailMonev.searchDetailMonevBy(parameter, query);
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

}