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
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.presenters.JudulPresenter;

public class DosenJudulPaSubdosenUbahActivity extends AppCompatActivity implements JudulWorkView {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Judul extradata;
    private Spinner spinner_kategori;
    private EditText et_judul, et_deskripsi;
    private Button btn_update;
    private JudulPresenter presenter;
    private SessionManager sessionManager;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_ubah);

        setTitle(getString(R.string.title_judulpa_dosen_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_judul = findViewById(R.id.act_dsn_judul_pa_edittext_judul);
        et_deskripsi = findViewById(R.id.act_dsn_judul_pa_edittext_deskripsi);
        spinner_kategori = findViewById(R.id.act_dsn_judul_pa_edittext_kategori);
        btn_update = findViewById(R.id.act_dsn_judul_pa_button_simpan);

        extradata = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String extra_judul = extradata.getJudul();
        String extra_deskripsi = extradata.getDeskripsi();
        final int id = extradata.getId();

        et_judul.setText(extra_judul);
        et_deskripsi.setText(extra_deskripsi);

        presenter = new JudulPresenter(this);

        sessionManager = new SessionManager(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        btn_update.setOnClickListener(new View.OnClickListener() {
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
                    presenter.updateJudul(id, judul, kategori, deskripsi, sessionManager.getSessionDosenNip());
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