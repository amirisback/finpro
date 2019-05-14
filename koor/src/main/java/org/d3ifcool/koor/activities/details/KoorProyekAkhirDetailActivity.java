package org.d3ifcool.koor.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.base.helpers.SessionManager;
import org.d3ifcool.base.interfaces.lists.BimbinganListView;
import org.d3ifcool.base.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.base.models.Bimbingan;
import org.d3ifcool.base.models.Judul;
import org.d3ifcool.base.models.ProyekAkhir;
import org.d3ifcool.base.presenters.BimbinganPresenter;
import org.d3ifcool.base.presenters.ProyekAkhirPresenter;
import org.d3ifcool.koor.R;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.base.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.base.helpers.Constant.ObjectConstanta.JUMLAH_BIMBINGAN_SIDANG;

public class KoorProyekAkhirDetailActivity extends AppCompatActivity implements
        ProyekAkhirListView, BimbinganListView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private ProyekAkhirPresenter proyekAkhirPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa,tv_nama_anggota1_pa, tv_nama_anggota2_pa,
            tv_nim_anggota1_pa, tv_nim_anggota2_pa, tv_dosen_pembimbing_pa, tv_jumlah_bimbingan_pa,
            tv_dosen_reviewer_pa, tv_jumlah_monev_pa, tv_status_sidang_pa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_proyek_akhir_detail);

        setTitle(getString(R.string.title_proyekakhir_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.mahasiswa.R.string.text_progress_dialog));

        tv_judul_pa = findViewById(R.id.frg_koor_pa_textview_judulpa);
        tv_kelompok_pa = findViewById(R.id.frg_koor_pa_textview_kelompokpa);
        tv_nama_anggota1_pa = findViewById(R.id.frg_koor_pa_textview_nama_1);
        tv_nim_anggota1_pa = findViewById(R.id.frg_koor_pa_textview_nim_1);
        tv_nama_anggota2_pa = findViewById(R.id.frg_koor_pa_textview_nama_2);
        tv_nim_anggota2_pa = findViewById(R.id.frg_koor_pa_textview_nim_2);
        tv_dosen_pembimbing_pa = findViewById(R.id.frg_koor_pa_textview_dsn_pembimbing);
        tv_jumlah_bimbingan_pa = findViewById(R.id.frg_koor_pa_textview_jml_bimbingan);
        tv_dosen_reviewer_pa = findViewById(R.id.frg_koor_pa_textview_dsn_reviewer);
        tv_status_sidang_pa = findViewById(R.id.frg_koor_pa_textview_dsn_status_sidang);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        final String stringJudulId = String.valueOf(extraJudul.getId());

        if (extraJudul.getDsn_nama() != null) {
            tv_dosen_pembimbing_pa.setText(extraJudul.getDsn_nama());
        } else {
            tv_dosen_pembimbing_pa.setText(getString(R.string.text_no_dosen_pembimbing));
        }

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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);

        if (!arrayListBimbingan.isEmpty()){
            int jumlahBimbingan = arrayListBimbingan.size();
            String stringJumlahBimibingan = String.valueOf(jumlahBimbingan);
            tv_jumlah_bimbingan_pa.setText(stringJumlahBimibingan);

        } else {
            int jumlahBimbingan = 0;
            String stringJumlahBimibingan = String.valueOf(jumlahBimbingan);
            tv_jumlah_bimbingan_pa.setText(stringJumlahBimibingan);
        }

        if (arrayListBimbingan.size() >= JUMLAH_BIMBINGAN_SIDANG){
            tv_status_sidang_pa.setText(getString(R.string.text_siap_sidang));
            tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextGreen));

        } else {
            tv_status_sidang_pa.setText(getString(R.string.text_belum_siap_sidang));
            tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextRed));

        }

    }


    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        if (!arrayListProyekAkhir.isEmpty()) {

            String stringProyekAkhirId = String.valueOf(arrayListProyekAkhir.get(0).getProyek_akhir_id());

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());
            tv_nama_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nama());
            tv_nim_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nim());

            bimbinganPresenter.searchBimbinganAllBy(BIMBINGAN_PARAM, stringProyekAkhirId);

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nama() != null) {
                tv_dosen_reviewer_pa.setText(arrayListProyekAkhir.get(0).getReviewer_dsn_nama());
            } else {
                tv_dosen_reviewer_pa.setText(org.d3ifcool.mahasiswa.R.string.text_no_dosen_monev);
            }

            if (arrayListProyekAkhir.size() == 2) {
                tv_nama_anggota2_pa.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nama());
                tv_nim_anggota2_pa.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nim());
            } else {
                tv_nama_anggota2_pa.setText("");
                tv_nim_anggota2_pa.setText("");
            }
        }

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
