package org.d3ifcool.dosen.activities.details;

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

import org.d3ifcool.dosen.R;
import org.d3ifcool.dosen.activities.editors.DosenJudulPaSubdosenUbahActivity;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.presenters.JudulPresenter;

public class DosenJudulPaSubdosenDetailActivity extends AppCompatActivity implements JudulWorkView {
    public static final String EXTRA_INFORMASI = "extra_informasi";
    private TextView tv_judul,tv_kategori,tv_deskripsi;
    private Judul extradata;
    private JudulPresenter presenter;
    private ProgressDialog dialog;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_detail);

        tv_judul = findViewById(R.id.frg_dsn_pa_textview_judulpa);
        tv_kategori = findViewById(R.id.frg_dsn_pa_textview_kategoripa);
        tv_deskripsi = findViewById(R.id.frg_dsn_pa_textview_deskripsi);


        extradata = getIntent().getParcelableExtra(EXTRA_INFORMASI);
        String judul = extradata.getJudul();
//        String kategori = extradata.getKategori();
        String deskripsi = extradata.getDeskripsi();

        tv_judul.setText(judul);
//        tv_kategori.setText(kategori);
        tv_deskripsi.setText(deskripsi);

        presenter = new JudulPresenter(this);
        dialog = new ProgressDialog(this);
        sessionManager = new SessionManager(this);
        dialog.setMessage(getString(R.string.text_progress_dialog));

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        String nip_dosen = extradata.getNip_dosen();
        if(nip_dosen.equalsIgnoreCase(sessionManager.getSessionDosenNip())) {
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
            Intent intentUbah = new Intent(DosenJudulPaSubdosenDetailActivity.this, DosenJudulPaSubdosenUbahActivity.class);
            Judul parcelinfo = extradata;
            intentUbah.putExtra(DosenJudulPaSubdosenUbahActivity.EXTRA_INFORMASI, parcelinfo);
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
                            presenter.deleteJudul(extradata.getId());
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


