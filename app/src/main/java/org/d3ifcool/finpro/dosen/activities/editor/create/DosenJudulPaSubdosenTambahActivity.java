package org.d3ifcool.finpro.dosen.activities.editor.create;

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

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERSEDIA;

public class DosenJudulPaSubdosenTambahActivity extends AppCompatActivity implements JudulWorkView, KategoriJudulListView {

    private Spinner spinner_kategori;
    private EditText et_judul, et_deskripsi;
    private Button btn_simpan;
    private SpinnerHelper spinnerHelper;
    private JudulPresenter judulPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private SessionManager sessionManager;
    private ProgressDialog dialog;
    private int getIdKategori;

    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_tambah);

        et_judul = findViewById(R.id.act_dsn_judul_pa_edittext_judul);
        et_deskripsi = findViewById(R.id.act_dsn_judul_pa_edittext_deskripsi);
        spinner_kategori = findViewById(R.id.act_dsn_judul_pa_edittext_kategori);
        btn_simpan = findViewById(R.id.act_dsn_judul_pa_button_simpan);

        spinnerHelper = new SpinnerHelper(this);

        setTitle(R.string.title_judulpa_dosen_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPresenter = new JudulPresenter(this);
        kategoriJudulPresenter = new KategoriJudulPresenter(this);

        judulPresenter.initContext(this);
        kategoriJudulPresenter.initContext(this);

        sessionManager = new SessionManager(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        kategoriJudulPresenter.getKategori();

        spinner_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getIdKategori = arrayListKategoriJudul.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String judul = et_judul.getText().toString();
                int kategori = getIdKategori;
                String deskripsi = et_deskripsi.getText().toString();
                if(judul.isEmpty()){
                    et_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if(deskripsi.isEmpty()){
                    et_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                }else{
                    judulPresenter.createJudul(judul, kategori, deskripsi, sessionManager.getSessionDosenNip(), JUDUL_STATUS_TERSEDIA);
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
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
    }

    @Override
    public void onSuccesWorkJudul() {
        finish();
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
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
