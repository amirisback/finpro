package org.d3ifcool.koor.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

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

import org.d3ifcool.base.interfaces.works.DosenWorkView;
import org.d3ifcool.base.models.Dosen;
import org.d3ifcool.base.presenters.DosenPresenter;
import org.d3ifcool.koor.R;
import org.d3ifcool.koor.activities.editor.update.KoorDosenUbahActivity;

import static org.d3ifcool.base.networks.bridge.ApiUrl.FinproUrl.URL_FOTO_DOSEN;

public class KoorDosenDetailActivity extends AppCompatActivity implements DosenWorkView {

    public static final String EXTRA_DOSEN = "extra_dosen";
    private Dosen extraDosen;
    private DosenPresenter dosenPresenter;
    private ProgressDialog progressDialog;
    private TextView tv_nama, tv_kode, tv_nip, tv_kontak, tv_email, tv_batas_bimbingan, tv_batas_reviewer;
    private CircleImageView circleImageView;
    private String nip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_dosen_detail);

        setTitle(getString(R.string.title_dosen_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        dosenPresenter = new DosenPresenter(this);
        dosenPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        tv_nama = findViewById(R.id.act_koor_dosen_nama);
        tv_kode = findViewById(R.id.act_koor_dosen_kode);
        tv_nip = findViewById(R.id.act_koor_dosen_nip);
        tv_kontak = findViewById(R.id.act_koor_dosen_kontak);
        tv_email = findViewById(R.id.act_koor_dosen_email);
        tv_batas_bimbingan = findViewById(R.id.act_koor_dosen_batas_bimbingan);
        tv_batas_reviewer = findViewById(R.id.act_koor_dosen_batas_reviewer);
        circleImageView = findViewById(R.id.act_koor_profil_foto_dosen);

        extraDosen = getIntent().getParcelableExtra(EXTRA_DOSEN);
        String nama = extraDosen.getDsn_nama();
        String kode = extraDosen.getDsn_kode();
        nip = extraDosen.getDsn_nip();
        String email = extraDosen.getDsn_email();
        String kontak = extraDosen.getDsn_kontak();
        String path = extraDosen.getDsn_foto();
        int batas_bimbingan = extraDosen.getBatas_bimbingan();
        int batas_reviewer = extraDosen.getBatas_reviewer();

        tv_nama.setText(nama);
        tv_kode.setText(kode);
        tv_nip.setText(nip);
        tv_email.setText(email);
        tv_kontak.setText(kontak);
        tv_batas_bimbingan.setText(String.valueOf(batas_bimbingan));
        tv_batas_reviewer.setText(String.valueOf(batas_reviewer));
        Picasso.get().load(URL_FOTO_DOSEN + path).into(circleImageView);

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
            Intent intentUbah = new Intent(KoorDosenDetailActivity.this, KoorDosenUbahActivity.class);
            intentUbah.putExtra(KoorDosenUbahActivity.EXTRA_DOSEN, extraDosen);
            startActivity(intentUbah);
            finish();
        } else if (i == R.id.toolbar_menu_hapus) {
            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Continue with delete operation
                            dosenPresenter.deleteDosen(nip);
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
