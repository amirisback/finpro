package org.d3ifcool.finpro.dosen.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.d3ifcool.finpro.core.interfaces.lists.BimbinganListView;
import org.d3ifcool.finpro.core.interfaces.lists.BimbinganSearchListView;
import org.d3ifcool.finpro.core.interfaces.works.ICreate;
import org.d3ifcool.finpro.core.models.Bimbingan;
import org.d3ifcool.finpro.core.models.ProyekAkhir;
import org.d3ifcool.finpro.core.presenters.BimbinganPresenter;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.dosen.adapters.recyclerview.DosenBimbinganViewAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_DISETUJUI;
import static org.d3ifcool.finpro.core.helpers.Constant.ObjectConstanta.STATUS_BIMBINGAN_PENDING;

public class DosenProyekAkhirBimbinganActivity extends AppCompatActivity implements BimbinganListView, BimbinganSearchListView, ICreate {

    public static final String EXTRA_PROYEK_AKHIR = "extra_proyek_akhir";
    private static final String BIMBINGAN_PARAM = "bimbingan.proyek_akhir_id";

    private static final String BIMBINGAN_PARAM_JUDUL_ID = "proyek_akhir.judul_id";
    private static final String BIMBINGAN_PARAM_STATUS = "bimbingan.bimbingan_status";

    private RecyclerView recyclerView;
    private BimbinganPresenter bimbinganPresenter;
    private DosenBimbinganViewAdapter adapter;
    private View empty_view, container_accept_bimbingan, content_line;

    private ProgressDialog progressDialog;
    private ArrayList<Bimbingan> arrayListBimbingan = new ArrayList<>();
    private ArrayList<Bimbingan> arrayListBimbinganSearch = new ArrayList<>();
    private ArrayList<ProyekAkhir> extraArrayProyekAkhir;

    private TextView tv_jml_bimbingan;
    private String judul_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_proyek_akhir_bimbingan);

        setTitle(getString(R.string.title_bimbingan));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adapter = new DosenBimbinganViewAdapter(this);
        progressDialog = new ProgressDialog(this);
        bimbinganPresenter = new BimbinganPresenter(this, this, this);
        bimbinganPresenter.initContext(this);

        progressDialog.setMessage(getString(R.string.text_progress_dialog));

        empty_view = findViewById(R.id.view_emptyview);
        recyclerView = findViewById(R.id.act_mhs_pa_bimbingan_detail_recyclerview);
        content_line = findViewById(R.id.content_line);
        tv_jml_bimbingan = findViewById(R.id.act_mhs_pa_bimbingan_detail_textview_jumlah);
        container_accept_bimbingan = findViewById(R.id.container_accept_all_bimbingan);

        extraArrayProyekAkhir = getIntent().getParcelableArrayListExtra(EXTRA_PROYEK_AKHIR);
        judul_id = String.valueOf(extraArrayProyekAkhir.get(0).getJudul_id());
        adapter.addProyekAkhir(extraArrayProyekAkhir);

        searchBimbingan();

        Button btn_approve_all = findViewById(R.id.btn_accept_all_bimbingan);

        btn_approve_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog
                        .Builder(DosenProyekAkhirBimbinganActivity.this)
                        .setTitle(getString(R.string.dialog_acc_bimbingan_title))
                        .setMessage(getString(R.string.dialog_acc_bimbingan_text))

                        .setPositiveButton(R.string.iya, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                for (int i = 0; i < arrayListBimbinganSearch.size(); i++) {
                                    bimbinganPresenter.updateBimbinganStatus(arrayListBimbinganSearch.get(i).getBimbingan_id(), STATUS_BIMBINGAN_DISETUJUI);
                                }
                            }
                        })

                        .setNegativeButton(R.string.tidak, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

    }

    private void searchBimbingan(){
        bimbinganPresenter.searchBimbinganAllBy(BIMBINGAN_PARAM, String.valueOf(extraArrayProyekAkhir.get(0).getProyek_akhir_id()));
        bimbinganPresenter.searchBimbinganAllByTwo(BIMBINGAN_PARAM_JUDUL_ID, judul_id, BIMBINGAN_PARAM_STATUS, STATUS_BIMBINGAN_PENDING);
    }

    @Override
    protected void onResume() {
        super.onResume();
        searchBimbingan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();
        if (i == android.R.id.home) {
            finish();
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
        searchBimbingan();
    }

    @Override
    public void onGetListBimbinganSearch(List<Bimbingan> bimbinganList) {

        arrayListBimbinganSearch.clear();
        arrayListBimbinganSearch.addAll(bimbinganList);

        if(arrayListBimbinganSearch.size() == 0){
            content_line.setVisibility(View.GONE);
            container_accept_bimbingan.setVisibility(View.GONE);
        } else {
            content_line.setVisibility(View.VISIBLE);
            container_accept_bimbingan.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onGetListBimbingan(List<Bimbingan> bimbinganList) {

        arrayListBimbingan.clear();
        arrayListBimbingan.addAll(bimbinganList);

        adapter.addItem(arrayListBimbingan);
        adapter.setLayoutType(R.layout.content_list_all_pa_bimbingan);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        tv_jml_bimbingan.setText(String.valueOf(arrayListBimbingan.size()));

        if (arrayListBimbingan.size() == 0) {
            empty_view.setVisibility(View.VISIBLE);
        } else {
            empty_view.setVisibility(View.GONE);
        }

    }

    @Override
    public void isEmptyListBimbingan() {
    }

    @Override
    public void onFailed(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
