package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.KategoriJudul;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.service.presenters.KategoriJudulPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_TERSEDIA;

public class KoorJudulPaSubdosenTambahActivity extends AppCompatActivity implements JudulWorkView, DosenListView, KategoriJudulListView {

    private Spinner sp_dosen, sp_kategori;
    private JudulPresenter judulPresenter;
    private DosenPresenter dosenPresenter;
    private KategoriJudulPresenter kategoriJudulPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<KategoriJudul> arrayListKategori = new ArrayList<>();
    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();

    private int getKategoriId;
    private String getNipDosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_tambah);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_judulpa_dosen_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPresenter = new JudulPresenter(this);
        dosenPresenter = new DosenPresenter(this);
        kategoriJudulPresenter = new KategoriJudulPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

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
                    et_judul.setError("judul tidak boleh kosong");
                }else if(deskripsi.isEmpty()){
                    et_deskripsi.setError("deskripsi tidak boleh kosong");
                }else{
                    judulPresenter.createJudul(judul, kategori, deskripsi, nipDosen, JUDUL_STATUS_TERSEDIA);
                }
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

    private void initSpinnerDosen(ArrayList<Dosen> arrayList, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayList.size(); i++) {
            spinnerItem.add(arrayList.get(i).getDsn_nama());
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
        initSpinnerKategori(arrayListKategori, sp_kategori);
    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        arrayListDosen.clear();
        arrayListDosen.addAll(dosen);
        initSpinnerDosen(arrayListDosen, sp_dosen);
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
