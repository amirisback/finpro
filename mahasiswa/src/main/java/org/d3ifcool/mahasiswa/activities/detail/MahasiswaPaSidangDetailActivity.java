package org.d3ifcool.mahasiswa.activities.detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.mahasiswa.R;
import org.d3ifcool.service.interfaces.lists.ProyekAkhirListView;
import org.d3ifcool.service.interfaces.lists.SidangListView;
import org.d3ifcool.service.models.ProyekAkhir;
import org.d3ifcool.service.models.Sidang;
import org.d3ifcool.service.presenters.ProyekAkhirPresenter;
import org.d3ifcool.service.presenters.SidangPresenter;

import java.util.ArrayList;
import java.util.List;

public class MahasiswaPaSidangDetailActivity extends AppCompatActivity implements SidangListView, ProyekAkhirListView{

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";

    private static final String PARAM_PROYEK_AKHIR = "proyek_akhir.judul_id";

    private ArrayList<ProyekAkhir> proyekAkhirArrayList = new ArrayList<>();
    private ArrayList<Sidang> sidangArrayList = new ArrayList<>();

    private SidangPresenter sidangPresenter;
    private ProyekAkhirPresenter proyekAkhirPresenter;
    private ProgressDialog progressDialog;

    private SwipeRefreshLayout swipeRefreshLayout;
    private CardView cardView_mhs_1,cardView_mhs_2;
    private LinearLayout linearLayout_mhs1, linearLayout_mhs2;
    private TextView tv_nama1,tv_nim1,tv_nama2,tv_nim2;
    private TextView tv_nilai_proposal1, tv_nilai_pembimbing,tv_nilai_penguji1_1,tv_nilai_penguji1_2,tv_nilai_total_1, tv_review_1, tv_status_1;
    private TextView tv_nilai_proposal2, tv_nilai_pembimbing2,tv_nilai_penguji2_1,tv_nilai_penguji2_2,tv_nilai_total_2,tv_review_2, tv_status_2;

    private String nama_1, nama_2, nim_1, nim_2, judul_id;
    private int nilai_prop1,nilai_prop2, nilai_pemb1,nilai_pemb2, nilai_peng1_1, nilai_peng1_2,nilai_peng2_1,nilai_peng2_2, nilai_total1,nilai_total2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_sidang_detail);

        setTitle(getString(R.string.title_sidang));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));
        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        sidangPresenter = new SidangPresenter(this);

        tv_nama1 = findViewById(R.id.dsn_sidang_textview_nama_1);
        tv_nim1 = findViewById(R.id.dsn_sidang_textview_nim_1);
        tv_nama2 = findViewById(R.id.dsn_sidang_textview_nama_2);
        tv_nim2 = findViewById(R.id.dsn_sidang_textview_nim_2);
        cardView_mhs_1 = findViewById(R.id.card_view_mhs_1);
        cardView_mhs_2 = findViewById(R.id.card_view_mhs_2);
        linearLayout_mhs1 = findViewById(R.id.linear_mhs_1);
        linearLayout_mhs2 = findViewById(R.id.linear_mhs_2);

        tv_nilai_proposal1 = findViewById(R.id.dsn_sidang_nilai_proposal_1);
        tv_nilai_pembimbing = findViewById(R.id.dsn_sidang_nilai_pembimbing1_1);
        tv_nilai_penguji1_1 = findViewById(R.id.dsn_sidang_nilai_penguji1_1);
        tv_nilai_penguji1_2 = findViewById(R.id.dsn_sidang_nilai_penguji1_2);
        tv_nilai_total_1 = findViewById(R.id.dsn_sidang_nilai_total_1);
        tv_review_1 = findViewById(R.id.dsn_sidang_textview_review_1);
        tv_status_1 = findViewById(R.id.dsn_sidang_textview_status_1);

        tv_nilai_proposal2 = findViewById(R.id.dsn_sidang_nilai_proposal_2);
        tv_nilai_pembimbing2 = findViewById(R.id.dsn_sidang_nilai_pembimbing1_2);
        tv_nilai_penguji2_1 = findViewById(R.id.dsn_sidang_nilai_penguji2_1);
        tv_nilai_penguji2_2 = findViewById(R.id.dsn_sidang_nilai_penguji2_2);
        tv_nilai_total_2 = findViewById(R.id.dsn_sidang_nilai_total_2);
        tv_review_2 = findViewById(R.id.dsn_sidang_textview_review_2);
        tv_status_2 = findViewById(R.id.dsn_sidang_textview_status_2);

        ProyekAkhir extra_proyek_akhir = getIntent().getParcelableExtra(EXTRA_PROYEK_AKHIR);
        judul_id = String.valueOf(extra_proyek_akhir.getJudul_id());

