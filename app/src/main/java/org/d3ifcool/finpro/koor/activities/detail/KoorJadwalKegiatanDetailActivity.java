package org.d3ifcool.finpro.koor.activities.detail;

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

import org.d3ifcool.finpro.core.interfaces.works.KegiatanWorkView;
import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.core.presenters.KegiatanPresenter;
import org.d3ifcool.finpro.koor.activities.editor.update.KoorJadwalKegiatanUbahActivity;
import org.d3ifcool.finpro.R;

public class KoorJadwalKegiatanDetailActivity extends AppCompatActivity implements KegiatanWorkView {

    public static final String EXTRA_JADWAL_KEGIATAN = "extra_kegiatan";
    private Kegiatan extraKegiatan;
    private ProgressDialog progressDialog;
    private KegiatanPresenter kegiatanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_jadwal_kegiatan_detail);

        setTitle(getString(R.string.title_jadwal_kegiatan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        kegiatanPresenter = new KegiatanPresenter(this);
        kegiatanPresenter.initContext(this);

        extraKegiatan = getIntent().getParcelableExtra(EXTRA_JADWAL_KEGIATAN);
        String stringExtraKegiatan = extraKegiatan.getKegiatan();
        String stringExtraTanggalMulai = extraKegiatan.getTanggal_mulai();
        String stringExtraTanggalBerakhir = extraKegiatan.getTanggal_berakhir();
        String stringExtraPelaku = extraKegiatan.getPelaku();
        String stringExtraKeterangan = extraKegiatan.getKeterangan();

        TextView textViewKegiatan = findViewById(R.id.all_jadwal_detail_kegiatan);
        TextView textViewTanggalMulai = findViewById(R.id.all_jadwal_detail_tanggal_mulai);
        TextView textViewTanggalBerakhir = findViewById(R.id.all_jadwal_detail_tanggal_berakhir);
        TextView textViewPelaku = findViewById(R.id.all_jadwal_detail_pelaku);
        TextView textViewKeterangan = findViewById(R.id.all_jadwal_detail_keterangan);

        textViewKegiatan.setText(stringExtraKegiatan);
        textViewTanggalMulai.setText(stringExtraTanggalMulai);
        textViewTanggalBerakhir.setText(stringExtraTanggalBerakhir);
        textViewPelaku.setText(stringExtraPelaku);
        textViewKeterangan.setText(stringExtraKeterangan);

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
            Intent intentUbah = new Intent(KoorJadwalKegiatanDetailActivity.this, KoorJadwalKegiatanUbahActivity.class);
            intentUbah.putExtra(KoorJadwalKegiatanDetailActivity.EXTRA_JADWAL_KEGIATAN, extraKegiatan);
            startActivity(intentUbah);

        } else if (i == R.id.toolbar_menu_hapus) {
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            kegiatanPresenter.deleteKegiatan(extraKegiatan.getKegiatan_id());
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
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
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
