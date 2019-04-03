package org.d3ifcool.dosen.activities.details;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.d3ifcool.dosen.R;
import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.works.JudulWorkView;
import org.d3ifcool.service.interfaces.works.MahasiswaWorkView;
import org.d3ifcool.service.interfaces.works.ProyekAkhirWorkView;
import org.d3ifcool.service.models.Judul;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.presenters.JudulPresenter;
import org.d3ifcool.service.presenters.MahasiswaPresenter;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DIGUNAKAN;
import static org.d3ifcool.service.helpers.Constant.ObjectConstanta.JUDUL_STATUS_DITOLAK;

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
    private TextView textViewMhsNIM1, textViewMhsNama1, textViewMhsNIM2, textViewMhsNama2, textViewKelompok;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_judul_pa_submahasiswa_detail);

        setTitle(getString(R.string.title_judulpa_detail));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        proyekAkhirPresenter = new ProyekAkhirPresenter(this, this);
        judulPresenter = new JudulPresenter(this);
        mahasiswaPresenter = new MahasiswaPresenter(this);

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
        textViewMhsNIM1 = findViewById(R.id.act_dsn_mhs_judul_nim_1);
        textViewMhsNama1 = findViewById(R.id.act_dsn_mhs_judul_nama_1);
        textViewMhsNIM2 = findViewById(R.id.act_dsn_mhs_judul_nim_2);
        textViewMhsNama2 = findViewById(R.id.act_dsn_mhs_judul_nama_2);

        textViewTanggal.setText(extraJudulTanggal);
        textViewJudul.setText(extraJudulNama);
        textViewKelompok.setText(extraJudulTanggal);
        textViewDeskripsi.setText(extraJudulDeskripsi);
        textViewKategori.setText(extraJudulKategori);

        proyekAkhirPresenter.searchProyekAkhir(PARAM_PROYEK_AKHIR, String.valueOf(extraJudulId));

        floatingActionButtonAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judulPresenter.updateStatusJudul(extraJudulId, JUDUL_STATUS_DIGUNAKAN);
            }
        });

        floatingActionButtonDecline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                judulPresenter.updateStatusJudul(extraJudulId, JUDUL_STATUS_DITOLAK);

                proyekAkhirPresenter.deleteProyekAkhir(arrayListProyekAkhir.get(0).getProyek_akhir_id());
                mahasiswaPresenter.updateMahasiswaJudul(arrayListProyekAkhir.get(0).getMhs_nim(), 0);

                if (arrayListProyekAkhir.size() == 2) {
                    proyekAkhirPresenter.deleteProyekAkhir(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getProyek_akhir_id());
                    mahasiswaPresenter.updateMahasiswaJudul(arrayListProyekAkhir.get(1).getMhs_nim(), 0);
                }

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
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
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

        textViewMhsNIM1.setText(arrayListProyekAkhir.get(0).getMhs_nim());
        textViewMhsNama1.setText(arrayListProyekAkhir.get(0).getMhs_nama());
        textViewKelompok.setText(arrayListProyekAkhir.get(0).getNama_tim());

        if (arrayListProyekAkhir.size() == 2) {
            textViewMhsNIM2.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nim());
            textViewMhsNama2.setText(arrayListProyekAkhir.get(arrayListProyekAkhir.size()-1).getMhs_nama());
        }
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
