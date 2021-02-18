package org.d3ifcool.finpro.mahasiswa.activities.editor;


import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EMAIL_PATTERN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PHONE_PATTERN;
import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilUbahActivity extends AppCompatActivity implements MahasiswaWorkView{

    private ProgressDialog progressDialog;
    private SessionManager sessionManager;

    private MahasiswaPresenter mahasiswaPresenter;

    private String nama_baru, angkatan_baru, kontak_baru, email_baru;
    private EditText et_nama, et_email, et_kontak;

    private TextView et_angkatan;


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
        mahasiswaPresenter.initContext(this);

        TextView tv_nim = findViewById(R.id.act_mhs_profil_nim);
        et_nama = findViewById(R.id.act_mhs_profil_nama);
        et_angkatan= findViewById(R.id.act_mhs_profil_angkatan);
        et_email = findViewById(R.id.act_mhs_profil_email);
        et_kontak = findViewById(R.id.act_mhs_profil_kontak);
        CircleImageView mahasiswa_foto = findViewById(R.id.act_mhs_profil_foto);

        String nama = sessionManager.getSessionMahasiswaNama();
        String angkatan = sessionManager.getSessionMahasiswaAngkatan();
        String kontak = sessionManager.getSessionMahasiswaKontak();
        String email = sessionManager.getSessionMahasiswaEmail();
        String nim = sessionManager.getSessionMahasiswaNim();

        tv_nim.setText(nim);
        et_nama.setText(nama);
        et_angkatan.setText(angkatan);
        et_kontak.setText(kontak);
        et_email.setText(email);
        Picasso.get().load(URL_FOTO_MAHASISWA + sessionManager.getSessionMahasiswaFoto()).into(mahasiswa_foto);
    }


    private void updateData(){
        nama_baru = et_nama.getText().toString();
        angkatan_baru = et_angkatan.getText().toString();
        String temp_kontak_baru = et_kontak.getText().toString();
        String temp_email_baru = et_email.getText().toString();

        if (nama_baru.isEmpty()){
            et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
        }else if (angkatan_baru.isEmpty()){
            et_angkatan.setError(getString(R.string.text_tidak_boleh_kosong));
        } else {

            if (!temp_kontak_baru.isEmpty()) {
                if (!temp_kontak_baru.matches(PHONE_PATTERN)) {
                    et_kontak.setError(getString(R.string.validate_telp_valid));
                } else {
                    if (temp_kontak_baru.length() <= 10) {
                        et_kontak.setError(getString(R.string.validate_telp_jumlah_kurang));
                    } else if (temp_kontak_baru.length() >= 13) {
                        et_kontak.setError(getString(R.string.validate_telp_jumlah_lebih));
                    } else {
                        kontak_baru = temp_kontak_baru;
                    }
                }
            }

            if (!temp_email_baru.isEmpty()){
                if (!temp_email_baru.matches(EMAIL_PATTERN)){
                    et_email.setError(getString(R.string.validate_email));
                } else {
                    email_baru = temp_email_baru;
                }
            }

            mahasiswaPresenter.updateMahasiswa(sessionManager.getSessionMahasiswaNim(), nama_baru, angkatan_baru, kontak_baru, email_baru);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         int itemId = item.getItemId();

        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.toolbar_menu_ubah_yes) {
            updateData();
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