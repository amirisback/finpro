package org.d3ifcool.mahasiswa.activities;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.helpers.SessionManager;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private CircleImageView mahasiswa_foto;
    private static final int PERMISSION_CODE =1;
    private static final int IMAGE_PICK_CODE =2;

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
         mahasiswa_foto = findViewById(R.id.act_mhs_profil_foto);

        mahasiswa_nama.setText(sessionManager.getSessionMahasiswaNamaM());
        mahasiswa_nim.setText(sessionManager.getSessionMahasiswaNim());
        mahasiswa_email.setText(sessionManager.getSessionMahasiswaEmailM());
        mahasiswa_kontak.setText(sessionManager.getSessionMahasiswaKontakM());
        Picasso.get().load(URL_FOTO_MAHASISWA + sessionManager.getSessionMahasiswaFotoM()).into(mahasiswa_foto);

        mahasiswa_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, IMAGE_PICK_CODE);
            }
        });

    }

    private void pickImageFromGallery() {
        Intent galeri = new Intent(Intent.ACTION_PICK);
        galeri.setType("image/*");
        startActivityForResult(galeri, IMAGE_PICK_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_CODE:{
                if (grantResults.length > 0 && grantResults[0] ==
                        PackageManager.PERMISSION_GRANTED){
                    pickImageFromGallery();
                }
                else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == IMAGE_PICK_CODE){
            mahasiswa_foto.setImageURI(data.getData());
        }
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
