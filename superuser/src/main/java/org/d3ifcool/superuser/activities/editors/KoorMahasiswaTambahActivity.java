package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.MahasiswaViewEditor;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.presenters.MahasiswaPresenter;
import org.d3ifcool.superuser.R;

public class KoorMahasiswaTambahActivity extends AppCompatActivity implements MahasiswaViewEditor {

    private MahasiswaPresenter presenter;
    private ProgressDialog dialog;
    private EditText et_nim, et_nama, et_angkatan, et_kontak, et_email;
    private Button btn_simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_tambah);

        setTitle(getString(R.string.title_mahasiswa_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new MahasiswaPresenter(this, KoorMahasiswaTambahActivity.this);
        dialog = new ProgressDialog(this);

        et_nim = findViewById(R.id.act_koor_edittext_nim_mahasiswa);
        et_nama = findViewById(R.id.act_koor_edittext_nama_mahasiswa);
        et_angkatan = findViewById(R.id.act_koor_edittext_angkatan_mahasiswa);
        et_kontak = findViewById(R.id.act_koor_edittext_kontak_mahasiswa);
        et_email = findViewById(R.id.act_koor_edittext_email_mahasiswa);
        btn_simpan = findViewById(R.id.act_koor_mahasiswa_button_simpan);

        dialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = et_nim.getText().toString();
                String nama = et_nama.getText().toString();
                String angkatan = et_angkatan.getText().toString();
                String kontak = et_kontak.getText().toString();
                String email = et_email.getText().toString();

                if (nim.isEmpty()) {
                    et_nim.setError("nim harus di isi");
                } else if (nama.isEmpty()) {
                    et_nama.setError("nim harus di isi");
                } else if (angkatan.isEmpty()) {
                    et_angkatan.setError("nim harus di isi");
                }else {
                    presenter.createMahasiswa(nim, nama, angkatan, kontak, email);
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
