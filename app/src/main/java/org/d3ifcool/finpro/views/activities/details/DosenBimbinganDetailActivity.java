package org.d3ifcool.finpro.views.activities.details;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.views.activities.edits.DosenBimbinganUbahActivity;

public class DosenBimbinganDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_detail);

        setTitle(getString(R.string.title_bimbingan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.toolbar_menu_ubah:
                Intent intentUbah = new Intent(DosenBimbinganDetailActivity.this, DosenBimbinganUbahActivity.class);
                startActivity(intentUbah);
                break;
            case R.id.toolbar_menu_hapus:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
