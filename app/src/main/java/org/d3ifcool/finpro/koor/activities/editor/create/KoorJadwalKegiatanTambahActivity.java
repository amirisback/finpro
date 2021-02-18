package org.d3ifcool.finpro.koor.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.interfaces.works.KegiatanWorkView;
import org.d3ifcool.finpro.core.presenters.KegiatanPresenter;
import org.d3ifcool.finpro.R;

public class KoorJadwalKegiatanTambahActivity extends AppCompatActivity implements KegiatanWorkView {

    private ProgressDialog progressDialog;
    private KegiatanPresenter kegiatanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_jadwal_kegiatan_tambah);

        setTitle(getString(R.string.title_jadwal_kegiatan_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        MethodHelper methodHelper = new MethodHelper();
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        kegiatanPresenter = new KegiatanPresenter(this);
        kegiatanPresenter.initContext(this);

        final EditText editTextKegiatan = findViewById(R.id.act_koor_jadwal_kegiatan);
        final EditText editTextPelaku = findViewById(R.id.act_koor_jadwal_pelaku);
        final EditText editTextKeterangan = findViewById(R.id.act_koor_jadwal_keterangan);
        final TextView textViewTanggalMulai = findViewById(R.id.act_koor_jadwal_tanggal_mulai);
        final TextView textViewTanggalBerakhir = findViewById(R.id.act_koor_jadwal_tanggal_berakhir);
        Button buttonSimpan = findViewById(R.id.act_koor_jadwal_button_simpan);

        methodHelper.setDatePickerNoMax(this, textViewTanggalMulai);
        methodHelper.setDatePickerNoMax(this, textViewTanggalBerakhir);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String kegiatan = editTextKegiatan.getText().toString();
                String pelaku = editTextPelaku.getText().toString();
                String keterangan = editTextKeterangan.getText().toString();
                String tanggalMulai = textViewTanggalMulai.getText().toString();
                String tanggalBerakhir = textViewTanggalBerakhir.getText().toString();

                kegiatanPresenter.createKegiatan(kegiatan, tanggalMulai, tanggalBerakhir, pelaku, keterangan);
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
