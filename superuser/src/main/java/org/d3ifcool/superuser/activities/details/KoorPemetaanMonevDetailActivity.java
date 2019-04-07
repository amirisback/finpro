package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.objects.DosenReviewerView;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;
import org.d3ifcool.superuser.R;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;

public class KoorPemetaanMonevDetailActivity extends AppCompatActivity implements ProyekAkhirListView, DosenReviewerView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa,tv_nama_anggota1_pa, tv_nama_anggota2_pa,
            tv_dosen_reviewer_pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_pemetaan_monev_detail);

        setTitle(getString(R.string.title_pemetaan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        dosenPresenter = new DosenPresenter(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.mahasiswa.R.string.text_progress_dialog));

        tv_judul_pa = findViewById(R.id.ctn_koor_pemetaan_judul);
        tv_kelompok_pa = findViewById(R.id.ctn_koor_pemetaan_kelompok);
        tv_nama_anggota1_pa = findViewById(R.id.ctn_koor_pemetaan_anggota1);
        tv_nama_anggota2_pa = findViewById(R.id.ctn_koor_pemetaan_anggota2);
        tv_dosen_reviewer_pa = findViewById(R.id.ctn_koor_pemetaan_reviewer);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        final String stringJudulId = String.valueOf(extraJudul.getId());

        proyekAkhirPresenter.searchAllProyekAkhirByTwo(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);
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
            } else {
                tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
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

