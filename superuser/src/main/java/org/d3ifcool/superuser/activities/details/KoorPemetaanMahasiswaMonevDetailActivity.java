package org.d3ifcool.superuser.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.superuser.R;
import org.d3ifcool.superuser.activities.editors.KoorDosenUbahActivity;
import org.d3ifcool.superuser.activities.editors.KoorInformasiUbahActivity;

public class KoorPemetaanMahasiswaMonevDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_pemetaan_mahasiswa_monev_detail);

        setTitle(getString(org.d3ifcool.dosen.R.string.title_mahasiswa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0f);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == android.R.id.home) {
            finish();

        } else if (i == org.d3ifcool.dosen.R.id.toolbar_menu_ubah) {
//            Intent intentUbah = new Intent(KoorInformasiDetailActivity.this, KoorInformasiUbahActivity.class);
//            intentUbah.putExtra(KoorInformasiUbahActivity.EXTRA_INFORMASI, extraInfo);
//            startActivity(intentUbah);
//            finish();

        } else if (i == org.d3ifcool.dosen.R.id.toolbar_menu_hapus) {
//            new AlertDialog
//                    .Builder(this)
//                    .setTitle(getString(R.string.dialog_hapus_title))
//                    .setMessage(getString(R.string.dialog_hapus_text))
//
//                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Continue with delete operation
//                            presenter.deleteInformasi(extraInfo.getId());
//                        }
//                    })
//
//                    .setNegativeButton(R.string.tidak, null)
//                    .setIcon(android.R.drawable.ic_dialog_alert)
//                    .show();
//
//        }
        }
            return super.onOptionsItemSelected(item);
        }
    }

