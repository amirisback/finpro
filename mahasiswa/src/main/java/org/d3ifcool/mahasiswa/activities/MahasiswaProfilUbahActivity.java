package org.d3ifcool.mahasiswa.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.service.presenters.MahasiswaPresenter;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.service.networks.bridge.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilUbahActivity extends AppCompatActivity implements MahasiswaWorkView {

    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private MahasiswaPresenter mahasiswaPresenter;

    private String nama_baru, angkatan_baru, kontak_baru, email_baru;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_profil_ubah);

        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        sessionManager = new SessionManager(this);
        mahasiswaPresenter = new MahasiswaPresenter(this);

        TextView tv_nim = findViewById(R.id.act_mhs_profil_nim);
        final EditText et_nama = findViewById(R.id.act_mhs_profil_nama);
        final EditText et_angkatan= findViewById(R.id.act_mhs_profil_angkatan);
        final EditText et_email = findViewById(R.id.act_mhs_profil_email);
        final EditText et_kontak = findViewById(R.id.act_mhs_profil_kontak);
        final CircleImageView mahasiswa_foto = findViewById(R.id.act_mhs_profil_foto);

        final String nama = sessionManager.getSessionMahasiswaNama();
        final String angkatan = sessionManager.getSessionMahasiswaAngkatan();
        String kontak = sessionManager.getSessionMahasiswaKontak();
        final String email = sessionManager.getSessionMahasiswaEmail();

        tv_nim.setText(sessionManager.getSessionMahasiswaNim());
        et_nama.setText(nama);
        et_angkatan.setText(angkatan);
        et_kontak.setText(kontak);
        et_email.setText(email);
        Picasso.get().load(URL_FOTO_MAHASISWA + sessionManager.getSessionMahasiswaFoto()).into(mahasiswa_foto);

        Button btn_ubah = findViewById(R.id.act_mhs_profil_button);
        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_baru = et_nama.getText().toString();
                angkatan_baru = et_angkatan.getText().toString();
                kontak_baru = et_kontak.getText().toString();
                email_baru = et_email.getText().toString();
                if (nama_baru.isEmpty()){
                    et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (angkatan_baru.isEmpty()){
                    et_angkatan.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (kontak_baru.length() < 7 ){
                    et_kontak.setError(getString(R.string.no_telp_kurang));
                }else if (kontak_baru.length() > 12){
                    et_kontak.setError(getString(R.string.no_lebih));
                } else if (!email_baru.matches(emailPattern)){
                    et_email.setError(getString(R.string.email_tidak_valid));
                } else {
                    mahasiswaPresenter.updateMahasiswa(sessionManager.getSessionMahasiswaNim(), nama_baru, angkatan_baru, kontak_baru, email_baru);
                }
            }
        });


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
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void onSucces() {
        sessionManager.updateSessionMahasiswa(nama_baru, angkatan_baru, kontak_baru, email_baru);
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}