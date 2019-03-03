package org.d3ifcool.dosen.views.activities.details;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.dosen.views.activities.edits.DosenInformasiUbahActivity;

public class DosenInformasiDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INFORMASI = "extra_informasi";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_detail);

        setTitle(getString(R.string.title_informasi_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView_isi = findViewById(R.id.act_dsn_info_detail_textview_isi);
        TextView textView_tanggal = findViewById(R.id.act_dsn_info_detail_textview_tanggal);
        TextView textView_dosen = findViewById(R.id.act_dsn_info_detail_textview_dosen);

        Informasi extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String isi = extraInfo.getInfo_deskripsi();
        String tanggal = extraInfo.getInfo_tanggal();
        String dosen = extraInfo.getPublisher();

        textView_isi.setText(isi);
        textView_tanggal.setText(tanggal);
        textView_dosen.setText(dosen);

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
            Intent intentUbah = new Intent(DosenInformasiDetailActivity.this, DosenInformasiUbahActivity.class);
            startActivity(intentUbah);

        } else if (i == R.id.toolbar_menu_hapus) {
        } else {
        }
        return super.onOptionsItemSelected(item);
    }

}
