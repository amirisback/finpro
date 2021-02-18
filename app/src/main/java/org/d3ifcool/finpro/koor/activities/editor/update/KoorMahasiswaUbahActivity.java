package org.d3ifcool.finpro.koor.activities.editor.update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.R;

public class KoorMahasiswaUbahActivity extends AppCompatActivity implements MahasiswaWorkView {

    private MahasiswaPresenter mahasiswaPresenter;
    private ProgressDialog progressDialog;
    private EditText et_nim,et_nama,et_angkatan, et_kontak, et_email;
    private Button btn_ubah;

    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";
    private Mahasiswa extraMahasiswa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_ubah);

        setTitle(getString(R.string.title_mahasiswa_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mahasiswaPresenter = new MahasiswaPresenter(this);
        mahasiswaPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        et_nim = findViewById(R.id.act_koor_edittext_nim_mahasiswa);
        et_nama = findViewById(R.id.act_koor_edittext_nama_mahasiswa);
        et_angkatan = findViewById(R.id.act_koor_edittext_angkatan_mahasiswa);
        et_kontak = findViewById(R.id.act_koor_edittext_kontak_mahasiswa);
        et_email = findViewById(R.id.act_koor_edittext_email_mahasiswa);
        btn_ubah = findViewById(R.id.act_koor_mahasiswa_button_simpan);

        extraMahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);
        String nim = extraMahasiswa.getMhs_nim();
        String nama = extraMahasiswa.getMhs_nama();
        final String angkatan = extraMahasiswa.getAngkatan();
        final String kontak = extraMahasiswa.getMhs_kontak();
        final String email = extraMahasiswa.getMhs_email();

        et_nim.setText(nim);
        et_nama.setText(nama);
        et_angkatan.setText(angkatan);
        et_kontak.setText(kontak);
        et_email.setText(email);

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                        .Builder(KoorMahasiswaUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                                final String nama_baru = et_nama.getText().toString();
                                final String angkatan_baru = et_angkatan.getText().toString();
                                final String kontak_baru = et_kontak.getText().toString();
                                final String email_baru = et_email.getText().toString();

                                if (nama_baru.isEmpty()) {
                                    et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
                                } else if (angkatan_baru.isEmpty()) {
                                    et_angkatan.setError(getString(R.string.text_tidak_boleh_kosong));
//                                } else if (email_baru.isEmpty()) {
//                                    et_email.setError("emai harus di isi");
//                                } else if (kontak_baru.isEmpty()) {
//                                    et_kontak.setError("kontak harus di isi");
                                } else {
                                    mahasiswaPresenter.updateMahasiswa(extraMahasiswa.getMhs_nim(), nama_baru, angkatan_baru, kontak_baru, email_baru);

                                }
//
                            }
                        })
                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
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
