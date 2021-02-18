package org.d3ifcool.finpro.dosen.activities.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.works.ICreate;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_DISETUJUI;

public class DosenBimbinganDetailActivity extends AppCompatActivity implements ICreate, BimbinganListView{

    public static final String EXTRA_BIMBINGAN = "extra_bimbingan";
    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir_array";

    private static final String PARAM_BIMBINGAN = "bimbingan.bimbingan_review";

    private ProgressDialog progressDialog;
    private BimbinganPresenter bimbinganPresenter;

    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private TextView textViewKehadiranMahasiswa1, textViewKehadiranMahasiswa2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_bimbingan_detail);

        setTitle(getString(R.string.title_bimbingan_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        bimbinganPresenter = new BimbinganPresenter(this, this);
        bimbinganPresenter.initContext(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        TextView textViewTanggal = findViewById(R.id.act_dsn_mhs_bimbingan_textview_tanggal);
        TextView textViewMahasiswa1 = findViewById(R.id.nama_mahasiswa_1);
        TextView textViewMahasiswa2 = findViewById(R.id.nama_mahasiswa_2);
        textViewKehadiranMahasiswa1 = findViewById(R.id.kehadiran_mahasiswa_1);
        textViewKehadiranMahasiswa2 = findViewById(R.id.kehadiran_mahasiswa_2);
        TextView textViewReviewBimbingan = findViewById(R.id.act_dsn_mhs_bimbingan_textview_review);
        FloatingActionButton fabDecline = findViewById(R.id.fab_bimbingan_decline);
        FloatingActionButton fabAccept = findViewById(R.id.fab_bimbingan_accept);

        Bimbingan extraBimbingan = getIntent().getParcelableExtra(EXTRA_BIMBINGAN);

        if (extraBimbingan.getBimbingan_status().equals(STATUS_BIMBINGAN_DISETUJUI)){
            fabDecline.setVisibility(View.GONE);
            fabAccept.setVisibility(View.GONE);
        } else {
            fabDecline.setVisibility(View.VISIBLE);
            fabAccept.setVisibility(View.VISIBLE);
        }

        ArrayList<ProyekAkhir> extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);
        if (extraArrayProyekAkhir.size() !=2 ){
            textViewKehadiranMahasiswa2.setVisibility(View.GONE);
            textViewMahasiswa2.setVisibility(View.GONE);
            textViewMahasiswa1.setText(extraArrayProyekAkhir.get(0).getMhs_nama());
        } else {
            textViewMahasiswa1.setText(extraArrayProyekAkhir.get(0).getMhs_nama());
            textViewMahasiswa2.setText(extraArrayProyekAkhir.get(1).getMhs_nama());
        }

        textViewTanggal.setText(extraBimbingan.getBimbingan_tanggal());
        textViewReviewBimbingan.setText(extraBimbingan.getBimbingan_review());

        bimbinganPresenter.searchBimbinganAllBy(PARAM_BIMBINGAN, extraBimbingan.getBimbingan_review());

        fabAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrayListBimbingan.size(); i++) {
                    bimbinganPresenter.updateBimbinganStatus(arrayListBimbingan.get(i).getBimbingan_id(), STATUS_BIMBINGAN_DISETUJUI);
                }
            }
        });

        fabDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrayListBimbingan.size(); i++) {
                    bimbinganPresenter.deleteBimbingan(arrayListBimbingan.get(i).getBimbingan_id());
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_edit_delete, menu);
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
        progressDialog.hide();
    }

    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);
        if (arrayListBimbingan.size() < 2) {
            textViewKehadiranMahasiswa1.setText(arrayListBimbingan.get(0).getBimbingan_kehadiran());
        } else {
            textViewKehadiranMahasiswa1.setText(arrayListBimbingan.get(0).getBimbingan_kehadiran());
            textViewKehadiranMahasiswa2.setText(arrayListBimbingan.get(1).getBimbingan_kehadiran());
        }
    }

    @Override
    public void isEmptyListBimbingan() {

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
