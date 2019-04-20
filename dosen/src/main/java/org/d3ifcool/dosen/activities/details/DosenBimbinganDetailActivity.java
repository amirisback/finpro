package org.d3ifcool.dosen.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.models.Bimbingan;

public class DosenBimbinganDetailActivity extends AppCompatActivity {

    public static final String EXTRA_BIMBINGAN = "extra_bimbingan";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_detail);

        setTitle(getString(R.string.title_bimbingan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewTanggal = findViewById(R.id.act_dsn_mhs_bimbingan_textview_tanggal);
        TextView textViewJudulBimbingan = findViewById(R.id.act_dsn_mhs_bimbingan_textview_judul);
        TextView textViewReviewBimbingan = findViewById(R.id.act_dsn_mhs_bimbingan_textview_review);

        Bimbingan extraBimbingan = getIntent().getParcelableExtra(EXTRA_BIMBINGAN);

        textViewTanggal.setText(extraBimbingan.getTanggal());
        textViewJudulBimbingan.setText(extraBimbingan.getJudul_bimbingan());
        textViewReviewBimbingan.setText(extraBimbingan.getIsi());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
