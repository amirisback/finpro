package org.d3ifcool.finpro.dosen.activities.editor.update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenJudulPaSubdosenUbahActivity extends AppCompatActivity implements JudulWorkView, KategoriJudulListView {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Judul extradata;
    private Spinner spinner_kategori;
    private EditText et_judul, et_deskripsi;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private SpinnerHelper spinnerHelper;
    private Button btn_update;
    private JudulPresenter judulPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();
    private int getIdKategori, spinnerPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_ubah);

        setTitle(getString(R.string.title_judulpa_dosen_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        judulPresenter = new JudulPresenter(this);
        kategoriJudulPresenter.initContext(this);
        judulPresenter.initContext(this);

        spinnerHelper = new SpinnerHelper(this);

        et_judul = findViewById(R.id.act_dsn_judul_pa_edittext_judul);
        et_deskripsi = findViewById(R.id.act_dsn_judul_pa_edittext_deskripsi);
        spinner_kategori = findViewById(R.id.act_dsn_judul_pa_edittext_kategori);
        btn_update = findViewById(R.id.act_dsn_judul_pa_button_simpan);

        extradata = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String extra_judul = extradata.getJudul();
        String extra_deskripsi = extradata.getDeskripsi();
        final int id = extradata.getId();
        String extra_kategori_id = extradata.getKategori_id();
        spinnerPosition = Integer.parseInt(extra_kategori_id);

        et_judul.setText(extra_judul);
        et_deskripsi.setText(extra_deskripsi);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

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

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog
                        .Builder(DosenJudulPaSubdosenUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String judul = et_judul.getText().toString();
                                int kategori = getIdKategori;
                                String deskripsi = et_deskripsi.getText().toString();
                                if(judul.isEmpty()){
                                    et_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else if(deskripsi.isEmpty()){
                                    et_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else{
                                    judulPresenter.updateJudul(id, judul, kategori, deskripsi);
                                }
                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

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
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategoriJudul.clear();
        arrayListKategoriJudul.addAll(kategori);
        spinnerHelper.initSpinnerKategoriJudul(arrayListKategoriJudul, spinner_kategori);
        for (int i = 0; i < arrayListKategoriJudul.size(); i++){
            if (arrayListKategoriJudul.get(i).getId() == spinnerPosition ){
                spinner_kategori.setSelection(i);
            }
        }
    }

    @Override
    public void isEmptyListKategori() {

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