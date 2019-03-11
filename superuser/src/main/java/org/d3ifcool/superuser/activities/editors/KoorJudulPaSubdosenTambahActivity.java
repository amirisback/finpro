package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.superuser.R;

public class KoorJudulPaSubdosenTambahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_tambah);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_judulpa_dosen_tambah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
