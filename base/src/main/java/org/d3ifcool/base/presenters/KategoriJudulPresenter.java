package org.d3ifcool.base.presenters;

import android.content.Context;
import android.widget.Toast;

import org.d3ifcool.base.R;
import org.d3ifcool.base.helpers.ConnectionHelper;
import org.d3ifcool.base.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.base.interfaces.works.KategoriJudulWorkView;
import org.d3ifcool.base.models.KategoriJudul;
import org.d3ifcool.base.networks.api.ApiInterfaceKategoriJudul;
import org.d3ifcool.base.networks.bridge.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KategoriJudulPresenter {

    private KategoriJudulListView viewResult;
    private KategoriJudulWorkView viewEditor;

    private ConnectionHelper connectionHelper = new ConnectionHelper();
    private Context context;

    public void initContext(Context context){
        this.context = context;
    }

    public KategoriJudulPresenter(KategoriJudulListView viewResult, KategoriJudulWorkView viewEditor) {
        this.viewResult = viewResult;
        this.viewEditor = viewEditor;
    }

    public KategoriJudulPresenter(KategoriJudulListView viewResult) {
        this.viewResult = viewResult;
    }

    public KategoriJudulPresenter(KategoriJudulWorkView viewEditor) {
        this.viewEditor = viewEditor;
    }

    public void createKategori(String kategori){

        if (connectionHelper.isConnected(context)){
            ApiInterfaceKategoriJudul apiInterfaceKategoriJudul = ApiClient.getApiClient().create(ApiInterfaceKategoriJudul.class);
            Call<KategoriJudul> call = apiInterfaceKategoriJudul.createKategoriJudul(kategori);
            call.enqueue(new Callback<KategoriJudul>() {
                @Override
                public void onResponse(Call<KategoriJudul> call, Response<KategoriJudul> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KategoriJudul> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void getKategori(){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiInterfaceKategoriJudul apiInterfaceKategoriJudul = ApiClient.getApiClient().create(ApiInterfaceKategoriJudul.class);
            Call<List<KategoriJudul>> call = apiInterfaceKategoriJudul.getKategoriJudul();
            call.enqueue(new Callback<List<KategoriJudul>>() {
                @Override
                public void onResponse(Call<List<KategoriJudul>> call, Response<List<KategoriJudul>> response) {
                    viewResult.hideProgress();
                    viewResult.onGetListKategoriJudul(response.body());
                }

                @Override
                public void onFailure(Call<List<KategoriJudul>> call, Throwable t) {
                    viewResult.hideProgress();
                    viewResult.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }


    }

    public void updateKategori(int id, String kategori){

        if (connectionHelper.isConnected(context)){
            ApiInterfaceKategoriJudul apiInterfaceKategoriJudul = ApiClient.getApiClient().create(ApiInterfaceKategoriJudul.class);
            Call<KategoriJudul> call = apiInterfaceKategoriJudul.updateKategoriJudul(id,kategori);
            call.enqueue(new Callback<KategoriJudul>() {
                @Override
                public void onResponse(Call<KategoriJudul> call, Response<KategoriJudul> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KategoriJudul> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }

    public void deleteKategori(int id){

        if (connectionHelper.isConnected(context)){
            viewResult.showProgress();
            ApiInterfaceKategoriJudul apiInterfaceKategoriJudul = ApiClient.getApiClient().create(ApiInterfaceKategoriJudul.class);
            Call<KategoriJudul> call = apiInterfaceKategoriJudul.deleteKategoriJudul(id);
            call.enqueue(new Callback<KategoriJudul>() {
                @Override
                public void onResponse(Call<KategoriJudul> call, Response<KategoriJudul> response) {
                    viewEditor.hideProgress();
                    viewEditor.onSucces();
                }

                @Override
                public void onFailure(Call<KategoriJudul> call, Throwable t) {
                    viewEditor.hideProgress();
                    viewEditor.onFailed(t.getMessage());
                }
            });
        } else {
            Toast.makeText(context, context.getString(R.string.validate_no_connection), Toast.LENGTH_SHORT).show();
        }



    }


}
