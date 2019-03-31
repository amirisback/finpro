package org.d3ifcool.mahasiswa.activities;

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

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.service.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.presenters.MahasiswaPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;

public class MahasiswaJudulPaDosenPengajuanActivity extends AppCompatActivity implements ProyekAkhirWorkView, MahasiswaWorkView {


    public static final String EXTRA_JUDUL = "extra_judul";
    private SessionManager sessionManager;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_dosen_pengajuan);

        setTitle(getString(R.string.title_pengajuan_judulpa));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        mahasiswaPresenter = new MahasiswaPresenter(this);

        TextView textViewJudul = findViewById(R.id.act_mhs_pa_pengajuan_textview_judul);
        TextView textViewJudulKategori = findViewById(R.id.act_mhs_pa_pengajuan_textview_kategori);
        TextView textViewJudulDeskripsi = findViewById(R.id.act_mhs_pa_pengajuan_textview_deskripsi);
        TextView textViewJudulPembimbing = findViewById(R.id.act_mhs_pa_pengajuan_textview_dosen_pembimbing);
        TextView textViewJudulNim1 = findViewById(R.id.act_mhs_pa_pengajuan_textview_nim_anggota_1);
        TextView textViewJudulNama1 = findViewById(R.id.act_mhs_pa_pengajuan_textview_nama_anggota_1);
        final EditText editTextJudulNim2 = findViewById(R.id.act_mhs_pa_pengajuan_editview_nim_anggota_2);
        final EditText editTextJudulNama2 = findViewById(R.id.act_mhs_pa_pengajuan_editview_nama_anggota_2);
        final EditText editTextJudulKelompok = findViewById(R.id.act_mhs_pa_pengajuan_editview_nama_kelompok);
        Button buttonAjukan = findViewById(R.id.act_mhs_pa_pengajuan_button_simpan);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        final int extraJudulId = extraJudul.getId();
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        String extraJudulStatus = extraJudul.getJudul_status();
        String extraKategoriId = extraJudul.getKategori_id();
        String extraKategoriNama = extraJudul.getKategori_nama();
        final String extraDosenNip = extraJudul.getNip_dosen();
        String extraDosenNama = extraJudul.getDsn_nama();

        final String extraMahasiswaNim1 = sessionManager.getSessionMahasiswaNim();
        String extraMahasiswaNama1 = sessionManager.getSessionMahasiswaNama();

        textViewJudul.setText(extraJudulNama);
        textViewJudulKategori.setText(extraKategoriNama);
        textViewJudulDeskripsi.setText(extraJudulDeskripsi);
        textViewJudulPembimbing.setText(extraDosenNama);
        textViewJudulNim1.setText(extraMahasiswaNim1);
        textViewJudulNama1.setText(extraMahasiswaNama1);

        textViewJudul.setText(extraJudulNama);
        textViewJudul.setText(extraJudulNama);
        textViewJudul.setText(extraJudulNama);
        textViewJudul.setText(extraJudulNama);

        buttonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahasiswaNim2 = editTextJudulNim2.getText().toString();
                String mahasiswaNama2 = editTextJudulNama2.getText().toString();
                String namaKelompok = editTextJudulKelompok.getText().toString();

                if (!mahasiswaNim2.isEmpty()) {
                    proyekAkhirPresenter.createProyekAkhir(extraJudulId, extraMahasiswaNim1, namaKelompok);
                    proyekAkhirPresenter.createProyekAkhir(extraJudulId, mahasiswaNim2, namaKelompok);
                    mahasiswaPresenter.updateMahasiswaJudul(extraMahasiswaNim1, extraJudulId);
                    mahasiswaPresenter.updateMahasiswaJudul(mahasiswaNim2, extraJudulId);
                    sessionManager.createSessionJudulMahasiswa(extraJudulId);
                } else {
                    proyekAkhirPresenter.createProyekAkhir(extraJudulId, extraMahasiswaNim1, namaKelompok);
                    mahasiswaPresenter.updateMahasiswaJudul(extraMahasiswaNim1, extraJudulId);
                    sessionManager.createSessionJudulMahasiswa(extraJudulId);
                }
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
