package org.d3ifcool.finpro.mahasiswa.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.objects.MahasiswaView;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PICK_PDF_REQUEST;

public class MahasiswaJudulPaDosenPengajuanActivity extends AppCompatActivity implements 
        ProyekAkhirWorkView, MahasiswaWorkView, MahasiswaView {

    public static final String EXTRA_JUDUL = "extra_judul";

    private SessionManager sessionManager;
    private MethodHelper methodHelper;
    private ProgressDialog progressDialog;

    private ProyekAkhirPresenter proyekAkhirPresenter;
    private MahasiswaPresenter mahasiswaPresenter;

    private TextView textViewJudulNama2, textViewAttach;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_dosen_pengajuan);

        setTitle(getString(R.string.title_pengajuan_judulpa));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sessionManager = new SessionManager(this);
        methodHelper = new MethodHelper(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        mahasiswaPresenter = new MahasiswaPresenter(this, this);

        proyekAkhirPresenter.initContext(this);
        mahasiswaPresenter.initContext(this);

        TextView textViewJudul = findViewById(R.id.act_mhs_pa_pengajuan_textview_judul);
        TextView textViewJudulKategori = findViewById(R.id.act_mhs_pa_pengajuan_textview_kategori);
        TextView textViewJudulDeskripsi = findViewById(R.id.act_mhs_pa_pengajuan_textview_deskripsi);
        TextView textViewJudulPembimbing = findViewById(R.id.act_mhs_pa_pengajuan_textview_dosen_pembimbing);
        TextView textViewJudulNim1 = findViewById(R.id.act_mhs_pa_pengajuan_textview_nim_anggota_1);
        TextView textViewJudulNama1 = findViewById(R.id.act_mhs_pa_pengajuan_textview_nama_anggota_1);

        final EditText editTextJudulNim2 = findViewById(R.id.act_mhs_pa_pengajuan_editview_nim_anggota_2);
        final EditText editTextJudulKelompok = findViewById(R.id.act_mhs_pa_pengajuan_editview_nama_kelompok);
        textViewJudulNama2 = findViewById(R.id.act_mhs_pa_pengajuan_editview_nama_anggota_2);
        textViewAttach = findViewById(R.id.act_all_attach_file);
        Button buttonAjukan = findViewById(R.id.act_mhs_pa_pengajuan_button_simpan);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        final int extraJudulId = extraJudul.getId();
        final String extraJudulStatus = extraJudul.getJudul_status();
        final String extraDosenNip = extraJudul.getNip_dosen();

        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        String extraKategoriNama = extraJudul.getKategori_nama();
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

        textViewAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                methodHelper.selectFile(intent);
                startActivityForResult(Intent.createChooser(intent, getString(R.string.title_select_file)), PICK_PDF_REQUEST);
            }
        });

        editTextJudulNim2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                findMahasiswa(editTextJudulNim2);
            }

            @Override
            public void afterTextChanged(Editable s) {
                findMahasiswa(editTextJudulNim2);
            }
        });


        buttonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mahasiswaNim2 = editTextJudulNim2.getText().toString();
                String namaKelompok = editTextJudulKelompok.getText().toString();

                if (namaKelompok.isEmpty()) {
                    editTextJudulKelompok.setError(getString(R.string.text_tidak_boleh_kosong));
                } else {
                    if (!mahasiswaNim2.isEmpty()) {
                        proyekAkhirPresenter.createProyekAkhir(extraJudulId, extraMahasiswaNim1, namaKelompok);
                        proyekAkhirPresenter.createProyekAkhir(extraJudulId, mahasiswaNim2, namaKelompok);
                        mahasiswaPresenter.updateMahasiswaJudul(extraMahasiswaNim1, extraJudulId);
                        mahasiswaPresenter.updateMahasiswaJudul(mahasiswaNim2, extraJudulId);
                        sessionManager.createSessionJudulMahasiswa(extraJudulId);
                        sessionManager.createSessionJudulStatusMahasiswa(extraJudulStatus);
                    } else {
                        proyekAkhirPresenter.createProyekAkhir(extraJudulId, extraMahasiswaNim1, namaKelompok);
                        mahasiswaPresenter.updateMahasiswaJudul(extraMahasiswaNim1, extraJudulId);
                        sessionManager.createSessionJudulMahasiswa(extraJudulId);
                    }
                }
            }
        });
    }

    private void findMahasiswa(EditText editText){
        String nim_mhs = editText.getText().toString();
        if (!nim_mhs.isEmpty()) {
            mahasiswaPresenter.getMahasiswaByParameter(nim_mhs);
        } else {
            textViewJudulNama2.setText(getText(R.string.hint_masukkan_nim));
        }
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
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        if (mahasiswa.getMhs_nama() != null){
            if (mahasiswa.getJudul_id() == 0){
                if (!mahasiswa.getMhs_nama().equalsIgnoreCase(sessionManager.getSessionMahasiswaNama())){
                    textViewJudulNama2.setText(mahasiswa.getMhs_nama());
                    textViewJudulNama2.setError(null);
                } else {
                    textViewJudulNama2.setText(getString(R.string.text_nim_dilarang));
                    textViewJudulNama2.setError(getString(R.string.text_nim_dilarang));
                }
            } else {
                textViewJudulNama2.setText(getString(R.string.text_mahasiswa_sudah_mengajukan_judul));
                textViewJudulNama2.setError(getString(R.string.text_mahasiswa_sudah_mengajukan_judul));
            }

        } else {
            textViewJudulNama2.setText(getString(R.string.hint_masukkan_nim));
        }
    }

    @Override
    public void isEmptyObjectMahasiswa() {

    }

    @Override
    public void onSucces() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PDF_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            textViewAttach.setText(methodHelper.getOnActivityResult(data));
        }
    }
}
