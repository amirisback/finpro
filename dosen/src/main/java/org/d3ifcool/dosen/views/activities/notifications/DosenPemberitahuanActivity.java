package org.d3ifcool.dosen.views.activities.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.views.adapters.recyclerviews.DosenPemberitahuanViewAdapter;
import org.d3ifcool.service.models.pemberitahuan;

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

        final ArrayList<pemberitahuan> data = new ArrayList<>();
        data.add(new pemberitahuan("Hariandi Maulid mengirim suatu informasi","28 januari 2019"
                ,"4 menit yang lalu","hariandi maulid","mahasiswa","informasi","ready"));

        DosenPemberitahuanViewAdapter adapter = new DosenPemberitahuanViewAdapter(this,data);
        rv.setLayoutManager(new LinearLayoutManager(this));
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
