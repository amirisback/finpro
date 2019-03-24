package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.d3ifcool.dosen.activities.details.DosenBimbinganDetailActivity;
import org.d3ifcool.dosen.activities.editors.DosenBimbinganUbahActivity;
import org.d3ifcool.dosen.activities.editors.DosenInformasiUbahActivity;
import org.d3ifcool.service.interfaces.DosenViewEditor;
import org.d3ifcool.service.models.Dosen;
import org.d3ifcool.service.models.Informasi;
import org.d3ifcool.service.presenters.DosenPresenter;
import org.d3ifcool.service.presenters.InformasiPresenter;
import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorDosenUbahActivity;
import org.d3ifcool.superuser.activities.editors.KoorInformasiUbahActivity;

import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_FOTO_DOSEN;
import static org.d3ifcool.service.network.bridge.ApiUrl.FinproUrl.URL_FOTO_MAHASISWA;

public class KoorDosenDetailActivity extends AppCompatActivity implements DosenViewEditor {

    public static final String EXTRA_DOSEN = "extra_dosen";
    private Dosen extraDosen;
    private DosenPresenter presenter;
    private ProgressDialog dialog;
    private TextView tv_nama, tv_kode, tv_nip, tv_kontak, tv_email;
    private CircleImageView circleImageView;
    private String nip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_dosen_detail);

        setTitle(getString(R.string.title_dosen_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);

        presenter = new DosenPresenter(this, KoorDosenDetailActivity.this);
        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.progress_dialog));

        tv_nama = findViewById(R.id.act_koor_dosen_nama);
        tv_kode = findViewById(R.id.act_koor_dosen_kode);
        tv_nip = findViewById(R.id.act_koor_dosen_nip);
        tv_kontak = findViewById(R.id.act_koor_dosen_kontak);
        tv_email = findViewById(R.id.act_koor_dosen_email);
        circleImageView = findViewById(R.id.act_koor_profil_foto_dosen);

        extraDosen = getIntent().getParcelableExtra(EXTRA_DOSEN);
        String nama = extraDosen.getDsn_nama();
        String kode = extraDosen.getDsn_kode();
        nip = extraDosen.getDsn_nip();
        String email = extraDosen.getDsn_email();
        String kontak = extraDosen.getDsn_kontak();
        String path = extraDosen.getDsn_foto();

        tv_nama.setText(nama);
        tv_kode.setText(kode);
        tv_nip.setText(nip);
        tv_email.setText(email);
        tv_kontak.setText(kontak);
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
                            presenter.deleteDosen(nip);
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
        dialog.show();
    }

    @Override
    public void hideProgress() {
        dialog.dismiss();
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
