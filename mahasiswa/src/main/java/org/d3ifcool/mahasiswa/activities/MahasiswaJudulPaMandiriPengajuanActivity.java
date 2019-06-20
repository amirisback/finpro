package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.base.helpers.SessionManager;
import org.d3ifcool.base.helpers.SpinnerHelper;
import org.d3ifcool.base.interfaces.lists.DosenListView;
import org.d3ifcool.base.interfaces.lists.JudulListView;
import org.d3ifcool.base.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.base.interfaces.objects.MahasiswaView;
import org.d3ifcool.base.interfaces.works.JudulWorkView;
import org.d3ifcool.base.interfaces.works.ProyekMahasiswaWorkView;
import org.d3ifcool.base.models.Dosen;
import org.d3ifcool.base.models.Judul;
import org.d3ifcool.base.models.KategoriJudul;
import org.d3ifcool.base.models.Mahasiswa;
import org.d3ifcool.base.presenters.DosenPresenter;
import org.d3ifcool.base.presenters.JudulPresenter;
import org.d3ifcool.base.presenters.KategoriJudulPresenter;
import org.d3ifcool.base.presenters.MahasiswaPresenter;
import org.d3ifcool.base.presenters.ProyekMahasiswaPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.base.helpers.Constant.ObjectConstanta.JUDUL_STATUS_PENDING;

