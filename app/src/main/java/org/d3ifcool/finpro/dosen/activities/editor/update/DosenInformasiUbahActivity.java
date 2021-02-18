package org.d3ifcool.finpro.dosen.activities.editor.update;

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

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.works.InformasiWorkView;
import org.d3ifcool.finpro.core.models.Informasi;
import org.d3ifcool.finpro.core.presenters.InformasiPresenter;

public class DosenInformasiUbahActivity extends AppCompatActivity implements InformasiWorkView {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;
    private InformasiPresenter informasiPresenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_ubah);

        setTitle(getString(R.string.title_informasi_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText info_judul = findViewById(R.id.act_dsn_edittext_judul);
        final EditText info_deskripsi = findViewById(R.id.act_dsn_edittext_deskripsi);
        Button btn_ubah = findViewById(R.id.act_dsn_info_button_simpan);

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraInfo.getInfo_judul();
        String isi = extraInfo.getInfo_deskripsi();

        info_judul.setText(judul);
        info_deskripsi.setText(isi);

        informasiPresenter = new InformasiPresenter(this);
        informasiPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(DosenInformasiUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String judul_baru = info_judul.getText().toString();
                                String isi_baru = info_deskripsi.getText().toString();
                                if (judul_baru.isEmpty()) {
                                    info_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                                } else if (judul_baru.isEmpty()) {
                                    info_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else {
                                    informasiPresenter.updateInformasi(extraInfo.getId(), judul_baru, isi_baru);
                                }
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
