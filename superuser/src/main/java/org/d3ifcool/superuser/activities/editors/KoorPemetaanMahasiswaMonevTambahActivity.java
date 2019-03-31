package org.d3ifcool.superuser.activities.editors;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.superuser.R;

public class KoorPemetaanMahasiswaMonevTambahActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_pemetaan_mahasiswa_monev_tambah);

        setTitle(getString(R.string.title_pemetaan_monev));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
