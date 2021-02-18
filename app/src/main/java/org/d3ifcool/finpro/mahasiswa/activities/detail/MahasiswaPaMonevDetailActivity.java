package org.d3ifcool.finpro.mahasiswa.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaPaMonevViewAdapter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.MonevDetailListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.MonevDetailPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaPaMonevDetailActivity extends AppCompatActivity implements MonevDetailListView, ProyekAkhirListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String PARAM_DETAIL_MONEV = "detail_monev.proyek_akhir_id";
    private static final String PARAM_PROYEK_AKHIR = "proyek_akhir.mhs_nim";

    private RecyclerView recyclerView;
    private View empty_view;

    private ProgressDialog progressDialog;
    private MonevDetailPresenter detailMonevPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;

    private ArrayList<DetailMonev> arrayList = new ArrayList<>();
    private MahasiswaPaMonevViewAdapter mahasiswaPaMonevViewAdapter;
    private TextView textViewJumlah;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_monev_detail);

        setTitle(getString(R.string.title_monev_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailMonevPresenter = new MonevDetailPresenter(this);
        proyekAkhirPresenter = new ProyekAkhirPresenter(this);

        detailMonevPresenter.initContext(this);
        proyekAkhirPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        SessionManager sessionManager = new SessionManager(this);
        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        String extraDosenNama = extraProyekAkhir.getReviewer_dsn_nama();

        TextView textViewDosen = findViewById(R.id.act_mhs_pa_monev_detail_textview_dosen);
        textViewDosen.setText(extraDosenNama);

        textViewJumlah = findViewById(R.id.act_mhs_pa_monev_detail_textview_jumlah);
        recyclerView = findViewById(R.id.act_mhs_pa_monev_detail_recyclerview);
        empty_view = findViewById(R.id.view_emptyview);

        mahasiswaPaMonevViewAdapter = new MahasiswaPaMonevViewAdapter(this);

        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, sessionManager.getSessionMahasiswaNim());

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
        progressDialog.hide();
    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        detailMonevPresenter.searchMonevDetailAllBy(PARAM_DETAIL_MONEV, String.valueOf(proyekAkhirList.get(0).getProyek_akhir_id()));
    }

    @Override
    public void isEmptyListProyekAkhir() {

    }

    @Override
    public void onGetListDetailMonev(List<DetailMonev> detailMonevList) {

        arrayList.clear();
        arrayList.addAll(detailMonevList);

        mahasiswaPaMonevViewAdapter.addItem(arrayList);
        mahasiswaPaMonevViewAdapter.setLayoutType(R.layout.content_list_all_pa_monev);

        recyclerView.setAdapter(mahasiswaPaMonevViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        textViewJumlah.setText(String.valueOf(arrayList.size()));

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListMonevDetail() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
