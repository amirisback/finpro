package org.d3ifcool.dosen.views.activities.details;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.views.activities.edits.DosenMonevUbahActivity;

public class DosenMonevDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_detail);

        setTitle(getString(R.string.title_monev_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(DosenMonevDetailActivity.this, DosenMonevUbahActivity.class);
            startActivity(intentUbah);

        } else if (i == R.id.toolbar_menu_hapus) {
        } else {
        }
        return super.onOptionsItemSelected(item);
    }


}
