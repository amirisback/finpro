package org.d3ifcool.dosen.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenMonevTambahActivity;
import org.d3ifcool.service.interfaces.lists.MonevListView;
import org.d3ifcool.service.models.DetailMonev;
import org.d3ifcool.service.models.Monev;
import org.d3ifcool.service.presenters.DetailMonevPresenter;

import java.util.List;

public class DosenMahasiswaMonevDetailActivity extends AppCompatActivity implements MonevListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private FloatingActionButton actionButton;
    private DetailMonevPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_pa_monev_detail);

        recyclerView = findViewById(R.id.act_mhs_pa_monev_detail_recyclerview);
        dialog = new ProgressDialog(this);
        actionButton = findViewById(R.id.act_dsn_monev_tambah);
//        presenter = new DetailMonevPresenter(this);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenMahasiswaMonevDetailActivity.this, DosenMonevTambahActivity.class);
                startActivity(intent);
            }
        });


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

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetListMonev(List<Monev> monevList) {

    }


    @Override
    public void onFailed(String message) {

    }
}
