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

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaJadwalKegiatanActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaJudulPaArsipActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaKuotaDosenActivity;
import org.d3ifcool.finpro.mahasiswa.fragments.parent.MahasiswaInformasiFragment;
import org.d3ifcool.finpro.mahasiswa.fragments.parent.MahasiswaJudulPaFragment;
import org.d3ifcool.finpro.mahasiswa.fragments.parent.MahasiswaPaFragment;
import org.d3ifcool.finpro.core.helpers.SessionManager;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaPemberitahuanActivity;
import org.d3ifcool.finpro.mahasiswa.activities.MahasiswaProfilActivity;
import org.d3ifcool.finpro.core.interfaces.objects.MahasiswaView;
import org.d3ifcool.finpro.core.models.Mahasiswa;
import org.d3ifcool.finpro.core.presenters.MahasiswaPresenter;

public class MahasiswaMainActivity extends AppCompatActivity implements MahasiswaView {

    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa_main);

        setTitle(R.string.title_informasi);
        getSupportActionBar().setElevation(0);
        // -----------------------------------------------------------------------------------------
//        mViewPager = findViewById(R.id.act_mhs_home_viewpager);
//        MahasiswaPagerAdapter mPagerAdapter = new MahasiswaPagerAdapter(this, getSupportFragmentManager());
//        mViewPager.setAdapter(mPagerAdapter);

        //    private MenuItem prevMenuItem = null;
        //    private ViewPager mViewPager;
        BottomNavigationView bottomNavigationView = findViewById(R.id.act_mhs_home_bottom_navigation);
        sessionManager = new SessionManager(this);
        MahasiswaPresenter mahasiswaPresenter = new MahasiswaPresenter(this);
        mahasiswaPresenter.initContext(this);

        mahasiswaPresenter.getMahasiswaByParameter(sessionManager.getSessionUsername());



        openFragment(new MahasiswaInformasiFragment());

        // -----------------------------------------------------------------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.bottom_menu_mhs_informasi:
//                        mViewPager.setCurrentItem(0);
                        openFragment(new MahasiswaInformasiFragment());
                        setTitle(R.string.title_informasi);
                        break;
                    case R.id.bottom_menu_mhs_proyekakhir:
//                        mViewPager.setCurrentItem(1);
                        openFragment(new MahasiswaPaFragment());
                        setTitle(R.string.title_proyekakhir);
                        break;
                    case R.id.bottom_menu_mhs_judulpa:
//                        mViewPager.setCurrentItem(2);
                        openFragment(new MahasiswaJudulPaFragment());
                        setTitle(R.string.title_judulpa);
                        break;
                }
//                return false;
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
                Intent intentPemberitahuan = new Intent(MahasiswaMainActivity.this, MahasiswaPemberitahuanActivity.class);
                startActivity(intentPemberitahuan);
                break;

            case R.id.toolbar_menu_profil:
                Intent intentProfil = new Intent(MahasiswaMainActivity.this, MahasiswaProfilActivity.class);
                startActivity(intentProfil);
                break;

            case R.id.toolbar_menu_jadwal_kegiatan:
                Intent intentJadwal = new Intent(MahasiswaMainActivity.this, MahasiswaJadwalKegiatanActivity.class);
                startActivity(intentJadwal);
                break;

            case R.id.toolbar_menu_arsip_judul:
                Intent intentArsip = new Intent(MahasiswaMainActivity.this, MahasiswaJudulPaArsipActivity.class);
                startActivity(intentArsip);
                break;

            case R.id.toolbar_menu_kuota_dosen:
                Intent intentKuota = new Intent(MahasiswaMainActivity.this, MahasiswaKuotaDosenActivity.class);
                startActivity(intentKuota);
                break;

            case R.id.toolbar_menu_keluar:
                new AlertDialog
                        .Builder(this)
                        .setTitle(getString(R.string.dialog_keluar_title))
                        .setMessage(getString(R.string.dialog_keluar_text))

                        .setPositiveButton("Keluar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intentKeluar = new Intent(MahasiswaMainActivity.this, LoginActivity.class);
                                startActivity(intentKeluar);
                                sessionManager.removeSession();
                                finish();
                            }
                        })

                        .setNegativeButton("Batal", null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
               ;
                break;

            case R.id.toolbar_menu_tentang_kami:
                Intent intentTentangKami = new Intent(MahasiswaMainActivity.this, TentangKamiActivity.class);
                startActivity(intentTentangKami);
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
    public void onGetObjectMahasiswa(Mahasiswa mahasiswa) {
        sessionManager.createSessionDataMahasiswa(mahasiswa);
    }

    @Override
    public void isEmptyObjectMahasiswa() {

    }

    @Override
    public void onFailed(String message) {

    }

}