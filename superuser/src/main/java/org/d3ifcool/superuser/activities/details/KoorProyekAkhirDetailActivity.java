package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.superuser.R;

public class KoorProyekAkhirDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_proyek_akhir_detail);

        setTitle(getString(R.string.title_proyekakhir_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            //
        } else if (i == R.id.toolbar_menu_hapus) {
            //
        } else {
        }
        return super.onOptionsItemSelected(item);
    }
}
