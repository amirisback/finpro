package org.d3ifcool.finpro.koor.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.works.DosenWorkView;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.koor.activities.editor.update.KoorDosenUbahActivity;
import org.d3ifcool.finpro.R;

public class KoorDosenTambahActivity extends AppCompatActivity implements DosenWorkView {

    private DosenPresenter dosenPresenter;
    private ProgressDialog progressDialog;
    private EditText et_nip_dosen, et_nama, et_kode;
    private Button btn_simpan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_dosen_tambah);

        setTitle(getString(R.string.title_tambah_dosen));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        et_nip_dosen = findViewById(R.id.act_koor__dosen_edittext_nipdosen);
        et_nama = findViewById(R.id.act_koor_edittext_namadosen);
        et_kode = findViewById(R.id.act_koor_edittext_kodedosen);
        btn_simpan = findViewById(R.id.act_koor_dosen_button_simpan);

        dosenPresenter = new DosenPresenter(this);
        dosenPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nip = et_nip_dosen.getText().toString();
                 String nama = et_nama.getText().toString();
                 String kode = et_kode.getText().toString();

                if (nip.isEmpty()){
                    et_nip_dosen.setError("nip tidak boleh kosong");
                }else if (nama.isEmpty()){
                    et_nama.setError("nama tidak boleh kosong");
                }else if (kode.isEmpty()){
                    et_kode.setError("kode dosen tidak boleh kosong");
                }else{
                    dosenPresenter.createDosen(nip,nama,kode);
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
            Intent intent = new Intent(this, KoorDosenUbahActivity.class);

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
