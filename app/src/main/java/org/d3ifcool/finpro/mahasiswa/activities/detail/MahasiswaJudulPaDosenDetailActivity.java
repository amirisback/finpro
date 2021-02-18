package org.d3ifcool.finpro.mahasiswa.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaJudulPaDosenPengajuanActivity;
import org.d3ifcool.finpro.core.models.Judul;

public class MahasiswaJudulPaDosenDetailActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    public static final String EXTRA_JUDUL = "extra_judul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_dosen_detail);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewDosenPembimbing  = findViewById(R.id.frg_mhs_pa_textview_dsn_pembimbing);
        TextView textViewJudulPa  = findViewById(R.id.frg_mhs_pa_textview_judulpa);
        TextView textViewKategori  = findViewById(R.id.frg_mhs_pa_textview_kategori);
        TextView textViewJudulDeskripsi  = findViewById(R.id.frg_mhs_pa_textview_deskripsi);
        FloatingActionButton button_pengajuan = findViewById(R.id.act_mhs_pengajuan_dosen_fab);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        String extraKategoriNama = extraJudul.getKategori_nama();
        String extraDosenNama = extraJudul.getDsn_nama();

        textViewJudulPa.setText(extraJudulNama);
        textViewKategori.setText(extraKategoriNama);
        textViewJudulDeskripsi.setText(extraJudulDeskripsi);
        textViewDosenPembimbing.setText(extraDosenNama);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));


        button_pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MahasiswaJudulPaDosenDetailActivity.this, MahasiswaJudulPaDosenPengajuanActivity.class);
                Judul parcelJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
                intent.putExtra(MahasiswaJudulPaDosenPengajuanActivity.EXTRA_JUDUL, parcelJudul);
                startActivity(intent);
                finish();
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

}
