package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.editor.create.DosenSidangTambahActivity;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.lists.MonevDetailListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.lists.SidangListView;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.MonevDetailPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;
import org.d3ifcool.finpro.core.presenters.SidangPresenter;
import org.d3ifcool.finpro.dosen.activities.editor.update.DosenSidangUbahActivity;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_LULUS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_LULUS_BERSYARAT;

public class DosenSidangActivity extends AppCompatActivity implements SidangListView , ProyekAkhirListView, BimbinganListView, MonevDetailListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private static final String PARAM_PROYEK_AKHIR = "proyek_akhir.judul_id";
    private static final String PARAM_BIMBINGAN = "bimbingan.proyek_akhir_id";

    private ArrayList<ProyekAkhir> proyekAkhirArrayList = new ArrayList<>();
    private ArrayList<Bimbingan> bimbinganArrayList = new ArrayList<>();
    private ArrayList<Sidang> sidangArrayList = new ArrayList<>();
    private ArrayList<DetailMonev> detailMonevArrayList = new ArrayList<>();

    private ProgressDialog progressDialog;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private SidangPresenter sidangPresenter;
    private BimbinganPresenter bimbinganPresenter;

    private TextView tv_nama1,tv_nim1,tv_nama2,tv_nim2,tv_status, tv_tanggal_1, tv_tanggal_2;
    private CardView cardView_mhs_1,cardView_mhs_2;
    private TextView tv_nilai_proposal1, tv_nilai_pembimbing1,tv_nilai_penguji1_1,tv_nilai_penguji1_2,tv_nilai_total_1, tv_review_1, tv_status_1, tv_tambah_sidang1, tv_ubah_sidang1;
    private TextView tv_nilai_proposal2, tv_nilai_pembimbing2,tv_nilai_penguji2_1,tv_nilai_penguji2_2,tv_nilai_total_2,tv_review_2, tv_status_2, tv_tambah_sidang2, tv_ubah_sidang2;
    private LinearLayout linearLayout_mhs1, linearLayout_mhs2;
    private String extraJudulId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_sidang);

        setTitle(getString(R.string.title_sidang));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        sidangPresenter = new SidangPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        MonevDetailPresenter detailMonevPresenter = new MonevDetailPresenter(this);

        proyekAkhirPresenter.initContext(this);
        sidangPresenter.initContext(this);
        bimbinganPresenter.initContext(this);
        detailMonevPresenter.initContext(this);

        tv_nama1 = findViewById(R.id.dsn_sidang_textview_nama_1);
        tv_nim1 = findViewById(R.id.dsn_sidang_textview_nim_1);
        tv_nama2 = findViewById(R.id.dsn_sidang_textview_nama_2);
        tv_nim2 = findViewById(R.id.dsn_sidang_textview_nim_2);
        cardView_mhs_1 = findViewById(R.id.card_view_mhs_1);
        cardView_mhs_2 = findViewById(R.id.card_view_mhs_2);
        linearLayout_mhs1 = findViewById(R.id.linear_mhs_1);
        linearLayout_mhs2 = findViewById(R.id.linear_mhs_2);

        tv_nilai_proposal1 = findViewById(R.id.dsn_sidang_nilai_proposal_1);
        tv_nilai_pembimbing1 = findViewById(R.id.dsn_sidang_nilai_pembimbing1_1);
        tv_nilai_penguji1_1 = findViewById(R.id.dsn_sidang_nilai_penguji1_1);
        tv_nilai_penguji1_2 = findViewById(R.id.dsn_sidang_nilai_penguji2_1);
        tv_nilai_total_1 = findViewById(R.id.dsn_sidang_nilai_total_1);
        tv_review_1 = findViewById(R.id.dsn_sidang_textview_review_1);
        tv_status_1 = findViewById(R.id.dsn_sidang_textview_status_1);
        tv_tanggal_1 = findViewById(R.id.tanggal_sidang1);

        tv_nilai_proposal2 = findViewById(R.id.dsn_sidang_nilai_proposal_2);
        tv_nilai_pembimbing2 = findViewById(R.id.dsn_sidang_nilai_pembimbing1_2);
        tv_nilai_penguji2_1 = findViewById(R.id.dsn_sidang_nilai_penguji1_2);
        tv_nilai_penguji2_2 = findViewById(R.id.dsn_sidang_nilai_penguji2_2);
        tv_nilai_total_2 = findViewById(R.id.dsn_sidang_nilai_total_2);
        tv_review_2 = findViewById(R.id.dsn_sidang_textview_review_2);
        tv_status_2 = findViewById(R.id.dsn_sidang_textview_status_2);
        tv_tambah_sidang1 = findViewById(R.id.act_dsn_textview_tambah_sidang_mhs_1);
        tv_tambah_sidang2 = findViewById(R.id.act_dsn_textview_tambah_sidang_mhs_2);
        tv_ubah_sidang1 = findViewById(R.id.act_dsn_textview_ubah_sidang_mhs_1);
        tv_ubah_sidang2 = findViewById(R.id.act_dsn_textview_ubah_sidang_mhs_2);
        tv_tanggal_2 = findViewById(R.id.tanggal_sidang2);

        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        extraJudulId = String.valueOf(extraProyekAkhir.getJudul_id());

        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, extraJudulId);
        detailMonevPresenter.searchMonevDetailAllBy(PARAM_PROYEK_AKHIR, extraJudulId);

        tv_tambah_sidang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(0);
                intentSidangAdd(parcelProyekAkhir);
            }
        });

        tv_tambah_sidang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(1);
                intentSidangAdd(parcelProyekAkhir);
            }
        });

        tv_ubah_sidang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sidang parcelSidang = sidangArrayList.get(0);
                ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(0);
                intentSidangUpdate(parcelProyekAkhir, parcelSidang);
            }
        });

        tv_ubah_sidang2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sidang parcelSidang = sidangArrayList.get(1);
                ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(1);
                intentSidangUpdate(parcelProyekAkhir, parcelSidang);
            }
        });


    }

    private void intentSidangUpdate(ProyekAkhir parcelProyekAkhir, Sidang sidang){
        Intent intentSidangUpdate = new Intent(DosenSidangActivity.this, DosenSidangUbahActivity.class);
        intentSidangUpdate.putExtra(DosenSidangUbahActivity.EXTRA_SIDANG, sidang);
        intentSidangUpdate.putExtra(DosenSidangUbahActivity.EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
        startActivity(intentSidangUpdate);
    }


    private void intentSidangAdd(ProyekAkhir parcel){
        int jumlahBimbingan = bimbinganArrayList.size();
        int jumlahMonev = detailMonevArrayList.size() / 2;

//        if (jumlahBimbingan >= JUMLAH_BIMBINGAN_SIDANG && jumlahMonev >= JUMLAH_MONEV_SIDANG){
        if (jumlahBimbingan >= 1){
            Intent intent = new Intent(DosenSidangActivity.this, DosenSidangTambahActivity.class);
            intent.putExtra(EXTRA_PROYEK_AKHIR, parcel);
            startActivity(intent);
        } else {
            Toast.makeText(DosenSidangActivity.this, getString(R.string.text_belum_memenuhi_syarat_sidang), Toast.LENGTH_SHORT).show();
        }
    }

    private void setSidangMahasiswa1(Sidang sidang){
        tv_nilai_proposal1.setText(String.valueOf(sidang.getNilai_proposal()));
        tv_nilai_pembimbing1.setText(String.valueOf(sidang.getNilai_pembimbing()));
        tv_nilai_penguji1_1.setText(String.valueOf(sidang.getNilai_penguji_1()));
        tv_nilai_penguji1_2.setText(String.valueOf(sidang.getNilai_penguji_2()));
        tv_nilai_total_1.setText(String.valueOf(sidang.getNilai_total()));
        tv_review_1.setText(sidang.getSidang_review());
        tv_tanggal_1.setText(sidang.getSidang_tanggal());
        tv_status_1.setText(sidang.getSidang_status());

        if (sidang.getSidang_status().equals(STATUS_SIDANG_LULUS) || sidang.getSidang_status().equals(STATUS_SIDANG_LULUS_BERSYARAT)){
            tv_status_1.setTextColor(DosenSidangActivity.this.getResources().getColor(R.color.colorTextGreen));
        } else {
            tv_status_1.setTextColor(DosenSidangActivity.this.getResources().getColor(R.color.colorTextRed));
        }

        linearLayout_mhs1.setVisibility(View.VISIBLE);
        tv_ubah_sidang1.setVisibility(View.VISIBLE);
        tv_tambah_sidang1.setVisibility(View.GONE);
    }

    private void setSidangMahasiswa2(Sidang sidang){
        tv_nilai_proposal2.setText(String.valueOf(sidang.getNilai_proposal()));
        tv_nilai_pembimbing2.setText(String.valueOf(sidang.getNilai_pembimbing()));
        tv_nilai_penguji2_1.setText(String.valueOf(sidang.getNilai_penguji_1()));
        tv_nilai_penguji2_2.setText(String.valueOf(sidang.getNilai_penguji_2()));
        tv_nilai_total_2.setText(String.valueOf(sidang.getNilai_total()));
        tv_review_2.setText(sidang.getSidang_review());
        tv_tanggal_2.setText(sidang.getSidang_tanggal());
        tv_status_2.setText(sidang.getSidang_status());

        if (sidang.getSidang_status().equals(STATUS_SIDANG_LULUS) || sidang.getSidang_status().equals(STATUS_SIDANG_LULUS_BERSYARAT)){
            tv_status_2.setTextColor(DosenSidangActivity.this.getResources().getColor(R.color.colorTextGreen));
        } else {
            tv_status_2.setTextColor(DosenSidangActivity.this.getResources().getColor(R.color.colorTextRed));
        }

        linearLayout_mhs2.setVisibility(View.VISIBLE);
        tv_ubah_sidang2.setVisibility(View.VISIBLE);
        tv_tambah_sidang2.setVisibility(View.GONE);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (extraJudulId != null){
            proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, extraJudulId);
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
    public void onGetListDetailMonev(List<DetailMonev> detailMonevList) {
        detailMonevArrayList.clear();
        detailMonevArrayList.addAll(detailMonevList);
    }

    @Override
    public void isEmptyListMonevDetail() {

    }

    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        bimbinganArrayList.clear();
        bimbinganArrayList.addAll(bimbinganList);
    }

    @Override
    public void isEmptyListBimbingan() {

    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        proyekAkhirArrayList.clear();
        proyekAkhirArrayList.addAll(proyekAkhirList);

        String stringProyekAkhirId = String.valueOf(proyekAkhirArrayList.get(0).getProyek_akhir_id());
        bimbinganPresenter.searchBimbinganAllBy(PARAM_BIMBINGAN, stringProyekAkhirId);
        sidangPresenter.searchAllSidangBy(PARAM_PROYEK_AKHIR, extraJudulId);

        if (proyekAkhirArrayList.size() == 2) {
            tv_nama1.setText(proyekAkhirArrayList.get(0).getMhs_nama());
            tv_nama2.setText(proyekAkhirArrayList.get(1).getMhs_nama());
            tv_nim1.setText(proyekAkhirArrayList.get(0).getMhs_nim());
            tv_nim2.setText(proyekAkhirArrayList.get(1).getMhs_nim());
        } else {
            tv_nama1.setText(proyekAkhirArrayList.get(0).getMhs_nama());
            tv_nim1.setText(proyekAkhirArrayList.get(0).getMhs_nim());
            cardView_mhs_2.setVisibility(View.GONE);
        }
    }

    @Override
    public void isEmptyListProyekAkhir() {

    }


    @Override
    public void onGetListSidang(List<Sidang> sidangList) {
        sidangArrayList.clear();
        sidangArrayList.addAll(sidangList);

        if (sidangArrayList.size() == 0){
            linearLayout_mhs1.setVisibility(View.GONE);
            tv_tambah_sidang1.setVisibility(View.VISIBLE);
            linearLayout_mhs2.setVisibility(View.GONE);
            tv_tambah_sidang2.setVisibility(View.VISIBLE);
        } else {
            if (sidangArrayList.size() == 2) {
                setSidangMahasiswa1(sidangArrayList.get(0));
                setSidangMahasiswa2(sidangArrayList.get(1));
            } else {
                int sidangProyekAkhirId = sidangArrayList.get(0).getProyek_akhir_id();
                int proyekAkhirId = proyekAkhirArrayList.get(0).getProyek_akhir_id();
                if (proyekAkhirId != 0 && sidangProyekAkhirId != 0) {
                    if (sidangProyekAkhirId == proyekAkhirId){
                        setSidangMahasiswa1(sidangArrayList.get(0));
                        linearLayout_mhs2.setVisibility(View.GONE);
                        tv_tambah_sidang2.setVisibility(View.VISIBLE);
                    } else {
                        setSidangMahasiswa2(sidangArrayList.get(0));
                        linearLayout_mhs1.setVisibility(View.GONE);
                        tv_tambah_sidang1.setVisibility(View.VISIBLE);
                    }
                }
            }
        }

    }

    @Override
    public void isEmptyListSidang() {

    }


    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
