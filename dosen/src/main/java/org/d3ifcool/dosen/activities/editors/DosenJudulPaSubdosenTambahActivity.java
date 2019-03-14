package org.d3ifcool.dosen.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewEditor;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewResult;
import org.d3ifcool.service.presenter.JudulPaPresenter;

public class DosenJudulPaSubdosenTambahActivity extends AppCompatActivity implements JudulPaSubDosenViewEditor {
    private Spinner spinner_kategori;
    private EditText et_judul, et_deskripsi;
    private Button btn_simpan;
    private JudulPaPresenter presenter;
    private SessionManager sessionManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_tambah);

        et_judul = findViewById(R.id.act_dsn_judul_pa_edittext_judul);
        et_deskripsi = findViewById(R.id.act_dsn_judul_pa_edittext_deskripsi);
        spinner_kategori = findViewById(R.id.act_dsn_judul_pa_edittext_kategori);
        btn_simpan = findViewById(R.id.act_dsn_judul_pa_button_simpan);

        setTitle(R.string.title_judulpa_dosen_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new JudulPaPresenter(this, DosenJudulPaSubdosenTambahActivity.this);

        sessionManager = new SessionManager(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("tunggu sebentar...");

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String judul = et_judul.getText().toString();
                String kategori = spinner_kategori.getSelectedItem().toString();
                String deskripsi = et_deskripsi.getText().toString();
                if(judul.isEmpty()){
                    et_judul.setError("judul tidak boleh kosong");
                }else if(deskripsi.isEmpty()){
                    et_deskripsi.setError("deskripsi tidak boleh kosong");
                }else{
//                    presenter.createJudul(judul, deskripsi, kategori, sessionManager.getSessionDosenNip());
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
}
