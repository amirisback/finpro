package org.d3ifcool.mahasiswa.activities;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.helpers.SessionManager;

import static org.d3ifcool.service.network.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilActivity extends AppCompatActivity {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_profil);

        setTitle(getString(R.string.title_profil));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        sessionManager = new SessionManager(this);

        TextView mahasiswa_nama = findViewById(R.id.act_mhs_profil_nama);
        TextView mahasiswa_nim = findViewById(R.id.act_mhs_profil_nim);
        TextView mahasiswa_email = findViewById(R.id.act_mhs_profil_email);
        TextView mahasiswa_kontak = findViewById(R.id.act_mhs_profil_kontak);
        CircleImageView mahasiswa_foto = findViewById(R.id.act_mhs_profil_foto);

        mahasiswa_nama.setText(sessionManager.getSessionMahasiswaNamaM());
        mahasiswa_nim.setText(sessionManager.getSessionMahasiswaNim());
        mahasiswa_email.setText(sessionManager.getSessionMahasiswaEmailM());
        mahasiswa_kontak.setText(sessionManager.getSessionMahasiswaKontakM());
        Picasso.get().load(URL_FOTO_MAHASISWA + sessionManager.getSessionMahasiswaFotoM()).into(mahasiswa_foto);

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
            Intent intentUbah = new Intent(MahasiswaProfilActivity.this, MahasiswaProfilUbahActivity.class);
            startActivity(intentUbah);

        } else {
        }
        return super.onOptionsItemSelected(item);
    }

}
