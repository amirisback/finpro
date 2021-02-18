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

import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.koor.activities.editor.update.KoorJudulPaSubdosenUbahActivity;
import org.d3ifcool.finpro.R;

public class KoorJudulPaSubdosenDetailActivity extends AppCompatActivity implements JudulWorkView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private JudulPresenter judulPresenter;
    private ProgressDialog progressDialog;

    private int extraJudulId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_koor_judul_pa_subdosen_detail);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        judulPresenter = new JudulPresenter(this);
        judulPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulKategori = extraJudul.getKategori_nama();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        extraJudulId = extraJudul.getId();

        TextView textViewJudul = findViewById(R.id.frg_koor_pa_textview_judulpa);
        TextView textViewKategori = findViewById(R.id.frg_koor_pa_textview_kategoripa);
        TextView textViewDeskripsi = findViewById(R.id.frg_dsn_pa_textview_deskripsi);

        textViewJudul.setText(extraJudulNama);
        textViewKategori.setText(extraJudulKategori);
        textViewDeskripsi.setText(extraJudulDeskripsi);

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

        } else if (i == R.id.toolbar_menu_ubah) {
            Intent intent = new Intent(KoorJudulPaSubdosenDetailActivity.this, KoorJudulPaSubdosenUbahActivity.class);
            intent.putExtra(KoorJudulPaSubdosenUbahActivity.EXTRA_JUDUL, String.valueOf(getIntent().getParcelableExtra(EXTRA_JUDUL)));
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
                            judulPresenter.deleteJudul(extraJudulId);
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
        progressDialog.show();
    }

    @Override
    public void onSuccesWorkJudul() {
        finish();
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
