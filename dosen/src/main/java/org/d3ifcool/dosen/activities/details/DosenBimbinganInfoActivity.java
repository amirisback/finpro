package org.d3ifcool.dosen.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.d3ifcool.dosen.R;

public class DosenBimbinganInfoActivity extends AppCompatActivity {

    public static final String EXTRA_JUDUL = "extra_judul";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_info);
        TextView tv_judul_pa = findViewById(R.id.act_dsn_pa_bimbingan_textview_judulpa);
        TextView tv_kelompok = findViewById(R.id.act_dsn_pa_bimbingan_textview_kelompok);
        TextView tv_nama_1 = findViewById(R.id.act_dsn_pa_bimbingan_textview_nama_1);
        TextView tv_nim_1 = findViewById(R.id.act_dsn_pa_bimbingan_textview_nim_1);
        TextView tv_nama_2 = findViewById(R.id.act_dsn_pa_bimbingan_textview_nama_2);
        TextView tv_nim_2 = findViewById(R.id.act_dsn_pa_bimbingan_textview_nim_2);
        TextView tv_dosen_pembimbing = findViewById(R.id.act_dsn_pa_bimbingan_textview_dosen_pembimbing);
        TextView tv_jml_bimbingan = findViewById(R.id.act_dsn_pa_bimbingan_textview_jml_bimbingan);
        TextView tv_dosen_reviewer = findViewById(R.id.act_dsn_pa_bimbingan_textview_dosen_reviewer);
        TextView tv_jml_monev = findViewById(R.id.act_dsn_pa_bimbingan_textview_jml_monev);
        TextView ev_status_sidang = findViewById(R.id.act_dsn_pa_bimbingan_textview_status_sidang);

        CardView cardView = findViewById(R.id.frg_mhs_pa_cardview_bimbingan);
        CardView cardViewMonev = findViewById(R.id.frg_mhs_pa_cardview_monev);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenBimbinganInfoActivity.this, DosenBimbinganDetailActivity.class);

                startActivity(intent);
            }
        });

        cardViewMonev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenBimbinganInfoActivity.this, DosenMahasiswaMonevDetailActivity.class);

                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();

        if (i == android.R.id.home){
            finish();
        }else if (i == R.id.toolbar_menu_ubah){

        }else if (i == R.id.toolbar_menu_hapus){

        }

        return super.onOptionsItemSelected(item);
    }
}
