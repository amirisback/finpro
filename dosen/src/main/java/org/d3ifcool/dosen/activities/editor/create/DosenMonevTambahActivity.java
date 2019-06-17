package org.d3ifcool.dosen.activities.editor.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.base.helpers.MethodHelper;
import org.d3ifcool.base.helpers.SpinnerHelper;
import org.d3ifcool.base.interfaces.lists.MonevListView;
import org.d3ifcool.base.interfaces.works.MonevDetailWorkView;
import org.d3ifcool.base.models.Monev;
import org.d3ifcool.base.models.ProyekAkhir;
import org.d3ifcool.base.presenters.MonevDetailPresenter;
import org.d3ifcool.base.presenters.MonevPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenMonevTambahActivity extends AppCompatActivity implements MonevDetailWorkView, MonevListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private ProgressDialog progressDialog;
    private MonevDetailPresenter detailMonevPresenter;
    private MonevPresenter monevPresenter;
    private Spinner spinnerMonev;
    private MethodHelper methodHelper;
    private SpinnerHelper spinnerHelper;

    private ArrayList<Monev> arrayListMonev = new ArrayList<>();
    private int getMonevId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_tambah);

        setTitle(getString(R.string.title_monev_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        monevPresenter = new MonevPresenter(this);
        methodHelper = new MethodHelper();
        detailMonevPresenter = new MonevDetailPresenter(this);
        spinnerHelper = new SpinnerHelper(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        monevPresenter.initContext(this);
        detailMonevPresenter.initContext(this);

        final EditText et_review_monev = findViewById(R.id.act_dsn_edittext_deskripsi);
        final EditText et_nilai_monev = findViewById(R.id.act_dsn_edittext_nilai_monev);
        spinnerMonev = findViewById(R.id.act_dsn_spinner_kategori_monev);
        Button buttonMonev = findViewById(R.id.act_dsn_info_button_simpan);

        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        final int extraProyekAkhirId = extraProyekAkhir.getProyek_akhir_id();

        monevPresenter.getMonev();

        spinnerMonev.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 getMonevId = arrayListMonev.get(position).getMonev_id();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String review = et_review_monev.getText().toString();
                String nilai_st = et_nilai_monev.getText().toString();
                int nilai = Integer.parseInt(nilai_st);
                int monevId = getMonevId;
                String tanggal = methodHelper.getDateToday();

                if (review.isEmpty()){
                    et_review_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (nilai_st.isEmpty()){
                    et_nilai_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (nilai >100){
                    et_nilai_monev.setError("Nilai Tidak Boleh Lebih Dari 100");
                } else {
                    detailMonevPresenter.createMonevDetail(nilai, tanggal, review, monevId, extraProyekAkhirId);
                    Log.d("review", review);
                    Log.d("nilai", String.valueOf(nilai));
                    Log.d("monevId", String.valueOf(monevId));
                    Log.d("tanggal", tanggal);
                    Log.d("extraProyekAkhirId", String.valueOf(extraProyekAkhirId));
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
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onGetListMonev(List<Monev> monevList) {
        arrayListMonev.clear();
        arrayListMonev.addAll(monevList);
        spinnerHelper.initSpinnerMonev(arrayListMonev, spinnerMonev);

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
