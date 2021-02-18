package org.d3ifcool.finpro.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import org.d3ifcool.finpro.dosen.activities.DosenJadwalKegiatanActivity;
import org.d3ifcool.finpro.dosen.activities.DosenKuotaDosenActivity;
import org.d3ifcool.finpro.dosen.activities.DosenPemberitahuanActivity;
import org.d3ifcool.finpro.dosen.activities.DosenProfilActivity;
import org.d3ifcool.finpro.dosen.activities.DosenJudulPaArsipActivity;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenInformasiFragment;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenJudulFragment;
import org.d3ifcool.finpro.dosen.fragments.parent.DosenPaFragment;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.core.helpers.MethodHelper;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.core.interfaces.objects.DosenView;
import org.d3ifcool.finpro.core.models.Dosen;
import org.d3ifcool.finpro.core.presenters.DosenPresenter;

public class DosenMainActivity extends AppCompatActivity implements DosenView {

    private SessionManager sessionManager;

    private MethodHelper methodHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosen_main);

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);
        // -----------------------------------------------------------------------------------------
//        mViewPager = findViewById(R.id.act_dsn_home_viewpager);
//        DosenPagerAdapter mPagerAdapter = new DosenPagerAdapter(this, getSupportFragmentManager());
//        mViewPager.setAdapter(mPagerAdapter);

        //    private MenuItem prevMenuItem = null;
        //    private ViewPager mViewPager;
        BottomNavigationView bottomNavigationView = findViewById(R.id.act_dsn_home_bottom_navigation);

        DosenPresenter dosenPresenter = new DosenPresenter(this);
        dosenPresenter.initContext(this);

        sessionManager = new SessionManager(this);
        dosenPresenter.getDosenByParameter(sessionManager.getSessionUsername());

        openFragment(new DosenInformasiFragment());

        // -----------------------------------------------------------------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_menu_dsn_informasi:
//                        mViewPager.setCurrentItem(0);
                        openFragment(new DosenInformasiFragment());
                        setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_dsn_pa:
//                        mViewPager.setCurrentItem(1);
                        openFragment(new DosenPaFragment());
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case R.id.bottom_menu_dsn_judulpa:
//                        mViewPager.setCurrentItem(2);
                        openFragment(new DosenJudulFragment());
                        setTitle(R.string.title_judulpa);
                        break;

                }
                return true;
            }
        });

//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (prevMenuItem != null)
//                    prevMenuItem.setChecked(false);
//                else
//                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
//
//                bottomNavigationView.getMenu().getItem(position).setChecked(true);
//                prevMenuItem = bottomNavigationView.getMenu().getItem(position);
//
//                switch (position) {
//                    case 0:
//                        setTitle(R.string.title_informasi);
//                        break;
//                    case 1:
//                        setTitle(R.string.title_proyekakhir);
//                        break;
//                    case 2:
//                        setTitle(R.string.title_judulpa);
//                        break;
//
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });


    }

    private void openFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.toolbar_menu_pemberitahuan:
                Intent intentPemberitahuan = new Intent(DosenMainActivity.this, DosenPemberitahuanActivity.class);
                startActivity(intentPemberitahuan);
                break;

            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(DosenMainActivity.this, DosenProfilActivity.class);
                startActivity(intentProfil);
                break;

            case R.id.toolbar_menu_jadwal_kegiatan:
                Intent intentJadwal = new Intent(DosenMainActivity.this, DosenJadwalKegiatanActivity.class);
                startActivity(intentJadwal);
                break;

            case R.id.toolbar_menu_arsip_judul:
                Intent intentArsip = new Intent(DosenMainActivity.this, DosenJudulPaArsipActivity.class);
                startActivity(intentArsip);
                break;

            case R.id.toolbar_menu_keluar:

                new AlertDialog
                        .Builder(this)
                        .setTitle(getString(R.string.dialog_keluar_title))
                        .setMessage(getString(R.string.dialog_keluar_text))
                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentKeluar = new Intent(DosenMainActivity.this, LoginActivity.class);
                                startActivity(intentKeluar);
                                sessionManager.removeSession();
                                finish();
                            }
                        })

                        .setNegativeButton("Batal", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
                break;

            case R.id.toolbar_menu_tentang_kami:
                Intent intentTentangKami = new Intent(DosenMainActivity.this, TentangKamiActivity.class);
                startActivity(intentTentangKami);
                break;

            case R.id.toolbar_menu_kuota_dosen:
                Intent intentKuota = new Intent(DosenMainActivity.this, DosenKuotaDosenActivity.class);
                startActivity(intentKuota);
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
    public void onGetObjectDosen(Dosen dosen) {
        sessionManager.createSessionDataDosen(dosen);
    }

    @Override
    public void isEmptyObjectDosen() {

    }

    @Override
    public void onFailed(String message) {

    }
}
