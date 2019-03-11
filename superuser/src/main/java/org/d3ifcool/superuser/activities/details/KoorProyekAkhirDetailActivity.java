package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.superuser.R;

public class KoorProyekAkhirDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_proyek_akhir_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_proyekakhir_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
