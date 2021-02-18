package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.finpro.core.interfaces.lists.KegiatanListView;
import org.d3ifcool.finpro.core.models.Kegiatan;
import org.d3ifcool.finpro.core.presenters.KegiatanPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenJadwalKegiatanViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class DosenJadwalKegiatanActivity extends AppCompatActivity implements KegiatanListView {

    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private View empty_view;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<Kegiatan> arrayListKegiatan = new ArrayList<>();
    private DosenJadwalKegiatanViewAdapter dosenJadwalKegiatanViewAdapter;
    private KegiatanPresenter kegiatanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_jadwal_kegiatan);

        setTitle(getString(R.string.title_jadwal_kegiatan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        dosenJadwalKegiatanViewAdapter = new DosenJadwalKegiatanViewAdapter(this);
        kegiatanPresenter = new KegiatanPresenter(this);
        kegiatanPresenter.initContext(this);

        recyclerView = findViewById(R.id.all_recyclerview_kegiatan);
        empty_view = findViewById(R.id.view_emptyview);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_kegiatan);

        kegiatanPresenter.getKegiatan();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                kegiatanPresenter.getKegiatan();
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
    public void onGetListKegiatan(List<Kegiatan> kegiatan) {
        arrayListKegiatan.clear();
        arrayListKegiatan.addAll(kegiatan);

        dosenJadwalKegiatanViewAdapter.addItem(arrayListKegiatan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(dosenJadwalKegiatanViewAdapter);
        swipeRefreshLayout.setRefreshing(false);

        if (arrayListKegiatan.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListKegiatan() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
