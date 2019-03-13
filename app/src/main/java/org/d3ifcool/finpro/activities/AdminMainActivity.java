package org.d3ifcool.finpro.activities;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.View;

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

import org.d3ifcool.dosen.activities.DosenPemberitahuanActivity;
import org.d3ifcool.dosen.activities.DosenProfilActivity;
import org.d3ifcool.finpro.R;
import org.d3ifcool.superuser.fragments.KoorDosenFragment;
import org.d3ifcool.superuser.fragments.KoorInformasiFragment;
import org.d3ifcool.superuser.fragments.KoorJudulPaSubdosenFragment;
import org.d3ifcool.superuser.fragments.KoorMahasiswaFragment;
import org.d3ifcool.superuser.fragments.KoorProyekAkhirFragment;

public class AdminMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private KoorDosenFragment koorDosenFragment = new KoorDosenFragment();
    private KoorInformasiFragment koorInformasiFragment = new KoorInformasiFragment();
    private KoorMahasiswaFragment koorMahasiswaFragment = new KoorMahasiswaFragment();
    private KoorJudulPaSubdosenFragment koorJudulPaSubdosenFragment = new KoorJudulPaSubdosenFragment();
    private KoorProyekAkhirFragment koorProyekAkhirFragment = new KoorProyekAkhirFragment();

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

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.toolbar_menu_pemberitahuan:
                Intent intentPemberitahuan = new Intent(AdminMainActivity.this, DosenPemberitahuanActivity.class);
                startActivity(intentPemberitahuan);
                break;
            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(AdminMainActivity.this, DosenProfilActivity.class);
                startActivity(intentProfil);
                break;
            case R.id.toolbar_menu_keluar:
                Intent intentKeluar = new Intent(AdminMainActivity.this, LoginActivity.class);
                startActivity(intentKeluar);
                finish();
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

}
