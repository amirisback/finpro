package org.d3ifcool.mahasiswa.activities;

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

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.interfaces.lists.BimbinganListView;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.mahasiswa.adapters.recyclerview.MahasiswaPaBimbinganViewAdapter;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.BimbinganPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaPaBimbinganActivity extends AppCompatActivity implements BimbinganListView {

    public static final String EXTRA_DOSEN_PEMBIMBING = "extra_dosen_pembimbing";
    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private RecyclerView recyclerView;
    private BimbinganPresenter bimbinganPresenter;
    private MahasiswaPaBimbinganViewAdapter adapter;
    private View empty_view;

    private ProgressDialog progressDialog;
    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> extraArrayProyekAkhir;

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
        progressDialog.setMessage(getString(org.d3ifcool.mahasiswa.R.string.text_progress_dialog));
        empty_view = findViewById(R.id.view_emptyview);

        recyclerView = findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);

        TextView textViewDosen = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_dosen);
        textViewJumlah = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);

        extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);
        Dosen extraDosen = getIntent().getParcelableExtra(EXTRA_DOSEN_PEMBIMBING);
        textViewDosen.setText(extraDosen.getDsn_nama());

        bimbinganPresenter.searchBimbingan(BIMBINGAN_PARAM, String.valueOf(extraArrayProyekAkhir.get(0).getProyek_akhir_id()));

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
        adapter.setLayoutType(R.layout.content_item_mahasiswa_pa_bimbingan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        textViewJumlah.setText(String.valueOf(arrayListBimbingan.size()));

        if (arrayListBimbingan.size() == 0) {
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
