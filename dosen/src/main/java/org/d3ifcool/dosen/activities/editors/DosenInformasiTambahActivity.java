package org.d3ifcool.dosen.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.InformasiViewEditor;
import org.d3ifcool.service.presenter.InformasiPresenter;

public class DosenInformasiTambahActivity extends AppCompatActivity implements InformasiViewEditor {

    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_tambah);

        setTitle(R.string.title_informasi_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText info_judul = findViewById(R.id.act_dsn_edittext_judul);
        final EditText info_deskripsi = findViewById(R.id.act_dsn_edittext_deskripsi);
        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);


        presenter = new InformasiPresenter(this,DosenInformasiTambahActivity.this);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text_info_judul = info_judul.getText().toString();
                String text_info_deskripsi = info_deskripsi.getText().toString();
                if(text_info_judul.isEmpty()){
                    info_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if(text_info_deskripsi.isEmpty()){
                    info_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                }else{
                    presenter.createInformasi(text_info_judul, text_info_deskripsi, sessionManager.getSessionDosenNamaD(), sessionManager.getSessionDosenFoto());
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onSucces() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}