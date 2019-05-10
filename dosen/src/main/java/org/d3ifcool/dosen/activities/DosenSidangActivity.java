package org.d3ifcool.dosen.activities;

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

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editor.create.DosenSidangTambahActivity;
import org.d3ifcool.service.interfaces.lists.BimbinganListView;
import org.d3ifcool.service.interfaces.lists.DetailMonevListView;
import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.lists.SidangListView;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.models.Sidang;
import org.d3ifcool.service.presenters.BimbinganPresenter;
import org.d3ifcool.service.presenters.DetailMonevPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;
import org.d3ifcool.service.presenters.SidangPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenSidangActivity extends AppCompatActivity implements SidangListView , ProyekAkhirListView, BimbinganListView, DetailMonevListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private static final String PARAM_PROYEK_AKHIR = "proyek_akhir.judul_id";
    private static final String PARAM_BIMBINGAN = "bimbingan.proyek_akhir_id";
    private static final String PARAM_BIMBINGAN2 = "bimbingan.judul_id";

    private ArrayList<ProyekAkhir> proyekAkhirArrayList = new ArrayList<>();
    private ArrayList<Bimbingan> bimbinganArrayList = new ArrayList<>();
    private ArrayList<Sidang> sidangArrayList = new ArrayList<>();
    private ArrayList<DetailMonev> detailMonevArrayList = new ArrayList<>();

    private ProgressDialog progressDialog;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private SidangPresenter sidangPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private DetailMonevPresenter detailMonevPresenter;

    private TextView tv_nama1,tv_nim1,tv_nama2,tv_nim2,tv_status;
    private CardView cardView_mhs_1,cardView_mhs_2;
    private TextView tv_nilai_proposal1, tv_nilai_pembimbing,tv_nilai_penguji1_1,tv_nilai_penguji1_2,tv_nilai_total_1, tv_review_1, tv_status_1;
    private TextView tv_nilai_proposal2, tv_nilai_pembimbing2,tv_nilai_penguji2_1,tv_nilai_penguji2_2,tv_nilai_total_2,tv_review_2, tv_status_2;
    private LinearLayout linearLayout_mhs1, linearLayout_mhs2;
    String extraJudulId;

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
        detailMonevPresenter = new DetailMonevPresenter(this);
        sidangPresenter.getSidang();


        tv_nama1 = findViewById(R.id.dsn_sidang_textview_nama_1);
        tv_nim1 = findViewById(R.id.dsn_sidang_textview_nim_1);
        tv_nama2 = findViewById(R.id.dsn_sidang_textview_nama_2);
        tv_nim2 = findViewById(R.id.dsn_sidang_textview_nim_2);
        cardView_mhs_1 = findViewById(R.id.card_view_mhs_1);
        cardView_mhs_2 = findViewById(R.id.card_view_mhs_2);
        linearLayout_mhs1 = findViewById(R.id.linear_mhs_1);
        linearLayout_mhs2 = findViewById(R.id.linear_mhs_2);

        tv_nilai_proposal1 = findViewById(R.id.dsn_sidang_nilai_proposal_1);
        tv_nilai_pembimbing = findViewById(R.id.dsn_sidang_nilai_pembimbing1_1);
        tv_nilai_penguji1_1 = findViewById(R.id.dsn_sidang_nilai_penguji1_1);
        tv_nilai_penguji1_2 = findViewById(R.id.dsn_sidang_nilai_penguji1_2);
        tv_nilai_total_1 = findViewById(R.id.dsn_sidang_nilai_total_1);
        tv_review_1 = findViewById(R.id.dsn_sidang_textview_review_1);
        tv_status_1 = findViewById(R.id.dsn_sidang_textview_status_1);

        tv_nilai_proposal2 = findViewById(R.id.dsn_sidang_nilai_proposal_2);
        tv_nilai_pembimbing2 = findViewById(R.id.dsn_sidang_nilai_pembimbing1_2);
        tv_nilai_penguji2_1 = findViewById(R.id.dsn_sidang_nilai_penguji2_1);
        tv_nilai_penguji2_2 = findViewById(R.id.dsn_sidang_nilai_penguji2_2);
        tv_nilai_total_2 = findViewById(R.id.dsn_sidang_nilai_total_2);
        tv_review_2 = findViewById(R.id.dsn_sidang_textview_review_2);
        tv_status_2 = findViewById(R.id.dsn_sidang_textview_status_2);
        final TextView tv_tambah_sidang1 = findViewById(R.id.act_dsn_textview_tambah_sidang_mhs_1);
        final TextView tv_tambah_sidang2 = findViewById(R.id.act_dsn_textview_tambah_sidang_mhs_2);

        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        extraJudulId = String.valueOf(extraProyekAkhir.getJudul_id());


        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, extraJudulId);
        sidangPresenter.searchAllSidangBy(PARAM_PROYEK_AKHIR, extraJudulId);
        detailMonevPresenter.getDetailMonev();
