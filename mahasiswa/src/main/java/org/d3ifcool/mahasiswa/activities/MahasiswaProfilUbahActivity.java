package org.d3ifcool.mahasiswa.activities;

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

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.base.helpers.SessionManager;
import org.d3ifcool.base.interfaces.lists.MahasiswaListView;
import org.d3ifcool.base.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.base.models.Mahasiswa;
import org.d3ifcool.base.presenters.MahasiswaPresenter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class MahasiswaProfilUbahActivity extends AppCompatActivity implements MahasiswaWorkView , MahasiswaListView{

    private ProgressDialog progressDialog;
    private SessionManager sessionManager;
    private MahasiswaPresenter mahasiswaPresenter;
    private ArrayList<Mahasiswa> mahasiswaArrayList = new ArrayList<>();

    private String nama_baru, angkatan_baru, kontak_baru, email_baru, foto_baru;
    private String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private String phonePattern ="^(^\\+62\\s?|^0)(\\d{3,4}-?){2}\\d{3,4}$";
    private static final int REQUEST_SELECT_IMAGE = 1;
    private String fileImagePath = "";
    private String imagePath = "";
    private Uri imageFile;
    private TextView tv_hasil;
    private CircleImageView mahasiswa_foto;
    private EditText et_nama, et_angkatan, et_email, et_kontak;

    private String nama,angkatan,kontak,email;

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
        mahasiswaPresenter = new MahasiswaPresenter(this,this);
        mahasiswaPresenter.getMahasiswa();

        TextView tv_nim = findViewById(R.id.act_mhs_profil_nim);
        et_nama = findViewById(R.id.act_mhs_profil_nama);
        et_angkatan= findViewById(R.id.act_mhs_profil_angkatan);
        et_email = findViewById(R.id.act_mhs_profil_email);
        et_kontak = findViewById(R.id.act_mhs_profil_kontak);
        mahasiswa_foto = findViewById(R.id.act_mhs_profil_foto);
        tv_hasil = findViewById(R.id.act_mhs_profil_hasil);

        nama = sessionManager.getSessionMahasiswaNama();
        angkatan = sessionManager.getSessionMahasiswaAngkatan();
        kontak = sessionManager.getSessionMahasiswaKontak();
        email = sessionManager.getSessionMahasiswaEmail();

        tv_nim.setText(sessionManager.getSessionMahasiswaNim());
        et_nama.setText(nama);
        et_angkatan.setText(angkatan);
        et_kontak.setText(kontak);
        et_email.setText(email);
        Picasso.get().load(URL_FOTO_MAHASISWA + sessionManager.getSessionMahasiswaFoto()).into(mahasiswa_foto);

        mahasiswa_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(MahasiswaProfilUbahActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    Intent intentGallery = new Intent(Intent.ACTION_PICK);
                    intentGallery.setType("image/*");
                    if (intentGallery.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(Intent.createChooser(intentGallery, "SELECT IMAGE"), REQUEST_SELECT_IMAGE);
                    } else {
//                        Toast.makeText(mContext, "You Don't Have Pick Image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    ActivityCompat.requestPermissions(MahasiswaProfilUbahActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_SELECT_IMAGE);
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
                    String hasil = file.getName();
                    tv_hasil.setText(hasil);
                    mahasiswa_foto.setImageURI(imageFile);
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
        getMenuInflater().inflate(R.menu.menu_accept, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            finish();
        } else if (itemId == R.id.toolbar_menu_ubah_yes) {
            nama_baru = et_nama.getText().toString();
            angkatan_baru = et_angkatan.getText().toString();
            kontak_baru = et_kontak.getText().toString();
            foto_baru = tv_hasil.getText().toString();
            email_baru = et_email.getText().toString();

            if (nama_baru.isEmpty()){
                et_nama.setError(getString(R.string.text_tidak_boleh_kosong));
            }else if (angkatan_baru.isEmpty()){
                et_angkatan.setError(getString(R.string.text_tidak_boleh_kosong));
            } else if (kontak_baru.length() <= 10 ){
                et_kontak.setError(getString(R.string.validate_telp_jumlah_kurang));
            }else if (kontak_baru.length() >= 13){
                et_kontak.setError(getString(R.string.validate_telp_jumlah_lebih));
            } else if (!email_baru.matches(emailPattern)){
                et_email.setError(getString(R.string.validate_email));
            }else if (!kontak_baru.matches(phonePattern)){
                et_kontak.setError(getString(R.string.validate_telp_valid));
            } else if (!kontak_baru.isEmpty()) {
                for (int i =0 ; i < mahasiswaArrayList.size() ; i++){
                    if (kontak_baru.equalsIgnoreCase(kontak)){
                        mahasiswaPresenter.updateMahasiswa(sessionManager.getSessionMahasiswaNim(), nama_baru, angkatan_baru, kontak_baru,foto_baru, email_baru);
                    }else if (kontak_baru.equalsIgnoreCase(mahasiswaArrayList.get(i).getMhs_kontak())){
                        et_kontak.setError(getString(R.string.validate_telp_sudah_ada));
                    }
                }
            }else if (!email_baru.isEmpty()){
                for (int i =0 ; i < mahasiswaArrayList.size() ; i++){
                    if (email_baru.equalsIgnoreCase(email)){
                        mahasiswaPresenter.updateMahasiswa(sessionManager.getSessionMahasiswaNim(), nama_baru, angkatan_baru, kontak_baru,foto_baru, email_baru);
                    }else if (email_baru.equalsIgnoreCase(mahasiswaArrayList.get(i).getMhs_email())){
                        et_email.setError(getString(R.string.validate_email_ada));
                    }
                }

            } else {
                mahasiswaPresenter.updateMahasiswa(sessionManager.getSessionMahasiswaNim(), nama_baru, angkatan_baru, kontak_baru,foto_baru, email_baru);
            }
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
    public void onGetListMahasiswa(List<Mahasiswa> mahasiswa) {
        mahasiswaArrayList.clear();
        mahasiswaArrayList.addAll(mahasiswa);
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