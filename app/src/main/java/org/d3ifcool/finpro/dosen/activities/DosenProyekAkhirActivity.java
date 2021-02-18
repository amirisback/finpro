package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.core.adapters.AnggotaViewAdapter;
import org.d3ifcool.finpro.core.helpers.ViewAdapterHelper;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.lists.MonevDetailListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.MonevDetailPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUMLAH_BIMBINGAN_SIDANG;

public class DosenProyekAkhirActivity extends AppCompatActivity implements ProyekAkhirListView,
        BimbinganListView, MonevDetailListView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PROYEK_AKHIR_PARAM_1 = "proyek_akhir.judul_id";
    private static final String PROYEK_AKHIR_PARAM_2 = "judul_status";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private ProyekAkhirPresenter proyekAkhirPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private MonevDetailPresenter detailMonevPresenter;
    private ProgressDialog progressDialog;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<DetailMonev> arrayListDetailMonev = new ArrayList<>();
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private ProyekAkhir parcelProyekAkhir;

    private ViewAdapterHelper viewAdapterHelper;
    private AnggotaViewAdapter anggotaViewAdapter;

    private TextView tv_judul_pa,tv_kelompok_pa, tv_dosen_pembimbing_pa, tv_jumlah_bimbingan_pa,
            tv_dosen_reviewer_pa, tv_status_sidang_pa;

    private String stringJudulId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_proyek_akhir);

        setTitle(R.string.title_proyekakhir_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        detailMonevPresenter = new MonevDetailPresenter(this);

        proyekAkhirPresenter.initContext(this);
        bimbinganPresenter.initContext(this);
        detailMonevPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        tv_judul_pa = findViewById(R.id.ctn_all_pa_textview_judul);
        tv_kelompok_pa = findViewById(R.id.ctn_all_pa_textview_kelompok);
        tv_dosen_pembimbing_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_dosen_pembimbing);
        tv_jumlah_bimbingan_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_jml_bimbingan);
        tv_dosen_reviewer_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_dosen_reviewer);
        tv_status_sidang_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_status_sidang);

        RecyclerView recyclerView = findViewById(R.id.all_recyclerview_anggota);

        anggotaViewAdapter = new AnggotaViewAdapter(this);
        viewAdapterHelper = new ViewAdapterHelper(this);
        viewAdapterHelper.setRecyclerView(recyclerView);

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        stringJudulId = String.valueOf(extraJudul.getId());
        proyekAkhirPresenter.searchAllProyekAkhirByTwo(PROYEK_AKHIR_PARAM_1, stringJudulId, PROYEK_AKHIR_PARAM_2, JUDUL_STATUS_DIGUNAKAN);

        if (extraJudul.getDsn_nama() != null) {
            tv_dosen_pembimbing_pa.setText(extraJudul.getDsn_nama());
        } else {
            tv_dosen_pembimbing_pa.setText(R.string.text_no_dosen_pembimbing);
        }

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

        detailMonevPresenter.getMonevDetail();

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
    public void isEmptyListMonevDetail() {

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

            parcelProyekAkhir = arrayListProyekAkhir.get(0);
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
