package org.d3ifcool.finpro.mahasiswa.activities.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.lists.SidangListView;
import org.d3ifcool.finpro.core.models.Sidang;
import org.d3ifcool.finpro.core.presenters.SidangPresenter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_LULUS;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_SIDANG_LULUS_BERSYARAT;

public class MahasiswaPaSidangDetailActivity extends AppCompatActivity implements SidangListView{

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String PARAM_SIDANG = "mhs_nim";

    private SidangPresenter sidangPresenter;
    private ProgressDialog progressDialog;
    private SessionManager sessionManager;

    private LinearLayout linearLayout_mhs1;

    private TextView tv_nilai_proposal1, tv_nilai_pembimbing, tv_nilai_penguji1_1,
            tv_nilai_total_1, tv_review_1, tv_status_1, tv_nilai_penguji2_1, tv_tanggal_sidang;

    private ArrayList<Sidang> sidangArrayList = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_pa_sidang_detail);

        setTitle(getString(R.string.title_sidang));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        sidangPresenter = new SidangPresenter(this);
        sidangPresenter.initContext(this);

        sessionManager = new SessionManager(this);

        linearLayout_mhs1 = findViewById(R.id.linear_mhs_1);
        TextView tv_nama1 = findViewById(R.id.dsn_sidang_textview_nama_1);
        TextView  tv_nim1 = findViewById(R.id.dsn_sidang_textview_nim_1);
        tv_nilai_proposal1 = findViewById(R.id.dsn_sidang_nilai_proposal_1);
        tv_nilai_pembimbing = findViewById(R.id.dsn_sidang_nilai_pembimbing1_1);
        tv_nilai_penguji1_1 = findViewById(R.id.dsn_sidang_nilai_penguji1_1);
        tv_nilai_total_1 = findViewById(R.id.dsn_sidang_nilai_total_1);
        tv_review_1 = findViewById(R.id.dsn_sidang_textview_review_1);
        tv_status_1 = findViewById(R.id.dsn_sidang_textview_status_1);
        tv_nilai_penguji2_1 = findViewById(R.id.dsn_sidang_nilai_penguji2_1);
        tv_tanggal_sidang = findViewById(R.id.tanggal_sidang);

        tv_nama1.setText(sessionManager.getSessionMahasiswaNama());
        tv_nim1.setText(sessionManager.getSessionMahasiswaNim());

        sidangPresenter.searchAllSidangBy(PARAM_SIDANG, sessionManager.getSessionMahasiswaNim());

    }


    private void setDataSidang(@NonNull Sidang sidang){
        tv_nilai_proposal1.setText(String.valueOf(sidang.getNilai_proposal()));
        tv_nilai_pembimbing.setText(String.valueOf(sidang.getNilai_pembimbing()));
        tv_nilai_penguji1_1.setText(String.valueOf(sidang.getNilai_penguji_1()));
        tv_nilai_penguji2_1.setText(String.valueOf(sidang.getNilai_penguji_2()));
        tv_nilai_total_1.setText(String.valueOf(sidang.getNilai_total()));
        tv_status_1.setText(sidang.getSidang_status());
        tv_review_1.setText(sidang.getSidang_review());
        tv_tanggal_sidang.setText(sidang.getSidang_tanggal());

        if (sidang.getSidang_status().equals(STATUS_SIDANG_LULUS) || sidang.getSidang_status().equals(STATUS_SIDANG_LULUS_BERSYARAT)){
            tv_status_1.setTextColor(MahasiswaPaSidangDetailActivity.this.getResources().getColor(R.color.colorTextGreen));
        } else {
            tv_status_1.setTextColor(MahasiswaPaSidangDetailActivity.this.getResources().getColor(R.color.colorTextRed));
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sessionManager.getSessionMahasiswaNim() != null){
            sidangPresenter.searchAllSidangBy(PARAM_SIDANG, sessionManager.getSessionMahasiswaNim());
        }
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
    public void onGetListSidang(List<Sidang> sidangList) {
        sidangArrayList.clear();
        sidangArrayList.addAll(sidangList);

        if (sidangArrayList.size() == 0) {
            linearLayout_mhs1.setVisibility(View.GONE);
        } else {
            setDataSidang(sidangArrayList.get(0));
            linearLayout_mhs1.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void isEmptyListSidang() {

    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
