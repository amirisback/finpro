package org.d3ifcool.dosen.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.dosen.R;
import org.d3ifcool.base.helpers.SessionManager;
import org.d3ifcool.base.interfaces.lists.DosenListView;
import org.d3ifcool.base.interfaces.works.DosenWorkView;
import org.d3ifcool.base.models.Dosen;
import org.d3ifcool.base.presenters.DosenPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class DosenProfilUbahActivity extends AppCompatActivity implements DosenWorkView, DosenListView {

    private DosenPresenter dosenPresenter;
    private SessionManager sessionManager;
    private ProgressDialog progressDialog;

    private String nama_baru, kode_baru, kontak_baru, email_baru,foto_baru;
    private String hasil;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String phonePattern ="^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$";

    private static final int REQUEST_SELECT_IMAGE = 1;
    String fileImagePath = "";
    String imagePath = "";
    private Uri imageFile;
    private TextView tv_hasil;
    private CircleImageView dosen_foto;

    private ArrayList<Dosen> dosens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_profil_ubah);

        setTitle(getString(R.string.title_profil_ubah));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        dosenPresenter = new DosenPresenter(this, this);
        sessionManager = new SessionManager(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        final EditText et_nama = findViewById(R.id.act_dsn_profil_nama);
        TextView et_nip = findViewById(R.id.act_dsn_profil_nip);
        final EditText et_kode = findViewById(R.id.act_dsn_profil_kode);
        final EditText et_email = findViewById(R.id.act_dsn_profil_email);
        final EditText et_kontak = findViewById(R.id.act_dsn_profil_kontak);

        dosen_foto = findViewById(R.id.act_dsn_profil_foto);
        Picasso.get().load(URL_FOTO_DOSEN + sessionManager.getSessionDosenFoto()).into(dosen_foto);

        tv_hasil = findViewById(R.id.act_dsn_profil_hasil);
        Button btn_ubah = findViewById(R.id.act_dsn_profil_button);

        String nama = sessionManager.getSessionDosenNama();
        String kode = sessionManager.getSessionDosenKode();
        final String email = sessionManager.getSessionDosenEmail();
        final String kontak = sessionManager.getSessionDosenKontak();

        et_nama.setText(nama);
        et_nip.setText(sessionManager.getSessionDosenNip());
        et_kode.setText(kode);
        et_email.setText(email);
        et_kontak.setText(kontak);

        dosenPresenter.getDosen();
        dosen_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(DosenProfilUbahActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intentGallery = new Intent(Intent.ACTION_PICK);
                    intentGallery.setType("image/*");
                    if (intentGallery.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(Intent.createChooser(intentGallery, "SELECT IMAGE"), REQUEST_SELECT_IMAGE);
                    } else {
//                        Toast.makeText(mContext, "You Don't Have Pick Image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ActivityCompat.requestPermissions(DosenProfilUbahActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_SELECT_IMAGE);
                }
            }
        });

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nama_baru = et_nama.getText().toString();
                kode_baru = et_kode.getText().toString();
                email_baru = et_email.getText().toString();
                kontak_baru = et_kontak.getText().toString();
                foto_baru = tv_hasil.getText().toString();

                if (nama_baru.isEmpty()){
                    et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
                }else if (kode_baru.isEmpty()){
                    et_kode.setError(getString(R.string.text_tidak_boleh_kosong));
                } else if (kontak_baru.length() <= 10 ){
                    et_kontak.setError(getString(R.string.validate_telp_jumlah_kurang));
                }else if (kontak_baru.length() >= 13){
                    et_kontak.setError(getString(R.string.validate_telp_jumlah_lebih));
                } else if (!email_baru.matches(emailPattern)){
                    et_email.setError(getString(R.string.validate_email));
                }else if (!kontak_baru.matches(phonePattern)){
                    et_kontak.setError(getString(R.string.validate_telp_valid));
                } else if (!kontak_baru.isEmpty()) {
                    for (int i =0 ; i < dosens.size() ; i++){
                        if (kontak_baru.equalsIgnoreCase(kontak)){
                            dosenPresenter.updateDosen(sessionManager.getSessionDosenNip(), nama_baru,kode_baru,kontak_baru,foto_baru,email_baru);
                        }else if (kontak_baru.equalsIgnoreCase(dosens.get(i).getDsn_kontak())){
                            et_kontak.setError(getString(R.string.validate_telp_sudah_ada));
                        }
                    }
                }else if (!email_baru.isEmpty()){
                    for (int i =0 ; i < dosens.size() ; i++){
                        if (email_baru.equalsIgnoreCase(email)){
                            dosenPresenter.updateDosen(sessionManager.getSessionDosenNip(), nama_baru,kode_baru,kontak_baru,foto_baru,email_baru);
                        }else if (email_baru.equalsIgnoreCase(dosens.get(i).getDsn_email())){
                            et_email.setError(getString(R.string.validate_email_ada));
                        }
                    }

                } else {
                    dosenPresenter.updateDosen(sessionManager.getSessionDosenNip(), nama_baru,kode_baru,kontak_baru,foto_baru,email_baru);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SELECT_IMAGE && resultCode == RESULT_OK && null != data){
            imageFile = data.getData();

            String[] projection = {MediaStore.Images.Media.DATA};

            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M || android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
                Cursor cursor = getContentResolver().query(imageFile, projection, null, null, null);
                if (cursor!=null){

                    cursor.moveToFirst();
                    int index = cursor.getColumnIndex(projection[0]);
                    fileImagePath = cursor.getString(index);

                    File file = new File(fileImagePath);
                     hasil= file.getName();
                    tv_hasil.setText(hasil);
                    dosen_foto.setImageURI(imageFile);
                    cursor.close();
                } else {
                    fileImagePath = imageFile.getPath();
                }
            } else {
                if (imageFile!=null){
                    imagePath = imageFile.getPath();
                    File file = new File(imagePath);
                    String result = file.getName();
                    tv_hasil.setText(result);
                } else {
                    imageFile = Uri.EMPTY;
                }
            }
        }
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
    public void onGetListDosen(List<Dosen> dosen) {
        dosens.clear();
        dosens.addAll(dosen);
    }

    @Override
    public void onSucces() {
        sessionManager.updateSessionDosen(nama_baru, kode_baru, kontak_baru, email_baru);
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
