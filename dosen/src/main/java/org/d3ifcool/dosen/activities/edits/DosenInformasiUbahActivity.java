package org.d3ifcool.dosen.activities.edits;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.InformasiViewEditor;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenter.InformasiPresenter;

public class DosenInformasiUbahActivity extends AppCompatActivity implements InformasiViewEditor {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;
    private InformasiPresenter presenter;
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

        presenter = new InformasiPresenter(this, DosenInformasiUbahActivity.this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progress_dialog));

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul_baru = info_judul.getText().toString();
                String isi_baru = info_deskripsi.getText().toString();
                presenter.updateInformasi(extraInfo.getId(), judul_baru, isi_baru);

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
