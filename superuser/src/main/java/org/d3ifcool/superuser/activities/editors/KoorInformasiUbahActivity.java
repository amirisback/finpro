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

import org.d3ifcool.service.interfaces.works.InformasiWorkView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenters.InformasiPresenter;
import org.d3ifcool.superuser.R;

public class KoorInformasiUbahActivity extends AppCompatActivity implements InformasiWorkView {
    private EditText et_judul, et_isi;
    private Button btn_simpan;
    private InformasiPresenter presenter;
    private ProgressDialog dialog;

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_informasi_ubah);
        setTitle(getString(org.d3ifcool.dosen.R.string.title_informasi_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraInfo.getInfo_judul();
        final String isi = extraInfo.getInfo_deskripsi();

        et_judul = findViewById(R.id.act_koor_edittext_judul);
        et_isi = findViewById(R.id.act_koor_edittext_deskripsi);

        et_judul.setText(judul);
        et_isi.setText(isi);

        btn_simpan = findViewById(R.id.act_koor_info_button_simpan);
        presenter = new InformasiPresenter(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                        .Builder(KoorInformasiUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah))
                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String judul_baru = et_judul.getText().toString();
                                String isi_baru = et_isi.getText().toString();
                                presenter.updateInformasi(extraInfo.getId(), judul_baru, isi_baru);
                            }
                        })
                        .setNegativeButton(org.d3ifcool.dosen.R.string.tidak, null)
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
