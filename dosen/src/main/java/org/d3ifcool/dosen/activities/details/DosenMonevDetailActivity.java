package org.d3ifcool.dosen.activities.details;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenMonevUbahActivity;
import org.d3ifcool.service.models.DetailMonev;

public class DosenMonevDetailActivity extends AppCompatActivity {

    public static final String EXTRA_MONEV = "extra_monev";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_detail);

        setTitle(getString(R.string.title_monev_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewTanggal = findViewById(R.id.act_dsn_monev_tanggal);
        TextView textViewKategori = findViewById(R.id.act_dsn_monev_kategori);
        TextView textViewUlasan = findViewById(R.id.act_dsn_monev_ulasan);
        TextView textViewNilai = findViewById(R.id.act_dsn_monev_nilai);

        DetailMonev extraDetailMonev = getIntent().getParcelableExtra(EXTRA_MONEV);
        String extraDetailMonevTanggal = extraDetailMonev.getMonev_tanggal();
        String extraDetailMonevKategori = extraDetailMonev.getMonev_kategori();
        String extraDetailMonevUlasan = extraDetailMonev.getMonev_ulasan();
        int extraDetailMonevNilai = extraDetailMonev.getMonev_nilai();

        textViewTanggal.setText(extraDetailMonevTanggal);
        textViewKategori.setText(extraDetailMonevKategori);
        textViewUlasan.setText(extraDetailMonevUlasan);
        textViewNilai.setText(String.valueOf(extraDetailMonevNilai));

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
