package org.d3ifcool.finpro.views.activities.details;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.models.dataclass.Bimbingan;
import org.d3ifcool.finpro.views.adapters.recyclerviews.MahasiswaPaBimbinganViewAdapter;

import java.util.ArrayList;

public class MahasiswaPaBimbinganDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_bimbingan_detail);

        getSupportActionBar().setTitle(getString(R.string.title_bimbingan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewDosen = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_dosen);
        TextView textViewJumlah = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);
        RecyclerView recyclerView = findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);

        ArrayList<Bimbingan> arrayList = new ArrayList<>();
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));
        arrayList.add(new Bimbingan("a", "01 Februari 2019", "Revisi Bab 4"));

        MahasiswaPaBimbinganViewAdapter adapter = new MahasiswaPaBimbinganViewAdapter(this);
        adapter.addItem(arrayList);
        adapter.setLayoutType(R.layout.content_item_mahasiswa_pa_bimbingan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

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
