package org.d3ifcool.finpro.koor.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERSEDIA;

public class KoorJudulPaSubdosenTambahActivity extends AppCompatActivity implements JudulWorkView, DosenListView, KategoriJudulListView {

    public static final String EXTRA_POSITION_SPINNER = "extra_spinner";

    private Spinner sp_dosen, sp_kategori;
    private JudulPresenter judulPresenter;
    private DosenPresenter dosenPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private ProgressDialog progressDialog;

    private SpinnerHelper spinnerHelper;

    private ArrayList<KategoriJudul> arrayListKategori = new ArrayList<>();
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();

    private int getKategoriId;
    private String getNipDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_tambah);

        setTitle(getString(R.string.title_judulpa_dosen_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPresenter = new JudulPresenter(this);
        dosenPresenter = new DosenPresenter(this);
        kategoriJudulPresenter = new KategoriJudulPresenter(this);

        judulPresenter.initContext(this);
        dosenPresenter.initContext(this);
        kategoriJudulPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        spinnerHelper = new SpinnerHelper(this);

        final EditText et_judul = findViewById(R.id.act_koor_judul_pa_edittext_judul);
        final EditText et_deskripsi = findViewById(R.id.act_koor_judul_pa_edittext_deskripsi);
        sp_dosen = findViewById(R.id.act_koor_judul_pa_spinner_nama_dosen);
        sp_kategori = findViewById(R.id.act_koor_judul_pa_spinner_kategori);
        Button btn_simpan = findViewById(R.id.act_koor_judul_pa_button_simpan);

        dosenPresenter.getDosen();
        kategoriJudulPresenter.getKategori();

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getNipDosen = arrayListDosen.get(position).getDsn_nip();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        sp_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getKategoriId = arrayListKategori.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String judul = et_judul.getText().toString();
                String deskripsi = et_deskripsi.getText().toString();
                String nipDosen = getNipDosen;
                int kategori = getKategoriId;

                if(judul.isEmpty()){
                    et_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if(deskripsi.isEmpty()){
                    et_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                }else{
                    judulPresenter.createJudul(judul, kategori, deskripsi, nipDosen, JUDUL_STATUS_TERSEDIA);
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
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategori.clear();
        arrayListKategori.addAll(kategori);
        spinnerHelper.initSpinnerKategoriJudul(arrayListKategori, sp_kategori);
    }

    @Override
    public void isEmptyListKategori() {

    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        spinnerHelper.initSpinnerDosen(arrayListDosen, sp_dosen);
        sp_dosen.setSelection(getIntent().getIntExtra(EXTRA_POSITION_SPINNER, 0));
    }

    @Override
    public void isEmptyListDosen() {

    }

    @Override
    public void onSuccesWorkJudul() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
