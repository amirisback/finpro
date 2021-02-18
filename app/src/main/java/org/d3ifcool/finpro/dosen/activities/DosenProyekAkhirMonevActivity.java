package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

public class DosenProyekAkhirMonevActivity extends AppCompatActivity implements ProyekAkhirListView, BimbinganListView {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String PARAM_PROYEK_AKHIR = "proyek_akhir.judul_id";
    private static final String PARAM_BIMBINGAN = "bimbingan.proyek_akhir_id";

    private ProgressDialog progressDialog;
    private CardView cardViewMhs1, cardViewMhs2;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private BimbinganPresenter bimbinganPresenter;
    private ArrayList<ProyekAkhir> proyekAkhirArrayList = new ArrayList<>();
    private ArrayList<Bimbingan> bimbinganArrayList = new ArrayList<>();
    private TextView textViewBimbingan, textViewNim1, textViewNim2, textViewNama1, textViewNama2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_proyek_akhir_monev);

        setTitle(getString(R.string.title_proyekakhir_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setElevation(0);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        proyekAkhirPresenter = new ProyekAkhirPresenter(this);
        bimbinganPresenter = new BimbinganPresenter(this);

        proyekAkhirPresenter.initContext(this);
        bimbinganPresenter.initContext(this);

        TextView textViewJudul = findViewById(R.id.frg_mhs_pa_textview_judulpa);
        TextView textViewKelompok = findViewById(R.id.frg_mhs_pa_textview_kelompokpa);
        textViewBimbingan = findViewById(R.id.act_mhs_pa_monev_bimbingan);
        textViewNim1 = findViewById(R.id.frg_mhs_pa_nim_1);
        textViewNim2 = findViewById(R.id.frg_mhs_pa_nim_2);
        textViewNama1 = findViewById(R.id.frg_mhs_pa_nama_1);
        textViewNama2 = findViewById(R.id.frg_mhs_pa_nama_2);

        cardViewMhs1 = findViewById(R.id.card_view_mhs_1);
        cardViewMhs2 = findViewById(R.id.card_view_mhs_2);

        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        String extraJudul = extraProyekAkhir.getJudul_nama();
        String extraNamaTim = extraProyekAkhir.getNama_tim();
        String extraJudulId = String.valueOf(extraProyekAkhir.getJudul_id());

        textViewJudul.setText(extraJudul);
        textViewKelompok.setText(extraNamaTim);

        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, extraJudulId);

        cardViewMhs1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMhs1 = new Intent(DosenProyekAkhirMonevActivity.this, DosenMonevMahasiswaActivity.class);
                ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(0);
                intentMhs1.putExtra(DosenMonevMahasiswaActivity.EXTRA_PROYEK_AKHIR_MONEV, parcelProyekAkhir);
                startActivity(intentMhs1);

            }
        });

        cardViewMhs2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentMhs2 = new Intent(DosenProyekAkhirMonevActivity.this, DosenMonevMahasiswaActivity.class);
                ProyekAkhir parcelProyekAkhir = proyekAkhirArrayList.get(1);
                intentMhs2.putExtra(DosenMonevMahasiswaActivity.EXTRA_PROYEK_AKHIR_MONEV, parcelProyekAkhir);
                startActivity(intentMhs2);
            }
        });

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
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {
        bimbinganArrayList.clear();
        bimbinganArrayList.addAll(bimbinganList);
        textViewBimbingan.setText(String.valueOf(bimbinganArrayList.size()));
    }

    @Override
    public void isEmptyListBimbingan() {

    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        proyekAkhirArrayList.clear();
        proyekAkhirArrayList.addAll(proyekAkhirList);

        String stringProyekAkhirId = String.valueOf(proyekAkhirArrayList.get(0).getProyek_akhir_id());

        bimbinganPresenter.searchBimbinganAllBy(PARAM_BIMBINGAN, stringProyekAkhirId);

        if (proyekAkhirArrayList.size() == 2) {
            textViewNama1.setText(proyekAkhirArrayList.get(0).getMhs_nama());
            textViewNama2.setText(proyekAkhirArrayList.get(1).getMhs_nama());
            textViewNim1.setText(proyekAkhirArrayList.get(0).getMhs_nim());
            textViewNim2.setText(proyekAkhirArrayList.get(1).getMhs_nim());
        } else {
            textViewNama1.setText(proyekAkhirArrayList.get(0).getMhs_nama());
            textViewNim1.setText(proyekAkhirArrayList.get(0).getMhs_nim());
            cardViewMhs2.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListProyekAkhir() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
