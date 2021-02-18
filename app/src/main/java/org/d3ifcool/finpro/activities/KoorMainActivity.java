package org.d3ifcool.finpro.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.objects.KoorView;
import org.d3ifcool.finpro.core.models.KoordinatorPa;
import org.d3ifcool.finpro.core.presenters.KoorPresenter;
import org.d3ifcool.finpro.koor.activities.KoorPemberitahuanActivity;
import org.d3ifcool.finpro.koor.activities.KoorProfilActivity;
import org.d3ifcool.finpro.koor.fragments.KoorArsipJudulFragment;
import org.d3ifcool.finpro.koor.fragments.KoorDosenFragment;
import org.d3ifcool.finpro.koor.fragments.KoorInformasiFragment;
import org.d3ifcool.finpro.koor.fragments.KoorJadwalKegiatanFragment;
import org.d3ifcool.finpro.koor.fragments.KoorKategoriJudulFragment;
import org.d3ifcool.finpro.koor.fragments.KoorJudulFragment;
import org.d3ifcool.finpro.koor.fragments.KoorKategoriMonevFragment;
import org.d3ifcool.finpro.koor.fragments.KoorMahasiswaFragment;
import org.d3ifcool.finpro.koor.fragments.KoorMahasiswaSiapSidangFragment;
import org.d3ifcool.finpro.koor.fragments.KoorPemetaanMonevFragment;
import org.d3ifcool.finpro.koor.fragments.KoorProyekAkhirFragment;
import org.d3ifcool.finpro.koor.fragments.KoorReviewerFragment;
import org.d3ifcool.finpro.koor.fragments.KoorTentangKamiFragment;

public class KoorMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, KoorView {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        sessionManager = new SessionManager(this);
        KoorPresenter koorPresenter = new KoorPresenter(this);
        koorPresenter.initContext(this);

        koorPresenter.getKoorByParameter(sessionManager.getSessionUsername());

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_toolbar_koor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.toolbar_menu_pemberitahuan:
                Intent intentPemberitahuan = new Intent(KoorMainActivity.this, KoorPemberitahuanActivity.class);
                startActivity(intentPemberitahuan);
                break;

            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(KoorMainActivity.this, KoorProfilActivity.class);
                startActivity(intentProfil);
                break;

            case R.id.toolbar_menu_keluar:
                new AlertDialog
                        .Builder(this)
                        .setTitle(getString(R.string.dialog_keluar_title))
                        .setMessage(getString(R.string.dialog_keluar_text))
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentKeluar = new Intent(KoorMainActivity.this, LoginActivity.class);
                                startActivity(intentKeluar);
                                sessionManager.removeSession();
                                finish();
                            }
                        })

                        .setNegativeButton("Batal", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu_informasi) {
            setTitle(getString(R.string.title_informasi));
            openFragment(new KoorInformasiFragment());
        } else if (id == R.id.nav_menu_mahasiswa) {
            setTitle(getString(R.string.title_mahasiswa));
            openFragment(new KoorMahasiswaFragment());
        } else if (id == R.id.nav_menu_dosen) {
            setTitle(getString(R.string.title_dosen));
            openFragment(new KoorDosenFragment());
        } else if (id == R.id.nav_menu_proyek_akhir) {
            setTitle(getString(R.string.title_proyekakhir));
            openFragment(new KoorProyekAkhirFragment());
        } else if (id == R.id.nav_menu_judul_pa) {
            setTitle(getString(R.string.title_judulpa));
            openFragment(new KoorJudulFragment());
        }else if (id == R.id.nav_menu_judul_pa_kategori){
            setTitle(getString(R.string.title_kategori_judul_pa));
            openFragment(new KoorKategoriJudulFragment());
        }else if (id == R.id.nav_menu_mahasiswa_pemetaan_monev){
            setTitle(getString(R.string.title_pemetaan_monev));
            openFragment(new KoorPemetaanMonevFragment());
        }else if (id == R.id.nav_menu_kategori_monev){
            setTitle(getString(R.string.title_kategori_monev));
            openFragment(new KoorKategoriMonevFragment());
        }else if (id == R.id.nav_menu_arsip_judul){
            setTitle(getString(R.string.title_arsip_judul));
            openFragment(new KoorArsipJudulFragment());
        }else if (id == R.id.nav_menu_tentang_kami){
            setTitle(R.string.title_tentang_kami);
            openFragment(new KoorTentangKamiFragment());
        }else if (id == R.id.nav_menu_jadwal_kegiatan){
            setTitle(R.string.title_jadwal_kegiatan);
            openFragment(new KoorJadwalKegiatanFragment());
        }else if (id == R.id.nav_menu_reviewer){
            setTitle(R.string.title_monev_reviewer);
            openFragment(new KoorReviewerFragment());
        }else if (id == R.id.nav_menu_mahasiswa_siap_sidang){
            setTitle(R.string.title_siap_sidang);
            openFragment(new KoorMahasiswaSiapSidangFragment());
        }

//        else if (id == R.id.nav_menu_kuota_dosen){
//            setTitle(R.string.title_kuota_dosen);
//            openFragment(new KoorKuotaDosenFragment());
//        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        item.setCheckable(true);

        return true;
    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_container, fragment)
                .commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void onGetObjectKoor(KoordinatorPa koordinatorPa) {
        sessionManager.createSessionDataKoor(koordinatorPa);
    }

    @Override
    public void isEmptyObjectKoor() {

    }

    @Override
    public void onFailed(String message) {

    }
}
