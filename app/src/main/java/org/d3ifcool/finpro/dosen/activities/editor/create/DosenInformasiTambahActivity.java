package org.d3ifcool.finpro.dosen.activities.editor.create;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;

import androidx.appcompat.app.AppCompatActivity;

import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.NOTIF_KATEGORI_INFORMASI;
import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.UNTUK_SEMUA;

public class DosenInformasiTambahActivity extends AppCompatActivity implements InformasiWorkView, NotifikasiWorkView {

    private ProgressDialog progressDialog;
    private InformasiPresenter informasiPresenter;
    private SessionManager sessionManager;
    private NotifikasiPresenter notifikasiPresenter;
    private String text_info_judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_tambah);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        informasiPresenter = new InformasiPresenter(this);
        informasiPresenter.initContext(this);

        notifikasiPresenter = new NotifikasiPresenter(this);

        setTitle(R.string.title_informasi_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText info_judul = findViewById(R.id.act_dsn_edittext_judul);
        final EditText info_deskripsi = findViewById(R.id.act_dsn_edittext_deskripsi);
        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text_info_judul = info_judul.getText().toString();
                String text_info_deskripsi = info_deskripsi.getText().toString();
                if (text_info_judul.isEmpty()) {
                    info_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (text_info_deskripsi.isEmpty()) {
                    info_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                } else {
                    informasiPresenter.createInformasi(text_info_judul, text_info_deskripsi, sessionManager.getSessionDosenNama());
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
    public void onSuccesCreateNotifikasi() {
        finish();
    }

    @Override
    public void onSucces() {
        notifikasiPresenter.createNotifikasi(NOTIF_KATEGORI_INFORMASI(sessionManager.getSessionDosenNama()), text_info_judul, sessionManager.getSessionDosenNama(), UNTUK_SEMUA);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


}