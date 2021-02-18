package org.d3ifcool.finpro.koor.activities.editor.update;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.KoorWorkView;
import org.d3ifcool.finpro.core.presenters.KoorPresenter;
import org.d3ifcool.finpro.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.EMAIL_PATTERN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.PHONE_PATTERN;
import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_KOOR;

public class KoorProfilUbahActivity extends AppCompatActivity implements KoorWorkView {

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private KoorPresenter koorPresenter;

    private String nip_baru, nama_baru, kode_baru, kontak_baru, email_baru;
    private EditText et_nama, et_kode, et_email, et_kontak, et_nip;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_profil_ubah);

        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        koorPresenter = new KoorPresenter(this);
        koorPresenter.initContext(this);

        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        et_nama = findViewById(R.id.act_koor_profil_nama);
        et_kode = findViewById(R.id.act_koor_profil_kode);
        et_email = findViewById(R.id.act_koor_profil_email);
        et_kontak = findViewById(R.id.act_koor_profil_kontak);
        et_nip = findViewById(R.id.act_koor_profil_nip);

        CircleImageView koor_foto = findViewById(R.id.act_koor_profil_foto);

        Picasso.get().load(URL_FOTO_KOOR + sessionManager.getSessionKoorFoto()).into(koor_foto);

        String nama = sessionManager.getSessionKoorNama();
        String kode = sessionManager.getSessionKoorKode();
        String email = sessionManager.getSessionKoorEmail();
        String kontak = sessionManager.getSessionKoorKontak();
        String nip = sessionManager.getSessionKoorNip();

        et_nip.setText(nip);
        et_nama.setText(nama);
        et_kode.setText(kode);
        et_email.setText(email);
        et_kontak.setText(kontak);

    }

    private void updateData(){
        nip_baru = et_nip.getText().toString();
        nama_baru = et_nama.getText().toString();
        kode_baru = et_kode.getText().toString();
        String temp_email_baru = et_email.getText().toString();
        String temp_kontak_baru = et_kontak.getText().toString();

        if (nama_baru.isEmpty()){
            et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
        }else if (kode_baru.isEmpty()){
            et_kode.setError(getString(R.string.text_tidak_boleh_kosong));
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

            koorPresenter.updateKoor(sessionManager.getSessionUsername(), nip_baru, nama_baru, kode_baru,kontak_baru, email_baru);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }else if (item.getItemId() == R.id.toolbar_menu_ubah_yes) {
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
        progressDialog.hide();
    }

    @Override
    public void onSucces() {
        sessionManager.updateSessionKoor(nip_baru, nama_baru, kode_baru,kontak_baru, email_baru);
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
