package org.d3ifcool.finpro.dosen.activities.detail;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.activities.editor.update.DosenMonevUbahActivity;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.works.MonevDetailWorkView;
import org.d3ifcool.finpro.core.models.DetailMonev;
import org.d3ifcool.finpro.core.presenters.MonevDetailPresenter;

public class DosenMonevDetailActivity extends AppCompatActivity implements MonevDetailWorkView {

    public static final String EXTRA_MONEV = "extra_monev";

    private SessionManager sessionManager;
    private ProgressDialog progressDialog;
    private MonevDetailPresenter detailMonevPresenter;

    private String extraDsnNipReviewer;
    private int extraMonevDetailId;
    private DetailMonev extraDetailMonev;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_monev_detail);

        setTitle(getString(R.string.title_monev_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        detailMonevPresenter = new MonevDetailPresenter(this);
        detailMonevPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        TextView textViewTanggal = findViewById(R.id.act_dsn_monev_tanggal);
        TextView textViewKategori = findViewById(R.id.act_dsn_monev_kategori);
        TextView textViewUlasan = findViewById(R.id.act_dsn_monev_ulasan);
        TextView textViewNilai = findViewById(R.id.act_dsn_monev_nilai);

        extraDetailMonev = getIntent().getParcelableExtra(EXTRA_MONEV);
        String extraDetailMonevTanggal = extraDetailMonev.getMonev_tanggal();
        String extraDetailMonevKategori = extraDetailMonev.getMonev_kategori();
        String extraDetailMonevUlasan = extraDetailMonev.getMonev_ulasan();
        int extraDetailMonevNilai = extraDetailMonev.getMonev_nilai();
        extraDsnNipReviewer = extraDetailMonev.getDsn_nip();
        extraMonevDetailId = extraDetailMonev.getMonev_detail_id();

        textViewTanggal.setText(extraDetailMonevTanggal);
        textViewKategori.setText(extraDetailMonevKategori);
        textViewUlasan.setText(extraDetailMonevUlasan);
        textViewNilai.setText(String.valueOf(extraDetailMonevNilai));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (sessionManager.getSessionDosenNip().equalsIgnoreCase(extraDsnNipReviewer)) {
            getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intentUbah = new Intent(DosenMonevDetailActivity.this, DosenMonevUbahActivity.class);
            intentUbah.putExtra(DosenMonevUbahActivity.EXTRA_DETAIL_MONEV, extraDetailMonev);
            startActivity(intentUbah);
            finish();

        } else if (i == R.id.toolbar_menu_hapus) {

            new AlertDialog
                    .Builder(this)
                    .setTitle(getString(R.string.dialog_hapus_title))
                    .setMessage(getString(R.string.dialog_hapus_text))

                    .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            detailMonevPresenter.deleteMonevDetail(extraMonevDetailId);
                        }
                    })

                    .setNegativeButton(R.string.tidak, null)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();

        } else {
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
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
