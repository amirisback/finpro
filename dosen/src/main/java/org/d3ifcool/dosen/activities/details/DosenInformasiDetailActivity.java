package org.d3ifcool.dosen.activities.details;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.InformasiViewEditor;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.dosen.activities.edits.DosenInformasiUbahActivity;
import org.d3ifcool.service.presenter.InformasiPresenter;

public class DosenInformasiDetailActivity extends AppCompatActivity implements InformasiViewEditor {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;
    private InformasiPresenter presenter;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_detail);

        setTitle(getString(R.string.title_informasi_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView_judul = findViewById(R.id.act_dsn_info_detail_textview_judul);
        TextView textView_isi = findViewById(R.id.act_dsn_info_detail_textview_isi);
        TextView textView_tanggal = findViewById(R.id.act_dsn_info_detail_textview_tanggal);
        TextView textView_dosen = findViewById(R.id.act_dsn_info_detail_textview_dosen);

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraInfo.getInfo_judul();
        String isi = extraInfo.getInfo_deskripsi();
        String tanggal = extraInfo.getInfo_tanggal();
        String dosen = extraInfo.getPublisher();

        textView_judul.setText(judul);
        textView_isi.setText(isi);
        textView_tanggal.setText(tanggal);
        textView_dosen.setText(dosen);

        presenter = new InformasiPresenter(this, DosenInformasiDetailActivity.this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.progress_dialog));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(DosenInformasiDetailActivity.this, DosenInformasiUbahActivity.class);
            intentUbah.putExtra(DosenInformasiUbahActivity.EXTRA_INFORMASI, extraInfo);
            startActivity(intentUbah);
            finish();

        } else if (i == R.id.toolbar_menu_hapus) {
            presenter.deleteInformasi(extraInfo.getId());
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
