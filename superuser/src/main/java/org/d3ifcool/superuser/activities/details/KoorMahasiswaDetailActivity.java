package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.MahasiswaViewEditor;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.models.Mahasiswa;
import org.d3ifcool.service.presenters.MahasiswaPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorMahasiswaUbahActivity;

import java.util.ArrayList;

public class KoorMahasiswaDetailActivity extends AppCompatActivity implements MahasiswaViewEditor {
    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";
    private Mahasiswa extraMahasiswa;
    private MahasiswaPresenter presenter;
    private ProgressDialog dialog;
    private TextView tv_nama, tv_nim, tv_kontak, tv_email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        presenter = new MahasiswaPresenter(this, KoorMahasiswaDetailActivity.this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.progress_dialog));
        tv_nama = findViewById(R.id.act_koor_profil_nama_mhs);
        tv_nim = findViewById(R.id.act_koor_profil_nim);
        tv_kontak = findViewById(R.id.act_koor_profil_kontak_mhs);
        tv_email = findViewById(R.id.act_koor_profil_email_mhs);

        extraMahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);
        String nim = extraMahasiswa.getMhs_nim();
        String nama = extraMahasiswa.getMhs_nama();
        String kontak = extraMahasiswa.getMhs_kontak();
        String email = extraMahasiswa.getMhs_email();

        tv_nama.setText(nama);
        tv_nim.setText(nim);
        tv_email.setText(email);
        tv_kontak.setText(kontak);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intent = new Intent(this, KoorMahasiswaUbahActivity.class);
            intent.putExtra(KoorMahasiswaUbahActivity.EXTRA_MAHASISWA, extraMahasiswa);
            startActivity(intent);
            finish();
        } else if (i == R.id.toolbar_menu_hapus) {
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            presenter.deleteMahasiswa(extraMahasiswa.getMhs_nim());
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
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