public class MahasiswaJudulPaMandiriPengajuanActivity extends AppCompatActivity
        implements JudulWorkView, ProyekMahasiswaWorkView,
        KategoriJudulListView, DosenListView, JudulListView, MahasiswaView {

    private static final String PARAM_JUDUL = "judul.judul_nama";

    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();
    private ArrayList<Judul> arrayListJudul = new ArrayList<>();
    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();
    private Spinner spinner_dosen, spinner_kategori;
    private SpinnerHelper spinnerHelper;

    private ProyekMahasiswaPresenter proyekMahasiswaPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private JudulPresenter judulPresenter;
    private ProgressDialog progressDialog;
    private TextView textViewNama2;
    private SessionManager sessionManager;
    private String nim2, deskripsi, kelompok, judul, spinnerItemNipDosen;
    private int spinnerItemKategoriId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_mandiri_pengajuan);

        setTitle(getString(R.string.title_pengajuan_judulpa));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DosenPresenter dosenPresenter = new DosenPresenter(this);
        KategoriJudulPresenter kategoriJudulPresenter = new KategoriJudulPresenter(this);
        mahasiswaPresenter = new MahasiswaPresenter(this);
        judulPresenter = new JudulPresenter(this, this);
        proyekMahasiswaPresenter = new ProyekMahasiswaPresenter(this);

        dosenPresenter.initContext(this);
        kategoriJudulPresenter.initContext(this);
        mahasiswaPresenter.initContext(this);
        judulPresenter.initContext(this);
        proyekMahasiswaPresenter.initContext(this);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        spinnerHelper = new SpinnerHelper(this);

        spinner_dosen = findViewById(R.id.act_mhs_list_dosen_pengajuan);
        spinner_kategori = findViewById(R.id.act_mhs_list_kategori);

        final EditText editTextJudul = findViewById(R.id.act_mhs_pa_mandiri_judul);
        final EditText editTextDeskripsi = findViewById(R.id.act_mhs_pa_mandiri_deskripsi);
        final EditText editTextNamaKelompok = findViewById(R.id.act_mhs_pa_mandiri_kelompok);
        final EditText editTextNIM2 = findViewById(R.id.act_mhs_pa_mandiri_nim_anggota_2);
        textViewNama2 = findViewById(R.id.act_mhs_pa_mandiri_nama_anggota_2);
        TextView textViewNIM1 = findViewById(R.id.act_mhs_pa_mandiri_nim_anggota_1);
        TextView textViewNama1 = findViewById(R.id.act_mhs_pa_mandiri_nama_anggota_1);
        Button buttonAjukan = findViewById(R.id.act_mhs_pa_mandiri_simpan);

        dosenPresenter.getDosen();
        kategoriJudulPresenter.getKategori();

        textViewNama1.setText(sessionManager.getSessionMahasiswaNama());
        textViewNIM1.setText(sessionManager.getSessionMahasiswaNim());


        editTextNIM2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                findMahasiswa(editTextNIM2);
            }

            @Override
            public void afterTextChanged(Editable s) {
                findMahasiswa(editTextNIM2);
            }
        });

        spinner_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemNipDosen = arrayListDosen.get(position).getDsn_nip();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemKategoriId = arrayListKategoriJudul.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAjukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(MahasiswaJudulPaMandiriPengajuanActivity.this)
                        .setTitle(getString(R.string.dialog_pengajuan_judul_title))
                        .setMessage(getString(R.string.dialog_pengajuan_judul_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                judul = editTextJudul.getText().toString();
                                deskripsi = editTextDeskripsi.getText().toString();
                                kelompok = editTextNamaKelompok.getText().toString();
                                nim2 = editTextNIM2.getText().toString();

                                if (judul.isEmpty()) {
                                    editTextJudul.setError(getString(R.string.text_tidak_boleh_kosong));
                                } else if (deskripsi.isEmpty()) {
                                    editTextDeskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                                } else if (kelompok.isEmpty()) {
                                    editTextNamaKelompok.setError(getString(R.string.text_tidak_boleh_kosong));
                                } else {
                                    judulPresenter.createJudul(judul, spinnerItemKategoriId, deskripsi, spinnerItemNipDosen, JUDUL_STATUS_PENDING);
                                }

                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();


            }
        });

    }

    private void findMahasiswa(EditText editText){
        String nim_mhs = editText.getText().toString();
        if (!nim_mhs.isEmpty()) {
            mahasiswaPresenter.getMahasiswaByParameter(nim_mhs);
        } else {
            textViewNama2.setText(getText(R.string.hint_masukkan_nim));
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
                    textViewNama2.setText(mahasiswa.getMhs_nama());
                    textViewNama2.setError(null);
                } else {
                    textViewNama2.setText(getString(R.string.text_nim_dilarang));
                    textViewNama2.setError(getString(R.string.text_nim_dilarang));
                }
            } else {
                textViewNama2.setText(getString(R.string.text_mahasiswa_sudah_mengajukan_judul));
                textViewNama2.setError(getString(R.string.text_mahasiswa_sudah_mengajukan_judul));
            }
        } else {
            textViewNama2.setText(getString(R.string.hint_masukkan_nim));
        }
    }

    @Override
    public void isEmptyObjectMahasiswa() {

    }

    @Override
    public void onSuccesWorkJudul() {
        judulPresenter.searchJudulBy(PARAM_JUDUL, judul);
    }

    @Override
    public void onGetListJudul(List<Judul> judulpa) {
        arrayListJudul.clear();
        arrayListJudul.addAll(judulpa);
        int judul_id = arrayListJudul.get(arrayListJudul.size()-1).getId();
        String judul_status = arrayListJudul.get(arrayListJudul.size()-1).getJudul_status();
        sessionManager.createSessionJudulMahasiswa(judul_id);
        sessionManager.createSessionJudulStatusMahasiswa(judul_status);

        if (!nim2.isEmpty()) {
            proyekMahasiswaPresenter.createProyekAkhir2(judul_id, sessionManager.getSessionMahasiswaNim(), nim2, kelompok);
        } else {
            proyekMahasiswaPresenter.createProyekAkhir(judul_id, sessionManager.getSessionMahasiswaNim(), kelompok);
        }

    }

    @Override
    public void isEmptyListJudul() {

    }

    @Override
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategoriJudul.clear();
        arrayListKategoriJudul.addAll(kategori);
        spinnerHelper.initSpinnerKategoriJudul(arrayListKategoriJudul, spinner_kategori);
    }

    @Override
    public void isEmptyListKategori() {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        spinnerHelper.initSpinnerDosen(arrayListDosen, spinner_dosen);
    }

    @Override
    public void isEmptyListDosen() {

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
