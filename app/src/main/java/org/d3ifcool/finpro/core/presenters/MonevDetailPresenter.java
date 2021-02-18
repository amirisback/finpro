package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.MonevDetailListView;
import org.d3ifcool.finpro.core.interfaces.objects.MonevDetailView;
import org.d3ifcool.finpro.core.interfaces.works.MonevDetailWorkView;
import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MonevDetailPresenter {

    private MonevDetailListView viewResult;
    private MonevDetailWorkView viewEditor;
    private MonevDetailView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

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

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getMonevDetail(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteMonevDetail(int monev_detail_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateMonevDetail(int monev_detail_id, int monev_nilai, String monev_tanggal, String monev_ulasan){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void searchMonevDetailByTwo(String parameter1, String query1, String parameter2, String query2) {

        if (connectionHelper.isConnected(context)){

            viewResult.showProgress();
            ApiService apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiService.class);
            Call<List<DetailMonev>> call = apiInterfaceDetailMonev.searchDetailMonevByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<DetailMonev>>() {
                @Override
                public void onResponse(Call<List<DetailMonev>> call, Response<List<DetailMonev>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListDetailMonev(response.body());
                    } else {
                        viewResult.isEmptyListMonevDetail();
                    }

                }

                @Override
                public void onFailure(Call<List<DetailMonev>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }

    }

    public void searchMonevDetailAllBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceDetailMonev = ApiClient.getApiClient().create(ApiService.class);
            Call<List<DetailMonev>> call = apiInterfaceDetailMonev.searchDetailMonevBy(parameter, query);
            call.enqueue(new Callback<List<DetailMonev>>() {
                @Override
                public void onResponse(Call<List<DetailMonev>> call, Response<List<DetailMonev>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListDetailMonev(response.body());
                    } else {
                        viewResult.isEmptyListMonevDetail();
                    }

                }

                @Override
                public void onFailure(Call<List<DetailMonev>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

}