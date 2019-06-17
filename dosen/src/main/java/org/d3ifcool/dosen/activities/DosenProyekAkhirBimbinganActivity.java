package org.d3ifcool.dosen.activities;

import android.app.ProgressDialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.recyclerview.DosenBimbinganViewAdapter;
import org.d3ifcool.base.interfaces.lists.BimbinganListView;
import org.d3ifcool.base.models.Bimbingan;
import org.d3ifcool.base.models.ProyekAkhir;
import org.d3ifcool.base.presenters.BimbinganPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenProyekAkhirBimbinganActivity extends AppCompatActivity implements BimbinganListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private RecyclerView recyclerView;
    private BimbinganPresenter bimbinganPresenter;
    private DosenBimbinganViewAdapter adapter;
    private View empty_view;

    private ProgressDialog progressDialog;
    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<ProyekAkhir> extraArrayProyekAkhir;

    private TextView tv_jml_bimbingan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_proyek_akhir_bimbingan);

        setTitle(getString(R.string.title_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new DosenBimbinganViewAdapter(this);
        progressDialog = new ProgressDialog(this);
        bimbinganPresenter = new BimbinganPresenter(this);
        bimbinganPresenter.initContext(this);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        empty_view = findViewById(R.id.view_emptyview);
        recyclerView = findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);
        tv_jml_bimbingan = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);

        extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);
        adapter.addProyekAkhir(extraArrayProyekAkhir);
        bimbinganPresenter.searchBimbinganAllBy(BIMBINGAN_PARAM, String.valueOf(extraArrayProyekAkhir.get(0).getProyek_akhir_id()));

    }

    @Override
    protected void onResume() {
        super.onResume();
        bimbinganPresenter.searchBimbinganAllBy(BIMBINGAN_PARAM, String.valueOf(extraArrayProyekAkhir.get(0).getProyek_akhir_id()));
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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {

        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);
        adapter.addItem(arrayListBimbingan);
        adapter.setLayoutType(R.layout.content_list_all_pa_bimbingan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        tv_jml_bimbingan.setText(String.valueOf(arrayListBimbingan.size()));

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
