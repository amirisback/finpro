package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.superuser.R;

public class KoorJudulPaSubdosenDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
