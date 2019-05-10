package org.d3ifcool.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.lists.BimbinganListView;
import org.d3ifcool.service.interfaces.lists.DetailMonevListView;
import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.objects.DosenPembimbingView;
import org.d3ifcool.service.interfaces.objects.DosenReviewerView;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.BimbinganPresenter;
import org.d3ifcool.service.presenters.DetailMonevPresenter;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;

public class DosenProyekAkhirActivity extends AppCompatActivity implements ProyekAkhirListView, BimbinganListView, DosenPembimbingView, DosenReviewerView, DetailMonevListView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private DosenPresenter dosenPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private DetailMonevPresenter detailMonevPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<DetailMonev> arrayListDetailMonev = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private ProyekAkhir parcelProyekAkhir;

    private TextView tv_judul_pa,tv_kelompok_pa,tv_nama_anggota1_pa, tv_nama_anggota2_pa,
            tv_nim_anggota1_pa, tv_nim_anggota2_pa, tv_dosen_pembimbing_pa, tv_jumlah_bimbingan_pa,
            tv_dosen_reviewer_pa, tv_jumlah_monev_pa, tv_status_sidang_pa;

    private String stringJudulId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_proyek_akhir);

        setTitle(R.string.title_proyekakhir);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        detailMonevPresenter = new DetailMonevPresenter(this);
        dosenPresenter = new DosenPresenter(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(org.d3ifcool.mahasiswa.R.string.text_progress_dialog));

        tv_judul_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_judulpa);
        tv_kelompok_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_kelompok);
        tv_nama_anggota1_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_nama_1);
        tv_nim_anggota1_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_nim_1);
        tv_nama_anggota2_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_nama_2);
        tv_nim_anggota2_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_nim_2);
        tv_dosen_pembimbing_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_dosen_pembimbing);
        tv_jumlah_bimbingan_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_jml_bimbingan);
        tv_dosen_reviewer_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_dosen_reviewer);
        tv_status_sidang_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_status_sidang);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        stringJudulId = String.valueOf(extraJudul.getId());
        proyekAkhirPresenter.searchAllProyekAkhirByTwo(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);



        CardView cardView = findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewMonev = findViewById(R.id.frg_mhs_pa_cardview_monev);
        CardView cardViewSidang = findViewById(R.id.frg_mhs_pa_cardview_sidang);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenProyekAkhirActivity.this, DosenProyekAkhirBimbinganActivity.class);
                ArrayList<ProyekAkhir> extraArrayProyekAkhir = arrayListProyekAkhir;
                intent.putParcelableArrayListExtra(DosenProyekAkhirBimbinganActivity.EXTRA_PROYEK_AKHIR, extraArrayProyekAkhir);
                startActivity(intent);
            }
        });

        cardViewMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenProyekAkhirActivity.this, DosenProyekAkhirMonevActivity.class);
                intent.putExtra(DosenProyekAkhirMonevActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                startActivity(intent);
            }
        });
        cardViewSidang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DosenProyekAkhirActivity.this, DosenSidangActivity.class);
                intent.putExtra(DosenSidangActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                startActivity(intent);
            }
        });


        bimbinganPresenter.getBimbingan();
        detailMonevPresenter.getDetailMonev();
        if (arrayListBimbingan.size() > 16 && arrayListDetailMonev.size() < 6){
            tv_status_sidang_pa.setText(getString(R.string.text_siap_sidang));
            tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextGreen));

        }else {
            tv_status_sidang_pa.setText(getString(R.string.text_belum_siap_sidang));
            tv_status_sidang_pa.setTextColor(getResources().getColor(R.color.colorTextRed));

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        proyekAkhirPresenter.searchAllProyekAkhirByTwo(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == android.R.id.home){
            finish();
        }else if (i == R.id.toolbar_menu_ubah){

        }else if (i == R.id.toolbar_menu_hapus){

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
    public void onGetListDetailMonev(List<DetailMonev> detailMonevList) {
        arrayListDetailMonev.clear();
        arrayListDetailMonev.addAll(detailMonevList);
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
    public void onGetObjectDosenPembimbing(Dosen dosen) {

        if (dosen != null) {
            tv_dosen_pembimbing_pa.setText(dosen.getDsn_nama());
        } else {
            tv_dosen_pembimbing_pa.setText(getString(org.d3ifcool.mahasiswa.R.string.text_no_dosen_pembimbing));
        }

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

    }


    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        if (!arrayListProyekAkhir.isEmpty()) {

            parcelProyekAkhir = arrayListProyekAkhir.get(0);
            String stringProyekAkhirId = String.valueOf(arrayListProyekAkhir.get(0).getProyek_akhir_id());

            tv_judul_pa.setText(arrayListProyekAkhir.get(0).getJudul_nama());
            tv_kelompok_pa.setText(arrayListProyekAkhir.get(0).getNama_tim());
            tv_nama_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nama());
            tv_nim_anggota1_pa.setText(arrayListProyekAkhir.get(0).getMhs_nim());

            bimbinganPresenter.searchBimbinganAllBy(BIMBINGAN_PARAM, stringProyekAkhirId);
            dosenPresenter.getDosenPembimbing(arrayListProyekAkhir.get(0).getPembimbing_dsn_nip());

            if (arrayListProyekAkhir.get(0).getReviewer_dsn_nip() != null) {
                dosenPresenter.getDosenReviewer(arrayListProyekAkhir.get(0).getReviewer_dsn_nip());
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
