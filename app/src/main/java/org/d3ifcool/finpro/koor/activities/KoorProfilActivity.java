package org.d3ifcool.finpro.koor.activities;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.koor.activities.editor.update.KoorProfilUbahActivity;
import org.d3ifcool.finpro.R;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_KOOR;

public class KoorProfilActivity extends AppCompatActivity {

    private TextView textView_nama, textView_nip, textView_kode, textView_email, textView_kontak;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_profil);

        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        sessionManager = new SessionManager(this);

        textView_nama = findViewById(R.id.act_dsn_profil_nama);
        textView_nip = findViewById(R.id.act_dsn_profil_nip);
        textView_kode = findViewById(R.id.act_dsn_profil_kode);
        textView_email = findViewById(R.id.act_dsn_profil_email);
        textView_kontak = findViewById(R.id.act_dsn_profil_kontak);
        CircleImageView imageView = findViewById(R.id.act_dsn_profil_foto);


        Picasso.get().load(URL_FOTO_KOOR + sessionManager.getSessionKoorFoto()).into(imageView);

    }

    private void initContentView(){
        textView_nama.setText(sessionManager.getSessionKoorNama());
        textView_nip.setText(sessionManager.getSessionKoorNip());
        textView_kode.setText(sessionManager.getSessionKoorKode());
        textView_email.setText(sessionManager.getSessionKoorEmail());
        textView_kontak.setText(sessionManager.getSessionKoorKontak());
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
            Intent intentUbah = new Intent(KoorProfilActivity.this, KoorProfilUbahActivity.class);
            startActivity(intentUbah);
        }
        return super.onOptionsItemSelected(item);
    }

}
