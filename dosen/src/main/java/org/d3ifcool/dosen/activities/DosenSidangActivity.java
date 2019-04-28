package org.d3ifcool.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editor.create.DosenProyekAkhirSidangTambahActivity;
import org.d3ifcool.service.models.ProyekAkhir;

import java.util.ArrayList;

public class DosenSidangActivity extends AppCompatActivity {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private ArrayList<ProyekAkhir> extraPa;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_sidang);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        CardView cardView_mhs_1 = findViewById(R.id.card_view_mhs_1);
        CardView cardView_mhs_2 = findViewById(R.id.card_view_mhs_2);

        cardView_mhs_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DosenSidangActivity.this, DosenProyekAkhirSidangTambahActivity.class);
                startActivity(intent);
            }
        });


//        TextView tv_judul = findViewById(R.id.);
//        TextView tv_kelompok = findViewById(R.id.);
//        TextView tv_jumlahbimbingan = findViewById(R.id.);
//        TextView tv_nama1 = findViewById(R.id.);
//        TextView tv_nim1 = findViewById(R.id.);
//        TextView tv_nama2 = findViewById(R.id.);
//        TextView tv_nim2 = findViewById(R.id.);
//        TextView tv_judul = findViewById(R.id.);
//        TextView tv_judul = findViewById(R.id.);
//        TextView tv_judul = findViewById(R.id.);
//        TextView tv_judul = findViewById(R.id.);
//        TextView tv_judul = findViewById(R.id.);
//        TextView tv_judul = findViewById(R.id.);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
