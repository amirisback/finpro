package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.works.InformasiWorkView;
import org.d3ifcool.service.presenters.InformasiPresenter;
import org.d3ifcool.superuser.R;

public class KoorInformasiTambahActivity extends AppCompatActivity implements InformasiWorkView {
    private EditText judul, deskripsi;
    Button btn_simpan;
    private ProgressDialog progressDialog;
    private InformasiPresenter presenter;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_informasi_tambah);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        presenter = new InformasiPresenter(this);


        setTitle(getString(org.d3ifcool.dosen.R.string.title_informasi_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        judul = findViewById(R.id.act_koor_edittext_judul);
        deskripsi = findViewById(R.id.act_koor_edittext_deskripsi);
        btn_simpan = findViewById(R.id.act_koor_info_button_simpan);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_judul = judul.getText().toString();
                String et_deskripsi = deskripsi.getText().toString();
                if (et_judul.isEmpty()){
                    judul.setError("judul tidak boleh kosong");
                }else if (et_deskripsi.isEmpty()){
                    judul.setError("deskripsi tidak boleh kosong");
                }else{
                    presenter.createInformasi(et_judul, et_deskripsi, sessionManager.getSessionKoorNama());
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
