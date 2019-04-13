package org.d3ifcool.superuser.activities.editors;

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

import org.d3ifcool.service.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;
import java.util.List;

public class KoorJudulPaSubdosenUbahActivity extends AppCompatActivity implements JudulWorkView, KategoriJudulListView {

    public static final String EXTRA_JUDUL = "extra_judul";

    private Spinner sp_kategori;
    private JudulPresenter judulPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<KategoriJudul> arrayListKategori = new ArrayList<>();

    private int getKategoriId;
    private int getJudulId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_ubah);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_judulpa_dosen_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPresenter = new JudulPresenter(this);
        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        getJudulId = extraJudul.getId();

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
                                    et_judul.setError("judul tidak boleh kosong");
                                }else if(deskripsi.isEmpty()){
                                    et_deskripsi.setError("deskripsi tidak boleh kosong");
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

    private void initSpinnerKategori(ArrayList<KategoriJudul> arrayList, Spinner spinner) {
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
        initSpinnerKategori(arrayListKategori, sp_kategori);
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
