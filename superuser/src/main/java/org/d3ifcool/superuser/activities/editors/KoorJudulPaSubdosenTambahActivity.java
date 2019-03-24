package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.DosenViewResult;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewEditor;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.network.api.ApiInterfaceDosen;
import org.d3ifcool.service.network.bridge.ApiClient;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;
import java.util.List;

public class KoorJudulPaSubdosenTambahActivity extends AppCompatActivity implements JudulPaSubDosenViewEditor {
    private EditText et_judul, et_deskripsi;
    private Spinner sp_dosen, sp_kategori;
    private JudulPresenter presenter;
    private ProgressDialog dialog;
    private Button btn_simpan;
    private Context mContext;
    private List<Dosen> dosens;
    private String nip_dosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_tambah);
        mContext = this;
        et_judul = findViewById(R.id.act_koor_judul_pa_edittext_judul);
        et_deskripsi = findViewById(R.id.act_koor_judul_pa_edittext_deskripsi);
        sp_dosen = findViewById(R.id.act_koor_judul_pa_spinner_nama_dosen);
        sp_kategori = findViewById(R.id.act_koor_judul_pa_spinner_kategori);
        btn_simpan = findViewById(R.id.act_koor_judul_pa_button_simpan);

        presenter = new JudulPresenter(this, KoorJudulPaSubdosenTambahActivity.this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.progress_dialog));

        setTitle(getString(org.d3ifcool.dosen.R.string.title_judulpa_dosen_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initSpinner();
        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(mContext, nip_dosen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            //
        } else if (i == R.id.toolbar_menu_hapus) {
            //
        } else {
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }



    @Override
    public void onSucces() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void initSpinner(){
        ApiInterfaceDosen interfaceDosen = ApiClient.getApiClient().create(ApiInterfaceDosen.class);
        Call<List<Dosen>> call = interfaceDosen.getDosen();
        call.enqueue(new Callback<List<Dosen>>() {
            @Override
            public void onResponse(Call<List<Dosen>> call, Response<List<Dosen>> response) {
                if(response.isSuccessful()) {
                     dosens = response.body();
                    List<String> spinner = new ArrayList<String>();
                    for (int i = 0; i < dosens.size(); i++) {
                        nip_dosen = dosens.get(i).getDsn_nip();
                        spinner.add(dosens.get(i).getDsn_nama());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, R.layout.support_simple_spinner_dropdown_item
                            , spinner);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    sp_dosen.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Dosen>> call, Throwable t) {
                Toast.makeText(KoorJudulPaSubdosenTambahActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
