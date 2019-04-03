package org.d3ifcool.dosen.activities.details;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenBimbinganTambahActivity;
import org.d3ifcool.dosen.activities.editors.DosenBimbinganUbahActivity;
import org.d3ifcool.service.interfaces.lists.BimbinganListView;
import org.d3ifcool.service.models.Bimbingan;
import org.d3ifcool.service.presenters.BimbinganPresenter;

import java.util.List;

public class DosenBimbinganDetailActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ProgressDialog dialog;
    private BimbinganPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_detail);

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        recyclerView = findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);
        FloatingActionButton btn_tambah_bimbingan = findViewById(R.id.act_dsn_bimbingan_tambah);
        TextView tv_judul_pa = findViewById(R.id.act_dsn_pa_bimbingan_detail_textview_judul);
        TextView tv_nama_anggota_1 = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_mhs1);
        TextView tv_nama_anggota_2 = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_mhs2);
        TextView tv_jml_bimbingan = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);

        setTitle(getString(R.string.title_bimbingan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btn_tambah_bimbingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DosenBimbinganDetailActivity.this, DosenBimbinganTambahActivity.class);
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
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {


        } else if (i == R.id.toolbar_menu_hapus) {

        }
        return super.onOptionsItemSelected(item);
    }


}
