package org.d3ifcool.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.adapters.recyclerviews.DosenPemberitahuanViewAdapter;
import org.d3ifcool.service.models.Notifikasi;

import java.util.ArrayList;

public class DosenPemberitahuanActivity extends AppCompatActivity {

    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_pemberitahuan);

        setTitle(getString(R.string.title_pemberitahuan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = findViewById(R.id.act_dsn_pemberitahuan_recyclerview);

        final ArrayList<Notifikasi> data = new ArrayList<>();
        data.add(new Notifikasi("Hariandi Maulid mengirim suatu informasi",getString(R.string.dummy_tanggal),"4 menit yang lalu","hariandi maulid","mahasiswa","informasi","ready"));
        data.add(new Notifikasi("Hariandi Maulid mengirim suatu informasi",getString(R.string.dummy_tanggal),"4 menit yang lalu","hariandi maulid","mahasiswa","informasi","ready"));
        data.add(new Notifikasi("Hariandi Maulid mengirim suatu informasi",getString(R.string.dummy_tanggal),"4 menit yang lalu","hariandi maulid","mahasiswa","informasi","ready"));
        data.add(new Notifikasi("Hariandi Maulid mengirim suatu informasi",getString(R.string.dummy_tanggal),"4 menit yang lalu","hariandi maulid","mahasiswa","informasi","ready"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        DosenPemberitahuanViewAdapter adapter = new DosenPemberitahuanViewAdapter(this,data);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        rv.setLayoutManager(linearLayoutManager);
        rv.addItemDecoration(itemDecoration);
        rv.setAdapter(adapter);

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
