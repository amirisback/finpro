package org.d3ifcool.finpro.koor.activities.editor.update;

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

import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

public class KoorJudulPaSubdosenUbahActivity extends AppCompatActivity implements JudulWorkView, KategoriJudulListView {

    public static final String EXTRA_JUDUL = "extra_judul";

    private Spinner sp_kategori;
    private JudulPresenter judulPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private ProgressDialog progressDialog;
    private SpinnerHelper spinnerHelper;

    private ArrayList<KategoriJudul> arrayListKategori = new ArrayList<>();

    private int getKategoriId, getJudulId, positionSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_ubah);

        setTitle(getString(R.string.title_judulpa_dosen_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPresenter = new JudulPresenter(this);
        kategoriJudulPresenter = new KategoriJudulPresenter(this);

        judulPresenter.initContext(this);
        kategoriJudulPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        spinnerHelper = new SpinnerHelper(this);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        getJudulId = extraJudul.getId();
        String extraJudulKategoriId = extraJudul.getKategori_id();
        positionSpinner = Integer.parseInt(extraJudulKategoriId);

        final EditText et_judul = findViewById(R.id.act_koor_judul_ubah_edittext_judul);
        final EditText et_deskripsi = findViewById(R.id.act_koor_judul_ubah_edittext_deskripsi);
        sp_kategori = findViewById(R.id.act_koor_judul_ubah_spinner_kategori);
        Button btn_simpan = findViewById(R.id.act_koor_judul_ubah_button_simpan);

        et_judul.setText(extraJudulNama);
        et_deskripsi.setText(extraJudulDeskripsi);

        kategoriJudulPresenter.getKategori();


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

                new AlertDialog
                        .Builder(KoorJudulPaSubdosenUbahActivity.this)
                        .setTitle(getString(R.string.dialog_ubah_title))
                        .setMessage(getString(R.string.dialog_ubah_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String judul = et_judul.getText().toString();
                                String deskripsi = et_deskripsi.getText().toString();
                                int kategori = getKategoriId;

                                if(judul.isEmpty()){
                                    et_judul.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else if(deskripsi.isEmpty()){
                                    et_deskripsi.setError(getString(R.string.text_tidak_boleh_kosong));
                                }else{
                                    judulPresenter.updateJudul(getJudulId, judul, kategori, deskripsi);
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

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategori.clear();
        arrayListKategori.addAll(kategori);
        spinnerHelper.initSpinnerKategoriJudul(arrayListKategori, sp_kategori);
        for (int i = 0; i < arrayListKategori.size(); i++){
            if (arrayListKategori.get(i).getId() == positionSpinner ){
                sp_kategori.setSelection(i);
            }
        }
    }

    @Override
    public void isEmptyListKategori() {

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
    public void onSuccesWorkJudul() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
