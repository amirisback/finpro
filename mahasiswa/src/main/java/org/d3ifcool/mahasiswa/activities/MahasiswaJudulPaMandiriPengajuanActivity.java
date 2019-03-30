package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.service.interfaces.objects.JudulView;
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.service.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;
import org.d3ifcool.service.presenters.MahasiswaPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaJudulPaMandiriPengajuanActivity extends AppCompatActivity
        implements ProyekAkhirWorkView, MahasiswaWorkView, JudulWorkView,
        KategoriJudulListView, DosenListView, JudulView {

    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();
    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();
    private Spinner sp_dosen, sp_kategori;

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private JudulPresenter judulPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private ProgressDialog progressDialog;

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_mandiri_pengajuan);

        setTitle(getString(R.string.title_pengajuan_judulpa));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dosenPresenter = new DosenPresenter(this);
        mahasiswaPresenter = new MahasiswaPresenter(this);
        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        sp_dosen = findViewById(R.id.act_mhs_list_dosen_pengajuan);
        sp_kategori = findViewById(R.id.act_mhs_list_kategori);

        final EditText editTextJudul = findViewById(R.id.act_mhs_pa_mandiri_judul);
        final EditText editTextDeskripsi = findViewById(R.id.act_mhs_pa_mandiri_deskripsi);
        final EditText editTextNamaKelompok = findViewById(R.id.act_mhs_pa_mandiri_kelompok);
        final EditText editTextNIM2 = findViewById(R.id.act_mhs_pa_mandiri_nim_anggota_2);
        final EditText editTextNama2 = findViewById(R.id.act_mhs_pa_mandiri_nama_anggota_2);
        TextView textViewNIM1 = findViewById(R.id.act_mhs_pa_mandiri_nim_anggota_1);
        TextView textViewNama1 = findViewById(R.id.act_mhs_pa_mandiri_nama_anggota_1);
        Button buttonAjukan = findViewById(R.id.act_mhs_pa_mandiri_simpan);

        dosenPresenter.getDosen();
        kategoriJudulPresenter.getKategori();

        textViewNama1.setText(sessionManager.getSessionMahasiswaNama());
        textViewNIM1.setText(sessionManager.getSessionMahasiswaNim());

        buttonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String judul = editTextJudul.getText().toString();
                String deskripsi = editTextDeskripsi.getText().toString();
                String kelompok = editTextNamaKelompok.getText().toString();
                String nim2 = editTextNIM2.getText().toString();
                String nama2 = editTextNama2.getText().toString();




            }
        });



    }

    private void initSpinnerNamaDosen(ArrayList<Dosen> arrayDosen, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayDosen.size(); i++) {
            spinnerItem.add(arrayDosen.get(i).getDsn_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void initSpinnerKategori(ArrayList<KategoriJudul> arrayKategori, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayKategori.size(); i++) {
            spinnerItem.add(arrayKategori.get(i).getKategori_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
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
    public void onGetObjectJudul(Judul judul) {

    }

    @Override
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategoriJudul.clear();
        arrayListKategoriJudul.addAll(kategori);
        initSpinnerKategori(arrayListKategoriJudul, sp_kategori);
    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        initSpinnerNamaDosen(arrayListDosen,sp_dosen);
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
