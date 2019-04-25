package org.d3ifcool.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.works.DosenWorkView;
import org.d3ifcool.service.presenters.DosenPresenter;

public class DosenProfilUbahActivity extends AppCompatActivity implements DosenWorkView {

    private DosenPresenter dosenPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    private String nama_baru, kode_baru, kontak_baru, email_baru;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_profil_ubah);

        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        dosenPresenter = new DosenPresenter(this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        final EditText et_nama = findViewById(R.id.act_dsn_profil_nama);
        TextView et_nip = findViewById(R.id.act_dsn_profil_nip);
        final EditText et_kode = findViewById(R.id.act_dsn_profil_kode);
        final EditText et_email = findViewById(R.id.act_dsn_profil_email);
        final EditText et_kontak = findViewById(R.id.act_dsn_profil_kontak);
        Button btn_ubah = findViewById(R.id.act_dsn_profil_button);

        String nama = sessionManager.getSessionDosenNama();
        String kode = sessionManager.getSessionDosenKode();
        String email = sessionManager.getSessionDosenEmail();
        String kontak = sessionManager.getSessionDosenKontak();

        et_nama.setText(nama);
        et_nip.setText(sessionManager.getSessionDosenNip());
        et_kode.setText(kode);
        et_email.setText(email);
        et_kontak.setText(kontak);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_baru = et_nama.getText().toString();
                kode_baru = et_kode.getText().toString();
                email_baru = et_email.getText().toString();
                kontak_baru = et_kontak.getText().toString();
                if (nama_baru.isEmpty()){
                    et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (kode_baru.isEmpty()){
                    et_kode.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (kontak_baru.length() < 7){
                    et_kontak.setError(getString(R.string.no_telp_kurang));
                }else if (kontak_baru.length() > 14 || kontak_baru.length() == 13){
                    et_kontak.setError(getString(R.string.no_lebih));
                } else if (!email_baru.matches(emailPattern)){
                    et_email.setError(getString(R.string.email_tidak_valid));
                }else {
                    dosenPresenter.updateDosen(sessionManager.getSessionDosenNip(), nama_baru,kode_baru,kontak_baru,email_baru);
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
        sessionManager.updateSessionDosen(nama_baru, kode_baru, kontak_baru, email_baru);
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
