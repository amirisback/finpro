package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.adapters.recyclerview.MahasiswaPemberitahuanViewAdapter;
import org.d3ifcool.base.interfaces.lists.NotifikasiListView;
import org.d3ifcool.base.interfaces.works.NotifikasiWorkView;
import org.d3ifcool.base.models.Notifikasi;
import org.d3ifcool.base.presenters.NotifikasiPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaPemberitahuanActivity extends AppCompatActivity implements NotifikasiListView, NotifikasiWorkView {

    private RecyclerView recyclerView;
    private NotifikasiPresenter notifikasiPresenter;
    private ProgressDialog progressDialog;
    private ArrayList<Notifikasi> notifikasiArrayList = new ArrayList<>();
    private MahasiswaPemberitahuanViewAdapter adapter;
    private View empty_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pemberitahuan);

        setTitle(getString(R.string.title_pemberitahuan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        notifikasiPresenter = new NotifikasiPresenter(this, this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        adapter = new MahasiswaPemberitahuanViewAdapter(this);
        recyclerView = findViewById(R.id.act_mhs_pemberitahuan_recyclerview);
        empty_view = findViewById(R.id.view_emptyview);

        adapter.setNotifikasiPresenter(notifikasiPresenter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);

        notifikasiPresenter.getNotifikasi();

    }

    @Override
    protected void onResume() {
        super.onResume();
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
    public void onSucces() {
    }

    @Override
    public void onGetListNotifikasi(List<Notifikasi> notifikasiList) {
        notifikasiArrayList.clear();
        notifikasiArrayList.addAll(notifikasiList);

        adapter.addItem(notifikasiArrayList);
        recyclerView.setAdapter(adapter);

        if (notifikasiArrayList.size() != 0){
            empty_view.setVisibility(View.GONE);

        } else {
            empty_view.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
