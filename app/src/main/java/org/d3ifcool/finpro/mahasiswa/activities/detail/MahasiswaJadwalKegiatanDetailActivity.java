package org.d3ifcool.finpro.mahasiswa.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.R;

public class MahasiswaJadwalKegiatanDetailActivity extends AppCompatActivity {

    public static final String EXTRA_JADWAL_KEGIATAN = "extra_kegiatan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_jadwal_kegiatan_detail);

        setTitle(getString(R.string.title_jadwal_kegiatan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Kegiatan extraKegiatan = getIntent().getParcelableExtra(EXTRA_JADWAL_KEGIATAN);
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

}