//        bimbinganPresenter.getBimbingan();
        if (bimbinganArrayList.size() > 16 && detailMonevArrayList.size() > 6){
            cardView_mhs_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DosenSidangActivity.this, DosenSidangTambahActivity.class);
                    ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(0);
                    intent.putExtra(EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                    startActivity(intent);
                }
            });

            cardView_mhs_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DosenSidangActivity.this, DosenSidangTambahActivity.class);
                    ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(1);
                    intent.putExtra(EXTRA_PROYEK_AKHIR, parcelProyekAkhir);
                    startActivity(intent);
                }
            });

        }else {


            cardView_mhs_1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DosenSidangActivity.this, "Belum Memenuhi Syarat untuk Sidang", Toast.LENGTH_SHORT).show();
                    tv_tambah_sidang1.setVisibility(View.GONE);
                }
            });

            cardView_mhs_2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(DosenSidangActivity.this, "Belum Memenuhi Syarat untuk Sidang", Toast.LENGTH_SHORT).show();
                    tv_tambah_sidang2.setVisibility(View.GONE);
                }
            });
        }




    }
    @Override
    protected void onResume() {
        super.onResume();
        sidangPresenter.getSidang();
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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        bimbinganArrayList.clear();
        bimbinganArrayList.addAll(bimbinganList);
    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        proyekAkhirArrayList.clear();
        proyekAkhirArrayList.addAll(proyekAkhirList);

        String stringProyekAkhirId = String.valueOf(proyekAkhirArrayList.get(0).getProyek_akhir_id());
        bimbinganPresenter.searchBimbinganAllBy(PARAM_BIMBINGAN, stringProyekAkhirId);


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
    public void onGetListSidang(List<Sidang> sidangList) {
        sidangArrayList.clear();
        sidangArrayList.addAll(sidangList);
        if (sidangArrayList.size() == 0){
            linearLayout_mhs1.setVisibility(View.GONE);
            linearLayout_mhs2.setVisibility(View.GONE);
        } else {
            if (sidangArrayList.size() == 2) {
                tv_nilai_proposal1.setText(String.valueOf(sidangArrayList.get(0).getNilai_proposal()));
                tv_nilai_pembimbing.setText(String.valueOf(sidangArrayList.get(0).getNilai_pembimbing()));
                tv_nilai_penguji1_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_1()));
                tv_nilai_penguji1_2.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_2()));
                tv_nilai_total_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_sidang()));
                tv_status_1.setText(sidangArrayList.get(0).getSidang_status());
                tv_review_1.setText(sidangArrayList.get(0).getSidang_review());

                tv_nilai_proposal2.setText(String.valueOf(sidangArrayList.get(1).getNilai_proposal()));
                tv_nilai_pembimbing2.setText(String.valueOf(sidangArrayList.get(1).getNilai_pembimbing()));
                tv_nilai_penguji2_1.setText(String.valueOf(sidangArrayList.get(1).getNilai_penguji_1()));
                tv_nilai_penguji2_2.setText(String.valueOf(sidangArrayList.get(1).getNilai_penguji_2()));
                tv_nilai_total_2.setText(String.valueOf(sidangArrayList.get(1).getNilai_sidang()));
                tv_status_2.setText(sidangArrayList.get(1).getSidang_status());
                tv_review_2.setText(sidangArrayList.get(1).getSidang_review());
            } else {
                tv_nilai_proposal1.setText(String.valueOf(sidangArrayList.get(0).getNilai_proposal()));
                tv_nilai_pembimbing.setText(String.valueOf(sidangArrayList.get(0).getNilai_pembimbing()));
                tv_nilai_penguji1_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_1()));
                tv_nilai_penguji1_2.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_2()));
                tv_nilai_total_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_sidang()));
                linearLayout_mhs2.setVisibility(View.GONE);
            }
        }

        }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
