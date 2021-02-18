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

import org.d3ifcool.finpro.core.adapters.AllDosenKuotaViewAdpater;
import org.d3ifcool.finpro.core.interfaces.lists.DosenListView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;
import org.d3ifcool.finpro.R;

import java.util.ArrayList;
import java.util.List;

public class DosenKuotaDosenActivity extends AppCompatActivity implements DosenListView {

    private RecyclerView recyclerView;
    private ArrayList<Dosen> arrayList = new ArrayList<>();

    private AllDosenKuotaViewAdpater adapter;
    private ProgressDialog progressDialog;
    private DosenPresenter dosenPresenter;

    private SwipeRefreshLayout refreshLayout;
    private View empty_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_kuota_dosen);

        setTitle(getString(R.string.title_kuota_dosen));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dosenPresenter = new DosenPresenter(this);
        dosenPresenter.initContext(this);

        refreshLayout = findViewById(R.id.refresh);
        recyclerView = findViewById(R.id.frg_koor_dosen_home_recyclerview);
        empty_view = findViewById(R.id.view_emptyview);

        adapter = new AllDosenKuotaViewAdpater(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dosenPresenter.getDosen();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dosenPresenter.getDosen();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        dosenPresenter.getDosen();
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
    public void onGetListDosen(List<Dosen> dosen) {

        empty_view.setVisibility(View.GONE);
        arrayList.clear();
        arrayList.addAll(dosen);

        adapter.setDosens(arrayList);
        recyclerView.setAdapter(adapter);
        refreshLayout.setRefreshing(false);

        if (arrayList.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListDosen() {
        empty_view.setVisibility(View.VISIBLE);
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