//        proyekAkhirPresenter.searchAllProyekAkhirBy(PARAM_PROYEK_AKHIR, judul_id);
//        sidangPresenter.searchAllSidangBy(PARAM_PROYEK_AKHIR, judul_id);

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
    public void onGetListProyekAkhir(List<ProyekAkhir> proyekAkhirList) {
        proyekAkhirArrayList.clear();
        proyekAkhirArrayList.addAll(proyekAkhirList);

        if (proyekAkhirArrayList.size() == 2) {
            tv_nama1.setText(proyekAkhirArrayList.get(0).getMhs_nama());
            tv_nama2.setText(proyekAkhirArrayList.get(1).getMhs_nama());
            tv_nim1.setText(proyekAkhirArrayList.get(0).getMhs_nim());
            tv_nim2.setText(proyekAkhirArrayList.get(1).getMhs_nim());
        } else {
            tv_nama1.setText(proyekAkhirArrayList.get(0).getMhs_nama());
            tv_nim1.setText(proyekAkhirArrayList.get(0).getMhs_nim());
            cardView_mhs_2.setVisibility(View.GONE);
        }

    }

    @Override
    public void onGetListSidang(List<Sidang> sidangList) {
        sidangArrayList.clear();
        sidangArrayList.addAll(sidangList);
        sidangPresenter.searchAllSidangBy(PARAM_PROYEK_AKHIR, judul_id);
        if (sidangArrayList.size() == 0){
            linearLayout_mhs1.setVisibility(View.GONE);
            linearLayout_mhs2.setVisibility(View.GONE);
        }

        if (sidangArrayList.size() == 2) {
            tv_nilai_proposal1.setText(String.valueOf(sidangArrayList.get(0).getNilai_proposal()));
            tv_nilai_pembimbing.setText(String.valueOf(sidangArrayList.get(0).getNilai_pembimbing()));
            tv_nilai_penguji1_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_1()));
            tv_nilai_penguji1_2.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_2()));
            tv_nilai_total_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_sidang()));
            tv_status_1.setText(sidangArrayList.get(0).getSidang_status());
            tv_review_1.setText(sidangArrayList.get(0).getSidang_review());

            tv_nilai_proposal2.setText(String.valueOf(sidangArrayList.get(1).getNilai_proposal()));
            tv_nilai_pembimbing2.setText(String.valueOf(sidangArrayList.get(1).getNilai_pembimbing()));
            tv_nilai_penguji2_1.setText(String.valueOf(sidangArrayList.get(1).getNilai_penguji_1()));
            tv_nilai_penguji2_2.setText(String.valueOf(sidangArrayList.get(1).getNilai_penguji_2()));
            tv_nilai_total_2.setText(String.valueOf(sidangArrayList.get(1).getNilai_sidang()));
            tv_status_2.setText(sidangArrayList.get(1).getSidang_status());
            tv_review_2.setText(sidangArrayList.get(1).getSidang_review());
        } else {
            tv_nilai_proposal1.setText(String.valueOf(sidangArrayList.get(0).getNilai_proposal()));
            tv_nilai_pembimbing.setText(String.valueOf(sidangArrayList.get(0).getNilai_pembimbing()));
            tv_nilai_penguji1_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_1()));
            tv_nilai_penguji1_2.setText(String.valueOf(sidangArrayList.get(0).getNilai_penguji_2()));
            tv_nilai_total_1.setText(String.valueOf(sidangArrayList.get(0).getNilai_sidang()));

            linearLayout_mhs2.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
