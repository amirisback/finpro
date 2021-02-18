package org.d3ifcool.finpro.dosen.activities.detail;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.editor.update.DosenJudulPaSubdosenUbahActivity;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenKelompokPengajuanJudulViewAdapter;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenJudulPaSubdosenDetailActivity extends AppCompatActivity implements JudulWorkView, ProyekAkhirListView {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    public static final String PARAM_JUDUL_ID = "proyek_akhir.judul_id";
    public static final String PARAM_JUDUL_STATUS = "judul.judul_status";

    private Judul extraJudul;

    private JudulPresenter judulPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;

    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private RecyclerView recyclerView;
    private View empty_view;
    private int judul_id;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DosenKelompokPengajuanJudulViewAdapter adapter;
    private ArrayList<ProyekAkhir> proyekAkhirArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_detail);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView tv_judul = findViewById(R.id.frg_dsn_pa_textview_judulpa);
        TextView tv_kategori = findViewById(R.id.frg_dsn_pa_textview_kategoripa);
        TextView tv_deskripsi = findViewById(R.id.frg_dsn_pa_textview_deskripsi);

        recyclerView = findViewById(R.id.act_dsn_recycler_view_kelompok);
        empty_view = findViewById(R.id.view_emptyview);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);

        recyclerView.setNestedScrollingEnabled(false);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        judulPresenter = new JudulPresenter(this);

        proyekAkhirPresenter.initContext(this);
        judulPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        adapter = new DosenKelompokPengajuanJudulViewAdapter(this);

        extraJudul = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraJudul.getJudul();
        String kategori = extraJudul.getKategori_nama();
        String deskripsi = extraJudul.getDeskripsi();
        judul_id = extraJudul.getId();

        adapter.addExtraJudul(judul_id);

        tv_judul.setText(judul);
        tv_kategori.setText(kategori);
        tv_deskripsi.setText(deskripsi);

        proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAM_JUDUL_ID, String.valueOf(judul_id));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAM_JUDUL_ID, String.valueOf(judul_id));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        proyekAkhirPresenter.searchDistinctProyekAkhirBy(PARAM_JUDUL_ID, String.valueOf(judul_id));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String nip_dosen = extraJudul.getNip_dosen();
        if(nip_dosen.equalsIgnoreCase(sessionManager.getSessionDosenNip())) {
            getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(DosenJudulPaSubdosenDetailActivity.this, DosenJudulPaSubdosenUbahActivity.class);
            Judul parcelinfo = extraJudul;
            intentUbah.putExtra(DosenJudulPaSubdosenUbahActivity.EXTRA_INFORMASI, parcelinfo);
            startActivity(intentUbah);
            finish();

        } else if (i == R.id.toolbar_menu_hapus) {

            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            judulPresenter.deleteJudul(extraJudul.getId());
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

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
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {

        proyekAkhirArrayList.clear();
        proyekAkhirArrayList.addAll(proyekAkhirList);
        adapter.addItem(proyekAkhirArrayList);
        adapter.setLayoutType(R.layout.content_list_dosen_pa_kelompok);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        swipeRefreshLayout.setRefreshing(false);

        if (proyekAkhirArrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListProyekAkhir() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onSuccesWorkJudul() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}


