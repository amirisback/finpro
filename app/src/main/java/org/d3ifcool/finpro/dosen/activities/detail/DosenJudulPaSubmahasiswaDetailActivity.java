package org.d3ifcool.finpro.dosen.activities.detail;

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
import org.d3ifcool.finpro.core.models.Judul;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.JudulPresenter;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;
import org.d3ifcool.finpro.core.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DITOLAK;

public class DosenJudulPaSubmahasiswaDetailActivity extends AppCompatActivity implements ProyekAkhirListView,
        JudulWorkView, ProyekAkhirWorkView, MahasiswaWorkView {

    public static final String EXTRA_JUDUL = "extra_judul";
    private static final String PARAM_PROYEK_AKHIR = "proyek_akhir.judul_id";

    private ProyekAkhirPresenter proyekAkhirPresenter;
    private JudulPresenter judulPresenter;
    private MahasiswaPresenter mahasiswaPresenter;

    private ProgressDialog progressDialog;
    private int extraJudulId;
    private ArrayList<ProyekAkhir> arrayListProyekAkhir = new ArrayList<>();
    private TextView textViewKelompok;

    private ViewAdapterHelper viewAdapterHelper;
    private AnggotaViewAdapter anggotaViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_submahasiswa_detail);

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

        Judul extraJudul = getIntent().getParcelableExtra(EXTRA_JUDUL);
        extraJudulId = extraJudul.getId();
        String extraJudulTanggal = extraJudul.getJudul_waktu();
        String extraJudulNama = extraJudul.getJudul();
        String extraJudulDeskripsi = extraJudul.getDeskripsi();
        String extraJudulKategori = extraJudul.getKategori_nama();

        TextView textViewTanggal = findViewById(R.id.act_dsn_mhs_judul_tanggal);
        TextView textViewJudul = findViewById(R.id.act_dsn_mhs_judul);
        TextView textViewDeskripsi = findViewById(R.id.act_dsn_mhs_judul_deskripsi);
        TextView textViewKategori = findViewById(R.id.act_dsn_mhs_judul_kategori);

        FloatingActionButton floatingActionButtonAccept = findViewById(R.id.act_dsn_mhs_judul_fab_accept);
        FloatingActionButton floatingActionButtonDecline = findViewById(R.id.act_dsn_mhs_judul_fab_decline);
        FloatingActionButton floatingActionButtonConversation = findViewById(R.id.act_dsn_mhs_judul_fab_conversation);

        textViewKelompok = findViewById(R.id.act_dsn_mhs_judul_kelompok);

        textViewTanggal.setText(extraJudulTanggal);
        textViewJudul.setText(extraJudulNama);
        textViewDeskripsi.setText(extraJudulDeskripsi);
        textViewKategori.setText(extraJudulKategori);

        RecyclerView recyclerView = findViewById(R.id.all_recyclerview_anggota);

        anggotaViewAdapter = new AnggotaViewAdapter(this);
        viewAdapterHelper = new ViewAdapterHelper(this);
        viewAdapterHelper.setRecyclerView(recyclerView);

        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, String.valueOf(extraJudulId));

        floatingActionButtonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                new AlertDialog
                        .Builder(DosenJudulPaSubmahasiswaDetailActivity.this)
                        .setTitle(getString(R.string.dialog_setuju_title))
                        .setMessage(getString(R.string.dialog_setuju_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
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
                        .Builder(DosenJudulPaSubmahasiswaDetailActivity.this)
                        .setTitle(getString(R.string.dialog_tolak_title))
                        .setMessage(getString(R.string.dialog_tolak_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                proyekAkhirPresenter.deleteProyekAkhir(arrayListProyekAkhir.get(0).getProyek_akhir_id());
                                mahasiswaPresenter.updateMahasiswaJudul(arrayListProyekAkhir.get(0).getMhs_nim(), 0);

                                if (arrayListProyekAkhir.size() == 2) {
                                    proyekAkhirPresenter.deleteProyekAkhir(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getProyek_akhir_id());
                                    mahasiswaPresenter.updateMahasiswaJudul(arrayListProyekAkhir.get(1).getMhs_nim(), 0);
                                }

                                judulPresenter.updateStatusJudul(extraJudulId, JUDUL_STATUS_DITOLAK);
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
    public void onSucces() {
    }

    @Override
    public void onSuccesWorkJudul() {
        finish();
    }

    @Override
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        arrayListProyekAkhir.clear();
        arrayListProyekAkhir.addAll(proyekAkhirList);

        textViewKelompok.setText(arrayListProyekAkhir.get(0).getNama_tim());

        anggotaViewAdapter.addItem(arrayListProyekAkhir);
        viewAdapterHelper.setAdapterAnggota(anggotaViewAdapter);
    }

    @Override
    public void isEmptyListProyekAkhir() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
