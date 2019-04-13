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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.R;
import org.d3ifcool.service.helpers.SessionManager;
import org.d3ifcool.service.interfaces.objects.KoorView;
import org.d3ifcool.service.models.KoordinatorPa;
import org.d3ifcool.service.presenters.KoorPresenter;
import org.d3ifcool.superuser.activities.KoorProfilActivity;
import org.d3ifcool.superuser.fragments.KoorDosenFragment;
import org.d3ifcool.superuser.fragments.KoorInformasiFragment;
import org.d3ifcool.superuser.fragments.KoorKategoriJudulFragment;
import org.d3ifcool.superuser.fragments.KoorJudulFragment;
import org.d3ifcool.superuser.fragments.KoorKategoriMonevFragment;
import org.d3ifcool.superuser.fragments.KoorMahasiswaFragment;
import org.d3ifcool.superuser.fragments.KoorPemetaanMonevFragment;
import org.d3ifcool.superuser.fragments.KoorProyekAkhirFragment;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, KoorView {

    private KoorDosenFragment koorDosenFragment = new KoorDosenFragment();
    private KoorInformasiFragment koorInformasiFragment = new KoorInformasiFragment();
    private KoorMahasiswaFragment koorMahasiswaFragment = new KoorMahasiswaFragment();
    private KoorJudulFragment koorJudulPaSubdosenFragment = new KoorJudulFragment();
    private KoorProyekAkhirFragment koorProyekAkhirFragment = new KoorProyekAkhirFragment();
    private KoorKategoriJudulFragment koorJudulPaKategoriFragment = new KoorKategoriJudulFragment();
    private KoorPemetaanMonevFragment koorPemetaanMahasiswaMonev = new KoorPemetaanMonevFragment();
    private KoorKategoriMonevFragment koorKategoriMonevFragment = new KoorKategoriMonevFragment();

    private SessionManager sessionManager;

    private KoorPresenter presenter;

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
        presenter = new KoorPresenter(this);

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        presenter.getKoorByParameter(sessionManager.getSessionUsername());
        setFragmentLayout(koorInformasiFragment);
        setTitle(getString(R.string.title_informasi));

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
            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(AdminMainActivity.this, KoorProfilActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.toolbar_menu_keluar:
                new AlertDialog
                        .Builder(this)
                        .setTitle(getString(R.string.keluar))
                        .setMessage(getString(R.string.messagekeluar))
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentKeluar = new Intent(AdminMainActivity.this, LoginActivity.class);
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
            setFragmentLayout(koorInformasiFragment);
        } else if (id == R.id.nav_menu_mahasiswa) {
            setTitle(getString(R.string.title_mahasiswa));
            setFragmentLayout(koorMahasiswaFragment);
        } else if (id == R.id.nav_menu_dosen) {
            setTitle(getString(R.string.title_dosen));
            setFragmentLayout(koorDosenFragment);
        } else if (id == R.id.nav_menu_proyek_akhir) {
            setTitle(getString(R.string.title_proyekakhir));
            setFragmentLayout(koorProyekAkhirFragment);
        } else if (id == R.id.nav_menu_judul_pa) {
            setTitle(getString(R.string.title_judulpa));
            setFragmentLayout(koorJudulPaSubdosenFragment);
        }else if (id == R.id.nav_menu_judul_pa_kategori){
            setTitle(getString(R.string.title_kategori_judul_pa));
            setFragmentLayout(koorJudulPaKategoriFragment);
        }else if (id == R.id.nav_menu_mahasiswa_pemetaan_monev){
            setTitle(getString(R.string.title_pemetaan_monev));
            setFragmentLayout(koorPemetaanMahasiswaMonev);
        }else if (id == R.id.nav_menu_kategori_monev){
            setTitle(getString(R.string.text_title_kategori_monev));
            setFragmentLayout(koorKategoriMonevFragment);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void setFragmentLayout(Fragment mFragment) {
        FragmentManager mFragmentManager = getSupportFragmentManager();
        FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.framelayout_container, mFragment);
        mFragmentTransaction.commit();
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
    public void onFailed(String message) {

    }
}
