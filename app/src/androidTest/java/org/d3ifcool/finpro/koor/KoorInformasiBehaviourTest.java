package org.d3ifcool.finpro.koor;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.KoorMainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 16/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.finpro.koor
 */
public class KoorInformasiBehaviourTest {

    @Rule
    public ActivityTestRule<KoorMainActivity> koorMainActivityTestRule = new ActivityTestRule<>(KoorMainActivity.class);

    @Before
    public void init(){
        koorMainActivityTestRule.getActivity();
    }

    @Test
    public void KoorTambahInformasi() throws InterruptedException {

        String JUDUL_1 = "[INFO][JADWAL SIDANG PROYEK AKHIR][BATCH JUNI]";
        String JUDUL_2 = "[INFO][PENDAFTARAN SIDANG PROYEK AKHIR]";

        String INFORMASI_1 = "Assalamu'alaikum Wr. Wb.\n" +
                "\n" +
                "Berikut adalah reminder jadwal sidang yang sudah dipublish pada web LAK FIT dari bulan Mei kemarin. \n" +
                "http://lak-fit.telkomuniversity.ac.id/wp-content/uploads/2019/05/publish-jadwal-sidang-Juni-2019-3.xlsx\n" +
                "Bagi kelompok yang sudah mendapatkan jadwal sidang agar untuk sesegera mungkin mengontak dosen pembimbing dan dosen penguji berupa informasi jadwal sidang, waktu sidang dan lokasi sidang.\n" +
                "Jangan lupa untuk mengambil Berita Acara Sidang di Layanan Akademik FIT, bisa diambil H-1 sebelum jadwal sidang.  \n" +
                "\n" +
                "Siapkan keseluruhan berkas dan artefak sidang yang akan diserahkan kepada dosen pembimbing dan dosen penguji. Hal ini baiknya dilakukan paling lambat H-2 sebelum sidang atau tergantung kesepakatan dengan dosen bersangkutan. \n" +
                "\n" +
                "Semoga sidang anda diberikan kelancaran dan kemudahan, \n" +
                "aamiin.\n" +
                "\n" +
                "TIM PA";

        String INFORMASI_2 = "Assalamu'alaikum Wr. Wb.\n" +
                "\n" +
                "Diinformasikan bagi seluruh mahasiswa yang sudah melaksanakan kegiatan monev VI PA, silahkan untuk melakukan persiapan pendaftaran sidang PA yang akan dilaksanakan dari tanggal 22 Mei hingga 24- Mei.\n" +
                "\n" +
                "Berkas Pendaftaran sidang sudah menjadi salah satu persyaratan untuk mengikuti kegiatan monev VI kemarin, silahkan untuk memenuhi seluruh persyaratan yang sudah tertera pada berkas pendaftaran sidang tersebut.\n" +
                "\n" +
                "Terima kasih.";

//        new HelperTest().openDrawerKoor("Informasi");

        createInformasi(JUDUL_1, INFORMASI_1);
        createInformasi(JUDUL_2, INFORMASI_2);

    }

    private void createInformasi(String JUDUL, String INFORMASI) throws InterruptedException {
        onView(withId(R.id.frg_koor_info_home_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.act_koor_edittext_judul)).perform(typeText(JUDUL));
        pressBack();
        onView(withId(R.id.act_koor_edittext_deskripsi)).perform(typeText(INFORMASI));
        pressBack();
        onView(withId(R.id.act_koor_info_button_simpan)).perform(click());
        Thread.sleep(3000);
    }


}
