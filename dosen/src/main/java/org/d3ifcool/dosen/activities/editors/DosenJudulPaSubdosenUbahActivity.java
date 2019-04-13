package org.d3ifcool.dosen.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenJudulPaSubdosenUbahActivity extends AppCompatActivity implements JudulWorkView, KategoriJudulListView {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Judul extradata;
    private Spinner spinner_kategori;
    private EditText et_judul, et_deskripsi;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private Button btn_update;
    private JudulPresenter presenter;
    private ProgressDialog progressDialog;

    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();
    private int getIdKategori;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_ubah);

        setTitle(getString(R.string.title_judulpa_dosen_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        presenter = new JudulPresenter(this);

        et_judul = findViewById(R.id.act_dsn_judul_pa_edittext_judul);
        et_deskripsi = findViewById(R.id.act_dsn_judul_pa_edittext_deskripsi);
        spinner_kategori = findViewById(R.id.act_dsn_judul_pa_edittext_kategori);
        btn_update = findViewById(R.id.act_dsn_judul_pa_button_simpan);

        extradata = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String extra_judul = extradata.getJudul();
        String extra_deskripsi = extradata.getDeskripsi();
        final int id = extradata.getId();

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
                                    et_judul.setError("judul tidak boleh kosong");
                                }else if(deskripsi.isEmpty()){
                                    et_deskripsi.setError("deskripsi tidak boleh kosong");
                                }else{
                                    presenter.updateJudul(id, judul, kategori, deskripsi);
                                }
                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

    }

    private void initSpinner(ArrayList<KategoriJudul> arrayList, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            spinnerItem.add(arrayList.get(i).getKategori_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, org.d3ifcool.mahasiswa.R.layout.support_simple_spinner_dropdown_item, spinnerItem);
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
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategoriJudul.clear();
        arrayListKategoriJudul.addAll(kategori);
        initSpinner(arrayListKategoriJudul, spinner_kategori);
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