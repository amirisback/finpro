package org.d3ifcool.finpro.mahasiswa;

import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.HelperTest;
import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.MahasiswaMainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeUp;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
 * org.d3ifcool.finpro.mahasiswa
 */
public class MahasiswaJudulDosenBehaviourTest {

    @Rule
    public ActivityTestRule<MahasiswaMainActivity> mahasiswaActivityTestRule = new ActivityTestRule<>(MahasiswaMainActivity.class);

    @Before
    public void init(){
        mahasiswaActivityTestRule.getActivity();
    }

    @Test
    public void mahasiswaPengajuanJudul() throws InterruptedException {

        String JUDUL = "Dummy_Judul";
        String DESKRIPSI_JUDUL = "Dummy_Deskripsi";
        String NIM_2 = "6706160002";
        String NAMA_KELOMPOK = "Dummy_Kelompok";

        new HelperTest().mahasiswaMenuJudul();

        onView(withId(R.id.frg_mhs_pa_pengajuan_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.act_mhs_pa_mandiri_judul)).perform(typeText(JUDUL));
        pressBack();
        onView(withId(R.id.act_mhs_pa_mandiri_deskripsi)).perform(typeText(DESKRIPSI_JUDUL));
        pressBack();
        onView(withId(R.id.container_scroll)).perform(swipeUp());
        onView(withId(R.id.act_mhs_pa_mandiri_nim_anggota_2)).perform(typeText(NIM_2));
        pressBack();
        onView(withId(R.id.act_mhs_pa_mandiri_kelompok)).perform(typeText(NAMA_KELOMPOK));
        pressBack();
        Thread.sleep(1000);
        onView(withId(R.id.act_mhs_pa_mandiri_simpan)).perform(click());
        onView(withText("IYA")).perform(click());
        Thread.sleep(3000);

    }

}
