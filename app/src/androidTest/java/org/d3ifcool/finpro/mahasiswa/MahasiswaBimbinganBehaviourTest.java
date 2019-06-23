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
public class MahasiswaBimbinganBehaviourTest {

    @Rule
    public ActivityTestRule<MahasiswaMainActivity> mahasiswaActivityTestRule = new ActivityTestRule<>(MahasiswaMainActivity.class);

    @Before
    public void init(){
        mahasiswaActivityTestRule.getActivity();
    }

    @Test
    public void mahasiswaBimbingan() throws InterruptedException {

        String BIMBINGAN = "Bimbingan ke - ";

        new HelperTest().mahasiswaMenuProyekAkhir();

        onView(withId(R.id.enable_view)).perform(swipeUp());

        onView(withId(R.id.frg_mhs_pa_cardview_bimbingan)).perform(click());
        Thread.sleep(2000);

        for (int i = 0; i < 16; i++) {
            String DES_BIMBINGAN = BIMBINGAN + (i+1);
            createBimbingan(DES_BIMBINGAN);
        }

    }

    public void createBimbingan(String BIMBINGAN) throws InterruptedException {
        onView(withId(R.id.act_mhs_bimbingan_tambah)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.act_mhs_info_edittext_konten)).perform(swipeUp());
        onView(withId(R.id.act_mhs_info_edittext_konten)).perform(typeText(BIMBINGAN));
        pressBack();
        onView(withId(R.id.act_mhs_info_button_simpan)).perform(click());
        Thread.sleep(2000);

    }

}
