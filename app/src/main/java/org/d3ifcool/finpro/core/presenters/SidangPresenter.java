package org.d3ifcool.finpro.core.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.ConnectionHelper;
import org.d3ifcool.finpro.core.interfaces.lists.SidangListView;
import org.d3ifcool.finpro.core.interfaces.objects.SidangView;
import org.d3ifcool.finpro.core.interfaces.works.SidangWorkView;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.core.api.ApiClient;
import org.d3ifcool.finpro.core.api.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SidangPresenter {

    private SidangListView viewResult;
    private SidangWorkView viewEditor;
    private SidangView viewObject;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

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

    public void createSidang(String sidang_review, String sidang_tanggal, int nilai_proposal, int nilai_penguji_1, int nilai_penguji_2, int nilai_pembimbing, double nilai_total, String status,int proyek_akhir_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceSidang = ApiClient.getApiClient().create(ApiService.class);
            Call<Sidang> call = apiInterfaceSidang.createSidang(sidang_review, sidang_tanggal, nilai_proposal , nilai_penguji_1,nilai_penguji_2,nilai_pembimbing, nilai_total, status, proyek_akhir_id);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getSidang(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceSidang = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Sidang>> call = apiInterfaceSidang.getSidang();
            call.enqueue(new Callback<List<Sidang>>() {
                @Override
                public void onResponse(Call<List<Sidang>> call, Response<List<Sidang>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListSidang(response.body());
                    } else {
                        viewResult.isEmptyListSidang();
                    }

                }

                @Override
                public void onFailure(Call<List<Sidang>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void deleteSidang(String sidang_id){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceSidang = ApiClient.getApiClient().create(ApiService.class);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }


    public void updateSidang(String sidang_id, String sidang_review, String sidang_tanggal, int nilai_proposal, int nilai_penguji_1, int nilai_penguji_2, int nilai_pembimbing, double nilai_total, String sidang_status){

        if (connectionHelper.isConnected(context)){
            viewEditor.showProgress();
            ApiService apiInterfaceSidang = ApiClient.getApiClient().create(ApiService.class);
            Call<Sidang> call = apiInterfaceSidang.updateSidang(sidang_id,sidang_review, sidang_tanggal, nilai_proposal , nilai_penguji_1,nilai_penguji_2,nilai_pembimbing, nilai_total, sidang_status);
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
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchAllSidangBy(String parameter, String query){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceSidang = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Sidang>> call = apiInterfaceSidang.searchAllSidangBy(parameter, query);
            call.enqueue(new Callback<List<Sidang>>() {
                @Override
                public void onResponse(Call<List<Sidang>> call, Response<List<Sidang>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListSidang(response.body());
                    } else {
                        viewResult.isEmptyListSidang();
                    }

                }

                @Override
                public void onFailure(Call<List<Sidang>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void searchAllSidangByTwo(String parameter1, String query1, String parameter2, String query2){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiService apiInterfaceSidang = ApiClient.getApiClient().create(ApiService.class);
            Call<List<Sidang>> call = apiInterfaceSidang.searchAllSidangByTwo(parameter1, query1, parameter2, query2);
            call.enqueue(new Callback<List<Sidang>>() {
                @Override
                public void onResponse(Call<List<Sidang>> call, Response<List<Sidang>> response) {
                    viewResult.hideProgress();
                    if (response.body() != null && response.isSuccessful()) {
                        viewResult.onGetListSidang(response.body());
                    } else {
                        viewResult.isEmptyListSidang();
                    }

                }

                @Override
                public void onFailure(Call<List<Sidang>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getLocalizedMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }
}