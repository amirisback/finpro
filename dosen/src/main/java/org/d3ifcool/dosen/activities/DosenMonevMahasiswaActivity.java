package org.d3ifcool.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editor.create.DosenMonevTambahActivity;
import org.d3ifcool.dosen.adapters.recyclerview.DosenMonevViewAdapter;
import org.d3ifcool.base.helpers.SessionManager;
import org.d3ifcool.base.interfaces.lists.MonevDetailListView;
import org.d3ifcool.base.models.DetailMonev;
import org.d3ifcool.base.models.ProyekAkhir;
import org.d3ifcool.base.presenters.MonevDetailPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenMonevMahasiswaActivity extends AppCompatActivity implements MonevDetailListView {

    public static final String EXTRA_PROYEK_AKHIR_MONEV = "extra_monev";
    private static final String PARAM_PROYEK_AKHIR = "detail_monev.proyek_akhir_id";

    private RecyclerView recyclerView;
    private View empty_view;
    private ProgressDialog progressDialog;
    private MonevDetailPresenter detailMonevPresenter;
    private ArrayList<DetailMonev> arrayList = new ArrayList<>();
    private DosenMonevViewAdapter dosenMonevViewAdapter;
    int extraProyekAkhirId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_mahasiswa);

        setTitle(getString(R.string.title_monev_mahasiswa));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SessionManager sessionManager = new SessionManager(this);
        FloatingActionButton floatingActionButton = findViewById(R.id.frg_dsn_add_monev);
        recyclerView = findViewById(R.id.act_mhs_pa_monev_recyclerview);
        empty_view = findViewById(R.id.view_emptyview);

        detailMonevPresenter = new MonevDetailPresenter(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        dosenMonevViewAdapter = new DosenMonevViewAdapter(this);

        final ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR_MONEV);
        extraProyekAkhirId = extraProyekAkhir.getProyek_akhir_id();
        String extraDosenReviewer = extraProyekAkhir.getReviewer_dsn_nip();

        detailMonevPresenter.searchMonevDetailAllBy(PARAM_PROYEK_AKHIR, String.valueOf(extraProyekAkhirId));

        if (sessionManager.getSessionDosenNip().equalsIgnoreCase(extraDosenReviewer)){
            floatingActionButton.setVisibility(View.VISIBLE);

            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DosenMonevMahasiswaActivity.this, DosenMonevTambahActivity.class);
                    intent.putExtra(DosenMonevTambahActivity.EXTRA_PROYEK_AKHIR, extraProyekAkhir);
                    startActivity(intent);
                }
            });

        } else {
            floatingActionButton.setVisibility(View.GONE);
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        detailMonevPresenter.searchMonevDetailAllBy(PARAM_PROYEK_AKHIR, String.valueOf(extraProyekAkhirId));
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
        arrayList.clear();
        arrayList.addAll(detailMonevList);

        dosenMonevViewAdapter.addItem(arrayList);
        dosenMonevViewAdapter.setLayoutType(R.layout.content_item_mahasiswa_pa_monev);

        recyclerView.setAdapter(dosenMonevViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
