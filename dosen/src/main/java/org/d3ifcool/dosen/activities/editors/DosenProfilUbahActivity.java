package org.d3ifcool.dosen.activities.editors;

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
    private DosenPresenter presenter;
    private SessionManager manager;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_profil_ubah);

        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        presenter = new DosenPresenter(this);
        manager = new SessionManager(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        final EditText et_nama = findViewById(R.id.act_dsn_profil_nama);
        TextView et_nip = findViewById(R.id.act_dsn_profil_nip);
        final EditText et_kode = findViewById(R.id.act_dsn_profil_kode);
        final EditText et_email = findViewById(R.id.act_dsn_profil_email);
        final EditText et_kontak = findViewById(R.id.act_dsn_profil_kontak);
        Button btn_ubah = findViewById(R.id.act_dsn_profil_button);

        String nama = manager.getSessionDosenNama();
        String kode = manager.getSessionDosenKode();
        String email = manager.getSessionDosenEmail();
        String kontak = manager.getSessionDosenKontak();

        et_nama.setText(nama);
        et_nip.setText(manager.getSessionDosenNip());
        et_kode.setText(kode);
        et_email.setText(email);
        et_kontak.setText(kontak);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_baru = et_nama.getText().toString();
                String kode_baru = et_kode.getText().toString();
                String email_baru = et_email.getText().toString();
                String kontak_baru = et_kontak.getText().toString();
                if (nama_baru.isEmpty()){
                    et_nama.setError("Field ini harus di isi");
                }else if (kode_baru.isEmpty()){
                    et_kode.setError("Field ini harus di isi");
//                }else if (email_baru.isEmpty()){
//                    et_email.setError("Field ini harus di isi");
//                }else if (kontak_baru.isEmpty()){
//                    et_kontak.setError("Field ini harus di isi");
                }else {
                    presenter.UpdateDosen(manager.getSessionDosenNip(), nama_baru,kode_baru,kontak_baru,email_baru);
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
