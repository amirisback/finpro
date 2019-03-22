package org.d3ifcool.superuser.activities.editors;

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

import org.d3ifcool.service.interfaces.DosenViewEditor;
import org.d3ifcool.service.interfaces.DosenViewResult;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;

public class KoorDosenTambahActivity extends AppCompatActivity implements DosenViewEditor {

    private ArrayList<Dosen> dosens = new ArrayList<>();
    private DosenPresenter presenter;
    private ProgressDialog dialog;
    private EditText et_nip_dosen, et_nama, et_kode,et_email, et_kontak;
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
        et_email = findViewById(R.id.act_koor_edittext_email);
        et_kontak = findViewById(R.id.act_koor_edittext_kontak);
        btn_simpan = findViewById(R.id.act_koor_dosen_button_simpan);


        presenter = new DosenPresenter(this, KoorDosenTambahActivity.this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nip = et_nip_dosen.getText().toString();
                 String nama = et_nama.getText().toString();
                 String kode = et_kode.getText().toString();
                 String email = et_email.getText().toString();
                 String kontak = et_kontak.getText().toString();

                if (nip.isEmpty()){
                    et_nip_dosen.setError("nip tidak boleh kosong");
                }else if (nama.isEmpty()){
                    et_nama.setError("nama tidak boleh kosong");
                }else if (kode.isEmpty()){
                    et_kode.setError("kode dosen tidak boleh kosong");
//                }else if (email.isEmpty()){
//                    et_email.setError("email tidak boleh kosong");
//                }else if (kontak.isEmpty()){
//                    et_kontak.setError("kontak tidak boleh kosong");
                }else{
                    presenter.createDosen(nip,nama,kode,kontak,email);
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
