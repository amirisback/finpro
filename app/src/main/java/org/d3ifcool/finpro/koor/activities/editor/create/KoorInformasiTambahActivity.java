package org.d3ifcool.finpro.koor.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;
import org.d3ifcool.finpro.core.presenters.NotifikasiPresenter;
import org.d3ifcool.finpro.R;

import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.NOTIF_KATEGORI_INFORMASI;
import static org.d3ifcool.finpro.core.helpers.ConstantNotif.ConstantaNotif.UNTUK_SEMUA;

public class KoorInformasiTambahActivity extends AppCompatActivity implements InformasiWorkView, NotifikasiWorkView {

    private ProgressDialog progressDialog;
    private InformasiPresenter informasiPresenter;
    private SessionManager sessionManager;
    private NotifikasiPresenter notifikasiPresenter;
    private String et_judul;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_informasi_tambah);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);

        informasiPresenter = new InformasiPresenter(this);
        notifikasiPresenter = new NotifikasiPresenter(this);

        informasiPresenter.initContext(this);
        notifikasiPresenter.initContext(this);

        setTitle(getString(R.string.title_informasi_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText judul = findViewById(R.id.act_koor_edittext_judul);
        final EditText deskripsi = findViewById(R.id.act_koor_edittext_deskripsi);
        Button btn_simpan = findViewById(R.id.act_koor_info_button_simpan);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_judul = judul.getText().toString();
                String et_deskripsi = deskripsi.getText().toString();
                if (et_judul.isEmpty()){
                    judul.setError("judul tidak boleh kosong");
                }else if (et_deskripsi.isEmpty()){
                    judul.setError("deskripsi tidak boleh kosong");
                }else{
                    informasiPresenter.createInformasi(et_judul, et_deskripsi, sessionManager.getSessionKoorNama());
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
    public void onSuccesCreateNotifikasi() {
        finish();
    }

    @Override
    public void onSucces() {
        notifikasiPresenter.createNotifikasi(NOTIF_KATEGORI_INFORMASI(sessionManager.getSessionKoorNama()), et_judul, sessionManager.getSessionKoorNama(), UNTUK_SEMUA);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
