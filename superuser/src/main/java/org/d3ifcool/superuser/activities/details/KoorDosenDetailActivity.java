package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.d3ifcool.superuser.R;

public class KoorDosenDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_dosen_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_dosen_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
