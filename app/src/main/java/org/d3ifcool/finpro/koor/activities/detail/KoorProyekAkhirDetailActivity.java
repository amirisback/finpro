package org.d3ifcool.finpro.koor.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.adapters.AnggotaViewAdapter;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.helpers.ViewAdapterHelper;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_ARSIP;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUMLAH_BIMBINGAN_SIDANG;

public class KoorProyekAkhirDetailActivity extends AppCompatActivity implements
        ProyekAkhirListView, BimbinganListView, JudulWorkView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private ProyekAkhirPresenter proyekAkhirPresenter;
    private JudulPresenter judulPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();

    private TextView tv_judul_pa,tv_kelompok_pa, tv_dosen_pembimbing_pa, tv_jumlah_bimbingan_pa,
            tv_dosen_reviewer_pa, tv_jumlah_monev_pa, tv_status_sidang_pa;

    private Button btn_arsip;

    private ViewAdapterHelper viewAdapterHelper;
    private AnggotaViewAdapter anggotaViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_proyek_akhir_detail);

        setTitle(getString(R.string.title_proyekakhir_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        judulPresenter = new JudulPresenter(this);

        proyekAkhirPresenter.initContext(this);
        bimbinganPresenter.initContext(this);
        judulPresenter.initContext(this);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        btn_arsip = findViewById(R.id.btn_judul_arsip);
        tv_judul_pa = findViewById(R.id.ctn_all_pa_textview_judul);
        tv_kelompok_pa = findViewById(R.id.ctn_all_pa_textview_kelompok);
        tv_dosen_pembimbing_pa = findViewById(R.id.frg_koor_pa_textview_dsn_pembimbing);
        tv_jumlah_bimbingan_pa = findViewById(R.id.frg_koor_pa_textview_jml_bimbingan);
        tv_dosen_reviewer_pa = findViewById(R.id.frg_koor_pa_textview_dsn_reviewer);
        tv_status_sidang_pa = findViewById(R.id.frg_koor_pa_textview_dsn_status_sidang);

        RecyclerView recyclerView = findViewById(R.id.all_recyclerview_anggota);

        anggotaViewAdapter = new AnggotaViewAdapter(this);
        viewAdapterHelper = new ViewAdapterHelper(this);
        viewAdapterHelper.setRecyclerView(recyclerView);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        String stringJudulId = String.valueOf(extraJudul.getId());
        final int judulId = extraJudul.getId();

        if (extraJudul.getDsn_nama() != null) {
            tv_dosen_pembimbing_pa.setText(extraJudul.getDsn_nama());
        } else {
            tv_dosen_pembimbing_pa.setText(getString(R.string.text_no_dosen_pembimbing));
        }

        proyekAkhirPresenter.searchAllProyekAkhirByTwo(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);

        btn_arsip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(KoorProyekAkhirDetailActivity.this)
                        .setTitle(getString(R.string.dialog_arsip_title))
                        .setMessage(getString(R.string.dialog_arsip_text))
                        .setPositiveButton("Arsip", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                judulPresenter.updateStatusJudul(judulId, JUDUL_STATUS_ARSIP);
                            }
                        })

                        .setNegativeButton("Batal", null)
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

        if (arrayListProyekAkhir.get(0).getNilai_total() == 0){
            if (arrayListBimbingan.size() >= JUMLAH_BIMBINGAN_SIDANG){
                tv_status_sidang_pa.setText(getString(R.string.text_siap_sidang));
                tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorBackgroundYellow));

            }else {
                tv_status_sidang_pa.setText(getString(R.string.text_belum_siap_sidang));
                tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextRed));

            }
        } else {
            tv_status_sidang_pa.setText(getString(R.string.text_sudah_sidang));
            tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextGreen));
        }


    }

    @Override
    public void isEmptyListBimbingan() {

    }


    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        if (!arrayListProyekAkhir.isEmpty()) {

            String stringProyekAkhirId = String.valueOf(arrayListProyekAkhir.get(0).getProyek_akhir_id());

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());

            bimbinganPresenter.searchBimbinganAllBy(BIMBINGAN_PARAM, stringProyekAkhirId);

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nama() != null) {
                tv_dosen_reviewer_pa.setText(arrayListProyekAkhir.get(0).getReviewer_dsn_nama());
            } else {
                tv_dosen_reviewer_pa.setText(R.string.text_no_dosen_monev);
            }

            anggotaViewAdapter.addItem(arrayListProyekAkhir);
            viewAdapterHelper.setAdapterAnggota(anggotaViewAdapter);
        }

    }

    @Override
    public void isEmptyListProyekAkhir() {

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
