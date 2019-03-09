package org.d3ifcool.dosen.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenProfilUbahActivity;
import org.d3ifcool.service.helpers.SessionManager;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class DosenProfilActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_profil);
        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        sessionManager = new SessionManager(this);

        TextView textView_nama = findViewById(R.id.act_dsn_profil_nama);
        TextView textView_nip = findViewById(R.id.act_dsn_profil_nip);
        TextView textView_email = findViewById(R.id.act_dsn_profil_email);
        TextView textView_kontak = findViewById(R.id.act_dsn_profil_kontak);
        CircleImageView imageView = findViewById(R.id.act_dsn_profil_foto);

        textView_nama.setText(sessionManager.getSessionDosenNamaD());
        textView_nip.setText(sessionManager.getSessionDosenNip());
        textView_email.setText(sessionManager.getSessionDosenEmail());
        textView_kontak.setText(sessionManager.getSessionDosenKontak());
        Picasso.get().load(URL_FOTO_DOSEN + sessionManager.getSessionDosenFoto()).into(imageView);

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

        } else {
        }
        return super.onOptionsItemSelected(item);
    }

}
