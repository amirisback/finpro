package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.presenters.JudulPresenter;

import java.util.ArrayList;

public class MahasiswaJudulPaDosenDetailActivity extends AppCompatActivity {
    private JudulPresenter presenter;
    private ProgressDialog dialog;
    private Context mContext;
    FloatingActionButton button_pengajuan;
    public static final String EXTRA_JUDUL = "extra_judul";
    private ArrayList<Judul> datajudul;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_judul_pa_dosen_detail);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_pengajuan= findViewById(R.id.act_mhs_pengajuan_dosen_fab);






        button_pengajuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MahasiswaJudulPaDosenDetailActivity.this, MahasiswaJudulPaPengajuanDosenTambahActivity.class);
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

}
