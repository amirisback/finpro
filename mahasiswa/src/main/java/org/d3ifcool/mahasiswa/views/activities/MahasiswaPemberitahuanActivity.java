package org.d3ifcool.mahasiswa.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.mahasiswa.views.adapters.MahasiswaPemberitahuanViewAdapter;
import org.d3ifcool.service.models.pemberitahuan;

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
        final ArrayList<pemberitahuan> notifs = new ArrayList<>();
        notifs.add(new pemberitahuan("Hariandi Maulid mengirim suatu informasi","28 januari 2019"
                ,"4 menit yang lalu","hariandi maulid","mahasiswa","informasi","ready"));

        MahasiswaPemberitahuanViewAdapter adapter = new MahasiswaPemberitahuanViewAdapter(this, notifs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

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
