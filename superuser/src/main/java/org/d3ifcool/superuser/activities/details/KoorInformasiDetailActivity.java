package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.d3ifcool.dosen.activities.details.DosenInformasiDetailActivity;
import org.d3ifcool.dosen.activities.editors.DosenInformasiUbahActivity;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.superuser.R;

public class KoorInformasiDetailActivity extends AppCompatActivity {

    public static final String EXTRA_INFORMASI = "extra_informasi";
    private Informasi extraInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_informasi_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_informasi_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textView_judul = findViewById(R.id.act_koor_info_detail_textview_judul);
        TextView textView_isi = findViewById(R.id.act_koor_info_detail_textview_isi);
        TextView textView_tanggal = findViewById(R.id.act_koor_info_detail_textview_tanggal);
        TextView textView_nama = findViewById(R.id.act_koor_info_nama);
//        CircleImageView imageView_foto = findViewById();

        extraInfo = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extraInfo.getInfo_judul();
        String isi = extraInfo.getInfo_deskripsi();
        String tanggal = extraInfo.getInfo_tanggal();
        String nama = extraInfo.getPublisher();
//        String foto = extraInfo.getFoto();

        textView_judul.setText(judul);
        textView_isi.setText(isi);
        textView_nama.setText(nama);
        textView_tanggal.setText(tanggal);



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

        } else if (i == org.d3ifcool.dosen.R.id.toolbar_menu_ubah) {
//            Intent intentUbah = new Intent(KoorInformasiDetailActivity.this, KooInformasiUbahActivity.class);
//            intentUbah.putExtra(DosenInformasiUbahActivity.EXTRA_INFORMASI, extraInfo);
//            startActivity(intentUbah);
//            finish();

        } else if (i == org.d3ifcool.dosen.R.id.toolbar_menu_hapus) {
//            presenter.deleteInformasi(extraInfo.getId());
        }

        return super.onOptionsItemSelected(item);
    }

}
