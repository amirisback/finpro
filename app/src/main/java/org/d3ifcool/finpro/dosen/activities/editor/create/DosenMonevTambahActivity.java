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

import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.MonevListView;
import org.d3ifcool.finpro.core.interfaces.works.MonevDetailWorkView;
import org.d3ifcool.finpro.core.models.Monev;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.MonevDetailPresenter;
import org.d3ifcool.finpro.core.presenters.MonevPresenter;

import java.util.ArrayList;
import java.util.List;



public class DosenMonevTambahActivity extends AppCompatActivity implements MonevDetailWorkView, MonevListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    public static final String EXTRA_STATUS_MONEV = "extra_monev";

    private ProgressDialog progressDialog;
    private MonevDetailPresenter detailMonevPresenter;
    private MonevPresenter monevPresenter;
    private Spinner spinnerMonev;
    private MethodHelper methodHelper;
    private SpinnerHelper spinnerHelper;

    private ArrayList<Monev> arrayListMonev = new ArrayList<>();
    private ArrayList<Monev> arrayListMonevTemp = new ArrayList<>();
    private ArrayList<DetailMonev> arrayListDetailMonev = new ArrayList<>();
    private int getMonevId;
    private Monev monevKategori = new Monev();
    private View enable_view, disable_view;


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
        enable_view = findViewById(R.id.enable_view);
        disable_view = findViewById(R.id.disable_view);

        Button buttonMonev = findViewById(R.id.act_dsn_info_button_simpan);

        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        arrayListDetailMonev = getIntent().getParcelableArrayListExtra(EXTRA_STATUS_MONEV);
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
                int monevId = getMonevId;
                String tanggal = methodHelper.getDateToday();

                if (review.equalsIgnoreCase("")){
                    et_review_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (nilai_st.equalsIgnoreCase("")){
                    et_nilai_monev.setError(getString(R.string.text_tidak_boleh_kosong));
                } else {
                    int nilai = Integer.parseInt(et_nilai_monev.getText().toString());
                    if (nilai>100) {
                        et_nilai_monev.setError("Nilai Tidak Boleh Lebih Dari 100");
                    } else {
                        detailMonevPresenter.createMonevDetail(nilai, tanggal, review, monevId, extraProyekAkhirId);
                    }

                }

            }
        });

    }

    private void checkSumMonev(int jumlahMonev){
        if (jumlahMonev == 0){
            enable_view.setVisibility(View.GONE);
            disable_view.setVisibility(View.VISIBLE);
        } else {
            enable_view.setVisibility(View.VISIBLE);
            disable_view.setVisibility(View.GONE);
            spinnerHelper.initSpinnerMonev(arrayListMonev, spinnerMonev);
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
    public void onGetListMonev(List<Monev> monevList) {
        arrayListMonevTemp.clear();
        arrayListMonev.clear();
        arrayListMonevTemp.addAll(monevList);

        if (arrayListDetailMonev.size() == 0){
            arrayListMonev = arrayListMonevTemp;
        } else {

            for (int i=0; i<arrayListMonevTemp.size(); i++){
                for (int j=0; j<arrayListDetailMonev.size(); j++){
                    if (arrayListMonevTemp.get(i).getMonev_id() == arrayListDetailMonev.get(j).getMonev_id()){
                        int monev_id = arrayListMonevTemp.get(i).getMonev_id();
                        String kategori = arrayListMonevTemp.get(i).getKategori();
                        String jumlahBimbingan = arrayListMonevTemp.get(i).getJumlah_bimbingan();
                        monevKategori.setMonev_id(monev_id);
                        monevKategori.setKategori(kategori);
                        monevKategori.setJumlah_bimbingan(jumlahBimbingan);
                    }
                }

                if (monevKategori != null) {
                    if (arrayListMonevTemp.get(i).getMonev_id() != monevKategori.getMonev_id()){
                        arrayListMonev.add(arrayListMonevTemp.get(i));
                    }
                }
            }
        }

        checkSumMonev(arrayListMonev.size());

    }

    @Override
    public void isEmptyListMonev() {

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
