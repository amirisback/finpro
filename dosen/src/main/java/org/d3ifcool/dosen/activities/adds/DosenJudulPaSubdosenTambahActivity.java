package org.d3ifcool.dosen.activities.adds;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.JudulPaSubDosenViewResult;
import org.d3ifcool.service.models.JudulPa;
import org.d3ifcool.service.network.ApiInterfaceJudulPa;
import org.d3ifcool.service.presenter.JudulPaPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenJudulPaSubdosenTambahActivity extends AppCompatActivity {
    Spinner spinner_kategori;
    JudulPaPresenter presenter;


    private JudulPaSubDosenViewResult viewResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_tambah);
        presenter = new JudulPaPresenter(viewResult, this);

        spinner_kategori = findViewById(R.id.act_dsn_judul_pa_spinner_kategori);
        setTitle(R.string.title_judulpa_dosen_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       
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


}
