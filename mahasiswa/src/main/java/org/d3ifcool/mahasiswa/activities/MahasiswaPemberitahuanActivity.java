package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.adapters.recyclerview.MahasiswaPemberitahuanViewAdapter;
import org.d3ifcool.service.interfaces.lists.NotifikasiListView;
import org.d3ifcool.service.models.Notifikasi;
import org.d3ifcool.service.presenters.NotifikasiPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaPemberitahuanActivity extends AppCompatActivity implements NotifikasiListView {

    private RecyclerView recyclerView;
    private NotifikasiPresenter notifikasiPresenter;
    private ProgressDialog progressDialog;
    private ArrayList<Notifikasi> notifikasiArrayList = new ArrayList<>();
    private MahasiswaPemberitahuanViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pemberitahuan);

        setTitle(getString(R.string.title_pemberitahuan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = findViewById(R.id.act_mhs_pemberitahuan_recyclerview);
        progressDialog = new ProgressDialog(this);
        notifikasiPresenter = new NotifikasiPresenter(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        notifikasiPresenter.getNotifikasi();

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
    public void onGetListNotifikasi(List<Notifikasi> notifikasiList) {
        notifikasiArrayList.clear();
        notifikasiArrayList.addAll(notifikasiList);

        adapter = new MahasiswaPemberitahuanViewAdapter(this, notifikasiArrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);


    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
