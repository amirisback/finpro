package org.d3ifcool.koor.activities.editors.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.base.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.base.presenters.MahasiswaPresenter;
import org.d3ifcool.koor.R;

public class KoorMahasiswaTambahActivity extends AppCompatActivity implements MahasiswaWorkView {

    private MahasiswaPresenter presenter;
    private ProgressDialog dialog;
    private EditText et_nim, et_nama;
    private Button btn_simpan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_tambah);

        setTitle(getString(R.string.title_mahasiswa_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        presenter = new MahasiswaPresenter(this);
        dialog = new ProgressDialog(this);

        et_nim = findViewById(R.id.act_koor_edittext_nim_mahasiswa);
        et_nama = findViewById(R.id.act_koor_edittext_nama_mahasiswa);
        btn_simpan = findViewById(R.id.act_koor_mahasiswa_button_simpan);

        dialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nim = et_nim.getText().toString();
                String nama = et_nama.getText().toString();

                if (nim.isEmpty()) {
                    et_nim.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (nama.isEmpty()) {
                    et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
                }else {
                    presenter.createMahasiswa(nim, nama);
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
