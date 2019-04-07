package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.lists.DosenListView;
import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.objects.DosenReviewerView;
import org.d3ifcool.service.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;
import java.util.List;


public class KoorPemetaanMonevDetailActivity extends AppCompatActivity implements ProyekAkhirListView, DosenReviewerView, DosenListView, ProyekAkhirWorkView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private ProgressDialog progressDialog;
    private Spinner sp_dosen;
    private View view_atur;

    private String spinnerItemPosition, spinnerItemNipDosen, extraDsnNip;

    private ArrayList<Dosen> arrayListDosen = new ArrayList<>();
    private ArrayList<Dosen> tempArrayListDosen = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa,tv_nama_anggota1_pa, tv_nama_anggota2_pa,
            tv_dosen_reviewer_pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_pemetaan_monev_detail);

        setTitle(getString(R.string.title_pemetaan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this, this);
        dosenPresenter = new DosenPresenter(this, this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.mahasiswa.R.string.text_progress_dialog));

        tv_judul_pa = findViewById(R.id.ctn_koor_pemetaan_judul);
        tv_kelompok_pa = findViewById(R.id.ctn_koor_pemetaan_kelompok);
        tv_nama_anggota1_pa = findViewById(R.id.ctn_koor_pemetaan_anggota1);
        tv_nama_anggota2_pa = findViewById(R.id.ctn_koor_pemetaan_anggota2);
        tv_dosen_reviewer_pa = findViewById(R.id.ctn_koor_pemetaan_reviewer);
        sp_dosen = findViewById(R.id.sp_dosen);
        view_atur = findViewById(R.id.view_atur);
        Button buttonAtur = findViewById(R.id.ctn_koor_pemetaan_button_atur);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        final String stringJudulId = String.valueOf(extraJudul.getId());
        extraDsnNip = extraJudul.getNip_dosen();

        dosenPresenter.getDosen();
        proyekAkhirPresenter.searchAllProyekAkhirBy(PROYEK_AKHIR_PARAM_1, stringJudulId);

        sp_dosen.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerItemPosition = parent.getItemAtPosition(position).toString();
                spinnerItemNipDosen = arrayListDosen.get(position).getDsn_nip();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonAtur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arrayListProyekAkhir.size() == 2){
                    proyekAkhirPresenter.updateReviewer(arrayListProyekAkhir.get(0).getProyek_akhir_id(), spinnerItemNipDosen);
                    proyekAkhirPresenter.updateReviewerFinish(arrayListProyekAkhir.get(1).getProyek_akhir_id(), spinnerItemNipDosen);
                } else {
                    proyekAkhirPresenter.updateReviewerFinish(arrayListProyekAkhir.get(0).getProyek_akhir_id(), spinnerItemNipDosen);
                }

            }
        });


    }

    private void initSpinner(ArrayList<Dosen> arrayDosen, Spinner spinner) {
        List<String> spinnerItem = new ArrayList<>();
        for (int i = 0; i < arrayDosen.size(); i++) {
            spinnerItem.add(arrayDosen.get(i).getDsn_nama());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, spinnerItem);
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
    public void onSucces() {
        finish();
    }

    @Override
    public void onGetListDosen(List<Dosen> dosen) {
        tempArrayListDosen.clear();
        tempArrayListDosen.addAll(dosen);

        for (int i = 0; i < tempArrayListDosen.size(); i++){
            if (!tempArrayListDosen.get(i).getDsn_nip().equalsIgnoreCase(extraDsnNip)) {
                arrayListDosen.add(tempArrayListDosen.get(i));
            }
        }

        initSpinner(arrayListDosen, sp_dosen);

    }

    @Override
    public void onGetObjectDosenReviewer(Dosen dosen) {

        if (dosen != null) {
            tv_dosen_reviewer_pa.setText(dosen.getDsn_nama());
        } else {
            tv_dosen_reviewer_pa.setText(org.d3ifcool.mahasiswa.R.string.text_no_dosen_monev);
        }


    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        if (!arrayListProyekAkhir.isEmpty()) {

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());
            tv_nama_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nama());

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nip() != null) {
                dosenPresenter.getDosenReviewer(arrayListProyekAkhir.get(0).getReviewer_dsn_nip());
                view_atur.setVisibility(View.GONE);
            } else {
                tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
                view_atur.setVisibility(View.VISIBLE);
            }

            if (arrayListProyekAkhir.size() == 2) {
                tv_nama_anggota2_pa.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nama());
            } else {
                tv_nama_anggota2_pa.setText("");
            }
        }

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

