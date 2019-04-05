package org.d3ifcool.mahasiswa.activities;

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

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.service.presenters.MahasiswaPresenter;

public class MahasiswaProfilUbahActivity extends AppCompatActivity implements MahasiswaWorkView {
    private ProgressDialog dialog;
    private SessionManager manager;
    private MahasiswaPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_profil_ubah);
        getSupportActionBar().setElevation(0f);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));
        manager = new SessionManager(this);
        presenter = new MahasiswaPresenter(this);

        TextView tv_nim = findViewById(R.id.act_mhs_profil_nim);
        final EditText et_nama = findViewById(R.id.act_mhs_profil_nama);
        final EditText et_angkatan= findViewById(R.id.act_mhs_profil_angkatan);
        final EditText et_email = findViewById(R.id.act_mhs_profil_email);
        final EditText et_kontak = findViewById(R.id.act_mhs_profil_kontak);

        final String nama = manager.getSessionMahasiswaNama();
        String angkatan = manager.getSessionMahasiswaAngkatan();
        String kontak = manager.getSessionMahasiswaKontak();
        String email = manager.getSessionMahasiswaEmail();

        tv_nim.setText(manager.getSessionMahasiswaNim());
        et_nama.setText(nama);
        et_angkatan.setText(angkatan);
        et_kontak.setText(kontak);
        et_email.setText(email);

        Button btn_ubah = findViewById(R.id.act_mhs_profil_button);
        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nama_baru = et_nama.getText().toString();
                String angkatan_baru = et_angkatan.getText().toString();
                String kontak_baru = et_kontak.getText().toString();
                String email_baru = et_email.getText().toString();
                if (nama_baru.isEmpty()){
                    et_nama.setError("Field ini harus di isi");
                }else {
                    presenter.updateMahasiswa(manager.getSessionMahasiswaNim(), nama_baru, angkatan_baru, kontak_baru,email_baru);
                }
            }
        });
        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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