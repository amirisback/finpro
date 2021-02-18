package org.d3ifcool.finpro.dosen.activities;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.dosen.activities.editor.update.DosenProfilUbahActivity;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class DosenProfilActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private CircleImageView imageView;
    private TextView textView_nama, textView_nip, textView_kode, textView_email, textView_kontak,
    textView_batas_bimbingan, textView_batas_reviewer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_profil);

        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        sessionManager = new SessionManager(this);
        textView_nama = findViewById(R.id.act_dsn_profil_nama);
        textView_nip = findViewById(R.id.act_dsn_profil_nip);
        textView_kode = findViewById(R.id.act_dsn_profil_kode);
        textView_email = findViewById(R.id.act_dsn_profil_email);
        textView_kontak = findViewById(R.id.act_dsn_profil_kontak);
        textView_batas_bimbingan = findViewById(R.id.act_dsn_profil_batas_bimbingan);
        textView_batas_reviewer = findViewById(R.id.act_dsn_profil_batas_reviewer);
        imageView = findViewById(R.id.act_dsn_profil_foto);

        initContentView();
        Picasso.get().load(URL_FOTO_DOSEN + sessionManager.getSessionDosenFoto()).into(imageView);

    }


    private void initContentView(){
        textView_nama.setText(sessionManager.getSessionDosenNama());
        textView_nip.setText(sessionManager.getSessionDosenNip());
        textView_kode.setText(sessionManager.getSessionDosenKode());
        textView_email.setText(sessionManager.getSessionDosenEmail());
        textView_kontak.setText(sessionManager.getSessionDosenKontak());
        textView_batas_bimbingan.setText(String.valueOf(sessionManager.getSessionDosenBatasBimbingan()));
        textView_batas_reviewer.setText(String.valueOf(sessionManager.getSessionDosenBatasReviewer()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initContentView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
        } else if (i == R.id.toolbar_menu_hanya_ubah) {
            Intent intentUbah = new Intent(DosenProfilActivity.this, DosenProfilUbahActivity.class);
            startActivity(intentUbah);
        }

        return super.onOptionsItemSelected(item);
    }

}
