package org.d3ifcool.finpro.dosen.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.finpro.core.adapters.AnggotaViewAdapter;
import org.d3ifcool.finpro.core.helpers.ViewAdapterHelper;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.finpro.core.interfaces.works.JudulWorkView;
import org.d3ifcool.finpro.core.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.finpro.core.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;

public class DosenJudulPaSubdosenAccActivity extends AppCompatActivity implements ProyekAkhirListView,
        JudulWorkView, ProyekAkhirWorkView, MahasiswaWorkView {

    public static final String EXTRA_JUDUL = "extra_judul";
    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private static final String PARAM_PROYEK_AKHIR_NAMA_TIM = "proyek_akhir.nama_tim";
    private static final String PARAM_PROYEK_AKHIR_JUDUL_ID = "proyek_akhir.judul_id";

    private ProyekAkhirPresenter proyekAkhirPresenter;
    private JudulPresenter judulPresenter;
    private MahasiswaPresenter mahasiswaPresenter;
    private ProgressDialog progressDialog;
    private int extraJudulId;
    private String extraNamaTim;

    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private ArrayList<ProyekAkhir> tempProyekAkhirArrayList = new ArrayList<>();
    private TextView textViewKelompok;

    private ViewAdapterHelper viewAdapterHelper;
    private AnggotaViewAdapter anggotaViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_subdosen_acc);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this, this);
        judulPresenter = new JudulPresenter(this);
        mahasiswaPresenter = new MahasiswaPresenter(this);

        proyekAkhirPresenter.initContext(this);
        judulPresenter.initContext(this);
        mahasiswaPresenter.initContext(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        FloatingActionButton floatingActionButtonAccept = findViewById(R.id.act_dsn_mhs_judul_fab_accept);
        FloatingActionButton floatingActionButtonDecline = findViewById(R.id.act_dsn_mhs_judul_fab_decline);
        FloatingActionButton floatingActionButtonConversation = findViewById(R.id.act_dsn_mhs_judul_fab_conversation);

        textViewKelompok = findViewById(R.id.act_dsn_mhs_judul_kelompok);

        RecyclerView recyclerView = findViewById(R.id.all_recyclerview_anggota);

        anggotaViewAdapter = new AnggotaViewAdapter(this);
        viewAdapterHelper = new ViewAdapterHelper(this);
        viewAdapterHelper.setRecyclerView(recyclerView);

        extraJudulId = getIntent().getIntExtra(EXTRA_JUDUL, 0);
        ProyekAkhir extraProyekAkhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        extraNamaTim = extraProyekAkhir.getNama_tim();

        textViewKelompok.setText(extraNamaTim);
        // search proyek_akhir berdasarkan judul_id
        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR_JUDUL_ID, String.valueOf(extraJudulId));

        floatingActionButtonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(DosenJudulPaSubdosenAccActivity.this)
                        .setTitle(getString(R.string.dialog_setuju_title))
                        .setMessage(getString(R.string.dialog_setuju_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                for (int i = 0; i < arrayListProyekAkhir.size(); i++) {
                                    proyekAkhirPresenter.deleteProyekAkhir(arrayListProyekAkhir.get(i).getProyek_akhir_id());
                                    mahasiswaPresenter.updateMahasiswaJudul(arrayListProyekAkhir.get(i).getMhs_nim(), 0);
                                }
                                proyekAkhirPresenter.createProyekAkhir(extraJudulId, tempProyekAkhirArrayList.get(0).getMhs_nim(), extraNamaTim);
                                mahasiswaPresenter.updateMahasiswaJudul(tempProyekAkhirArrayList.get(0).getMhs_nim(), extraJudulId);
                                if (tempProyekAkhirArrayList.size() == 2){
                                    proyekAkhirPresenter.createProyekAkhir(extraJudulId, tempProyekAkhirArrayList.get(1).getMhs_nim(), extraNamaTim);
                                    mahasiswaPresenter.updateMahasiswaJudul(tempProyekAkhirArrayList.get(1).getMhs_nim(), extraJudulId);
                                }
                                judulPresenter.updateStatusJudul(extraJudulId, JUDUL_STATUS_DIGUNAKAN);

                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        floatingActionButtonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(DosenJudulPaSubdosenAccActivity.this)
                        .setTitle(getString(R.string.dialog_tolak_title))
                        .setMessage(getString(R.string.dialog_tolak_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                proyekAkhirPresenter.deleteProyekAkhir(tempProyekAkhirArrayList.get(0).getProyek_akhir_id());
                                mahasiswaPresenter.updateMahasiswaJudul(tempProyekAkhirArrayList.get(0).getMhs_nim(), 0);

                                if (tempProyekAkhirArrayList.size() == 2) {
                                    proyekAkhirPresenter.deleteProyekAkhir(tempProyekAkhirArrayList.get(1).getProyek_akhir_id());
                                    mahasiswaPresenter.updateMahasiswaJudul(tempProyekAkhirArrayList.get(1).getMhs_nim(), 0);
                                }

                                finish();
                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        floatingActionButtonConversation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    }

    @Override
    public void hideProgress() {
    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        for (int i = 0; i < arrayListProyekAkhir.size(); i++){
            if (arrayListProyekAkhir.get(i).getNama_tim().equalsIgnoreCase(extraNamaTim)){
                // jika proyek_akhir getProyekAkhirDistinct = session
                tempProyekAkhirArrayList.add(arrayListProyekAkhir.get(i));
            }
        }

        anggotaViewAdapter.addItem(tempProyekAkhirArrayList);
        viewAdapterHelper.setAdapterAnggota(anggotaViewAdapter);

    }

    @Override
    public void isEmptyListProyekAkhir() {

    }

    @Override
    public void onSucces() {
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
