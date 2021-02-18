package org.d3ifcool.finpro.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.editor.MahasiswaPaBimbinganTambahActivity;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.mahasiswa.adapters.recyclerview.MahasiswaPaBimbinganViewAdapter;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaPaBimbinganActivity extends AppCompatActivity implements BimbinganListView {

    public static final String EXTRA_DOSEN_PEMBIMBING = "extra_dosen_pembimbing";
    public static final String EXTRA_BIMBINGAN_JUMLAH = "extra_bimbingan_jumlah";
    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String PARAM_BIMBINGAN_ID = "bimbingan.proyek_akhir_id";
    private static final String PARAM_BIMBINGAN_STATUS = "bimbingan.bimbingan_status";

    private RecyclerView recyclerView;
    private BimbinganPresenter bimbinganPresenter;
    private MahasiswaPaBimbinganViewAdapter adapter;
    private View empty_view;

    private ProgressDialog progressDialog;
    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> extraArrayProyekAkhir;
    private SessionManager sessionManager;
    private TextView textViewJumlah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_bimbingan);

        setTitle(getString(R.string.title_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new MahasiswaPaBimbinganViewAdapter(this);
        progressDialog = new ProgressDialog(this);

        bimbinganPresenter = new BimbinganPresenter(this);
        bimbinganPresenter.initContext(this);

        progressDialog.setMessage(getString(org.d3ifcool.finpro.R.string.text_progress_dialog));
        empty_view = findViewById(R.id.view_emptyview);
        sessionManager = new SessionManager(this);

        recyclerView = findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);
        FloatingActionButton btn_tambah_bimbingan = findViewById(R.id.act_mhs_bimbingan_tambah);

        TextView textViewDosen = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_dosen);
        textViewJumlah = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);

        extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);
        Dosen extraDosen = getIntent().getParcelableExtra(EXTRA_DOSEN_PEMBIMBING);
        int extraJumlahBimbingan = getIntent().getIntExtra(EXTRA_BIMBINGAN_JUMLAH, 0);
        textViewJumlah.setText(String.valueOf(extraJumlahBimbingan));
        textViewDosen.setText(extraDosen.getDsn_nama());


        for (int i = 0; i < extraArrayProyekAkhir.size(); i++) {
            if (extraArrayProyekAkhir.get(i).getMhs_nim().equals(sessionManager.getSessionMahasiswaNim())  ) {
                bimbinganPresenter.searchBimbinganAllBy(PARAM_BIMBINGAN_ID, String.valueOf(extraArrayProyekAkhir.get(i).getProyek_akhir_id()));
            }
        }

        btn_tambah_bimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MahasiswaPaBimbinganActivity.this, MahasiswaPaBimbinganTambahActivity.class);
                intent.putParcelableArrayListExtra(MahasiswaPaBimbinganTambahActivity.EXTRA_PROYEK_AKHIR, extraArrayProyekAkhir);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        for (int i = 0; i < extraArrayProyekAkhir.size(); i++) {
            if (extraArrayProyekAkhir.get(i).getMhs_nim().equals(sessionManager.getSessionMahasiswaNim())) {
                bimbinganPresenter.searchBimbinganAllBy(PARAM_BIMBINGAN_ID, String.valueOf(extraArrayProyekAkhir.get(i).getProyek_akhir_id()));
            }
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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);
        adapter.addItem(arrayListBimbingan);
        adapter.setLayoutType(R.layout.content_list_all_pa_bimbingan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

//        textViewJumlah.setText(String.valueOf(arrayListBimbingan.size()));

        if (arrayListBimbingan.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListBimbingan() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
