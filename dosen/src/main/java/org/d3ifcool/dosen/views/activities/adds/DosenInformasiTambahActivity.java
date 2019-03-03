package org.d3ifcool.dosen.views.activities.adds;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.InformasiView;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.network.ApiInterfaceInformasi;
import org.d3ifcool.service.presenter.InformasiPresenter;

public class DosenInformasiTambahActivity extends AppCompatActivity implements InformasiView {
    ProgressDialog progressDialog;
    InformasiPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_informasi_tambah);
        setTitle(R.string.title_informasi_tambah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final EditText info_judul = findViewById(R.id.act_dsn_judul_editext);
        final EditText info_deskripsi = findViewById(R.id.act_dsn_edittext_deskripsi);
        Button btn_simpan = findViewById(R.id.act_dsn_info_button_simpan);
        presenter = new InformasiPresenter(this,DosenInformasiTambahActivity.this);
        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text_info_judul = info_judul.getText().toString();
                String text_info_deskripsi = info_deskripsi.getText().toString();


                if(text_info_judul.isEmpty()){
                    info_judul.setError("harus di isi");
                }else if(text_info_deskripsi.isEmpty()){
                    info_deskripsi.setError("harus di isi");
                }else{
                    presenter.createInformasi(text_info_deskripsi,text_info_judul);
                }
            }
        });

        progressDialog = new ProgressDialog(this);
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

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
