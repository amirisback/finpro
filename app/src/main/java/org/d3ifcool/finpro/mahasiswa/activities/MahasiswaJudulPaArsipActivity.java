package org.d3ifcool.finpro.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaJudulPaArsipViewAdapter;
import org.d3ifcool.finpro.core.helpers.SpinnerHelper;
import org.d3ifcool.finpro.core.interfaces.lists.JudulListView;
import org.d3ifcool.finpro.core.interfaces.lists.KategoriJudulListView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.KategoriJudul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.KategoriJudulPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_ARSIP;

public class MahasiswaJudulPaArsipActivity extends AppCompatActivity implements JudulListView, KategoriJudulListView {

    private final String PARAM_KATEGORI = "judul.kategori_id";
    private final String PARAM_STATUS = "judul.judul_status";

    private Spinner sp_kategori;
    private RecyclerView recyclerView;
    private MahasiswaJudulPaArsipViewAdapter adapter;
    private ProgressDialog progressDialog;
    private View empty_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Judul> arrayListJudul = new ArrayList<>();
    private ArrayList<KategoriJudul> arrayListKategoriJudul = new ArrayList<>();

    private JudulPresenter judulPresenter;
    private SpinnerHelper spinnerHelper;

    private String kategori_id;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_arsip);

        setTitle(getString(R.string.title_arsip_judul));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sp_kategori = findViewById(R.id.spinner_kategori);
        recyclerView = findViewById(R.id.frg_mahasiswa_judul_arsip_recyclerview);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        spinnerHelper = new SpinnerHelper(this);

        adapter = new MahasiswaJudulPaArsipViewAdapter(this);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        empty_view = findViewById(R.id.view_emptyview);

        KategoriJudulPresenter kategoriJudulPresenter = new KategoriJudulPresenter(this);
        judulPresenter = new JudulPresenter(this);

        judulPresenter.initContext(this);
        kategoriJudulPresenter.initContext(this);

        kategoriJudulPresenter.getKategori();

        sp_kategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                kategori_id = String.valueOf(arrayListKategoriJudul.get(position).getId());
                judulPresenter.searchJudulByTwo(PARAM_KATEGORI, kategori_id, PARAM_STATUS, JUDUL_STATUS_ARSIP);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                judulPresenter.searchJudulByTwo(PARAM_KATEGORI, kategori_id, PARAM_STATUS, JUDUL_STATUS_ARSIP);
            }
        });
        
    }

    @Override
    public void onResume() {
        super.onResume();
        if (kategori_id != null) {
            judulPresenter.searchJudulByTwo(PARAM_KATEGORI, kategori_id, PARAM_STATUS, JUDUL_STATUS_ARSIP);
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
        progressDialog.hide();
    }

    @Override
    public void onGetListKategoriJudul(List<KategoriJudul> kategori) {
        arrayListKategoriJudul.clear();
        arrayListKategoriJudul.addAll(kategori);
        spinnerHelper.initSpinnerKategoriJudul(arrayListKategoriJudul, sp_kategori);
    }

    @Override
    public void isEmptyListKategori() {

    }

    @Override
    public void onGetListJudul(List<Judul> judulpa) {

        arrayListJudul.clear();
        arrayListJudul.addAll(judulpa);
        adapter.additem(arrayListJudul);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListJudul.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListJudul() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
