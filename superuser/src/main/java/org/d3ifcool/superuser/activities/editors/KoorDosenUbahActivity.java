package org.d3ifcool.superuser.activities.editors;

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

import org.d3ifcool.service.interfaces.works.DosenWorkView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.superuser.R;

public class KoorDosenUbahActivity extends AppCompatActivity implements DosenWorkView {

    private EditText et_nip, et_nama,et_kode, et_kontak, et_email;
    private Button btn_simpan;
    private DosenPresenter presenter;
    private ProgressDialog progressDialog;

    public static final String EXTRA_DOSEN = "extra_dosen";
    private Dosen extraDosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_dosen_ubah);
        presenter = new DosenPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        extraDosen =getIntent().getParcelableExtra(EXTRA_DOSEN);
        String nip = extraDosen.getDsn_nip();
        String nama = extraDosen.getDsn_nama();
        String kode = extraDosen.getDsn_kode();
        String kontak = extraDosen.getDsn_kontak();
        String email = extraDosen.getDsn_email();

        et_nip = findViewById(R.id.act_koor_edittext_nipdosen);
        et_nama = findViewById(R.id.act_koor_edittext_namadosen);
        et_kode = findViewById(R.id.act_koor_edittext_kodedosen);
        et_kontak = findViewById(R.id.act_koor_edittext_kontak);
        et_email = findViewById(R.id.act_koor_edittext_email);
        btn_simpan = findViewById(R.id.act_koor_dosen_button_simpan);

        et_nip.setText(nip);
        et_nama.setText(nama);
        et_kode.setText(kode);
        et_kontak.setText(kontak);
        et_email.setText(email);

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                        .Builder(KoorDosenUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))
                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String nama_baru = et_nama.getText().toString();
                                String kode_baru = et_kode.getText().toString();
                                String kontak_baru = et_kontak.getText().toString();
                                String email_baru = et_email.getText().toString();
                                presenter.updateDosen(extraDosen.getDsn_nip(), nama_baru,kode_baru,kontak_baru,email_baru);
                            }
                        })
                        .setNegativeButton(org.d3ifcool.dosen.R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });


        setTitle(getString(R.string.title_dosen_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
