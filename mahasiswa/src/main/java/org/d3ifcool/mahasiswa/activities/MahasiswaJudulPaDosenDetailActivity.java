package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.interfaces.objects.DosenView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.presenters.DosenPresenter;

public class MahasiswaJudulPaDosenDetailActivity extends AppCompatActivity implements DosenView {

    private DosenPresenter dosenPresenter;
    private ProgressDialog dialog;
    private ProgressDialog progressDialog;
    private TextView textViewDosenPembimbing;
    private Dosen parcelDosen;

    FloatingActionButton button_pengajuan;

    public static final String EXTRA_JUDUL = "extra_judul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_dosen_detail);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        textViewDosenPembimbing  = findViewById(R.id.frg_mhs_pa_textview_dsn_pembimbing);
        TextView textViewJudulPa  = findViewById(R.id.frg_mhs_pa_textview_judulpa);
        TextView textViewKategori  = findViewById(R.id.frg_mhs_pa_textview_kategori);
        TextView textViewJudulDeskripsi  = findViewById(R.id.frg_mhs_pa_textview_deskripsi);
        button_pengajuan = findViewById(R.id.act_mhs_pengajuan_dosen_fab);
        dosenPresenter = new DosenPresenter(this);


        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        int extraJudulId = extraJudul.getId();
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        String extraJudulStatus = extraJudul.getJudul_status();
        String extraJudulNipDosen = extraJudul.getNip_dosen();
        String extraKategoriId = extraJudul.getKategori_id();
        String extraKategoriNama = extraJudul.getKategori_nama();

        dosenPresenter.getDosenByParameter(extraJudulNipDosen);

        textViewJudulPa.setText(extraJudulNama);
        textViewKategori.setText(extraKategoriNama);
        textViewJudulDeskripsi.setText(extraJudulDeskripsi);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));


        button_pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MahasiswaJudulPaDosenDetailActivity.this, MahasiswaJudulPaPengajuanDosenTambahActivity.class);
                Judul parcelJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
                intent.putExtra(MahasiswaJudulPaPengajuanDosenTambahActivity.EXTRA_JUDUL, parcelJudul);
                intent.putExtra(MahasiswaJudulPaPengajuanDosenTambahActivity.EXTRA_DOSEN, parcelDosen);
                startActivity(intent);

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
    public void onGetObjectDosen(Dosen dosen) {
        textViewDosenPembimbing.setText(dosen.getDsn_nama());
        parcelDosen = dosen;
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
