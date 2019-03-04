package org.d3ifcool.dosen.activities.profiles;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.edits.DosenProfilUbahActivity;

public class DosenProfilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_profil);
        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_hanya_ubah) {
            Intent intentUbah = new Intent(DosenProfilActivity.this, DosenProfilUbahActivity.class);
            startActivity(intentUbah);

        } else {
        }
        return super.onOptionsItemSelected(item);
    }

}
