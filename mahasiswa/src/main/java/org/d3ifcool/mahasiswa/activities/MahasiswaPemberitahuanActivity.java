package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.adapters.recyclerview.MahasiswaPemberitahuanViewAdapter;
import org.d3ifcool.service.models.Notifikasi;

import java.util.ArrayList;

public class MahasiswaPemberitahuanActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pemberitahuan);

        setTitle(getString(R.string.title_pemberitahuan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.act_mhs_pemberitahuan_recyclerview);
        final ArrayList<Notifikasi> notifs = new ArrayList<>();
        notifs.add(new Notifikasi("Hariandi Maulid mengirim suatu informasi",getString(R.string.dummy_tanggal),"4 menit yang lalu","hariandi maulid","mahasiswa","informasi",true));

        MahasiswaPemberitahuanViewAdapter adapter = new MahasiswaPemberitahuanViewAdapter(this, notifs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);;
        DividerItemDecoration itemDecoration = new DividerItemDecoration(this, linearLayoutManager.getOrientation());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(itemDecoration);

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
