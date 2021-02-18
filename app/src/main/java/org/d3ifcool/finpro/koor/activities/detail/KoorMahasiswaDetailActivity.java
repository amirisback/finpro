package org.d3ifcool.finpro.koor.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.koor.activities.editor.update.KoorMahasiswaUbahActivity;
import org.d3ifcool.finpro.R;

import de.hdodenhof.circleimageview.CircleImageView;

import static org.d3ifcool.finpro.core.api.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class KoorMahasiswaDetailActivity extends AppCompatActivity implements MahasiswaWorkView {

    public static final String EXTRA_MAHASISWA = "extra_mahasiswa";

    private Mahasiswa extraMahasiswa;
    private MahasiswaPresenter mahasiswaPresenter;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_mahasiswa_detail);

        setTitle(getString(R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        mahasiswaPresenter = new MahasiswaPresenter(this);
        mahasiswaPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        TextView tv_nama = findViewById(R.id.act_koor_profil_nama_mhs);
        TextView tv_nim = findViewById(R.id.act_koor_profil_nim);
        TextView tv_kontak = findViewById(R.id.act_koor_profil_kontak_mhs);
        TextView tv_email = findViewById(R.id.act_koor_profil_email_mhs);
        TextView tv_angkatan = findViewById(R.id.act_koor_profil_angkatan_mhs);
        TextView tv_judul = findViewById(R.id.act_koor_profil_judul_mhs);
        CircleImageView circleImageView = findViewById(R.id.act_koor_profil_foto_mhs);

        extraMahasiswa = getIntent().getParcelableExtra(EXTRA_MAHASISWA);
        String nim = extraMahasiswa.getMhs_nim();
        String nama = extraMahasiswa.getMhs_nama();
        String kontak = extraMahasiswa.getMhs_kontak();
        String email = extraMahasiswa.getMhs_email();
        String angkatan = extraMahasiswa.getAngkatan();
        String judul = extraMahasiswa.getJudul_nama();
        String path = extraMahasiswa.getMhs_foto();

        tv_nama.setText(nama);
        tv_nim.setText(nim);
        tv_email.setText(email);
        tv_kontak.setText(kontak);
        tv_angkatan.setText(angkatan);
        tv_judul.setText(judul);
        Picasso.get().load(URL_FOTO_MAHASISWA + path).into(circleImageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intent = new Intent(this, KoorMahasiswaUbahActivity.class);
            intent.putExtra(KoorMahasiswaUbahActivity.EXTRA_MAHASISWA, extraMahasiswa);
            startActivity(intent);
            finish();
        } else if (i == R.id.toolbar_menu_hapus) {
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            mahasiswaPresenter.deleteMahasiswa(extraMahasiswa.getMhs_nim());
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
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
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
