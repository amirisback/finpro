package org.d3ifcool.finpro.koor;

import android.view.Gravity;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.contrib.DrawerActions;
import androidx.test.rule.ActivityTestRule;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.KoorMainActivity;
import org.d3ifcool.finpro.activities.LoginActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeLeft;
import static androidx.test.espresso.action.ViewActions.swipeRight;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 15/06/2019.
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
public class KoorMahasiswaBehaviorTest {

    @Rule
    public ActivityTestRule<KoorMainActivity> koorMainActivityTestRule = new ActivityTestRule<>(KoorMainActivity.class);

    @Before
    public void init(){
        koorMainActivityTestRule.getActivity();
    }

    private void createMahasiswa(String NIM_MHS, String NAMA_MHS) throws InterruptedException {
        onView(withId(R.id.frg_koor_mahasiswa_home_fab)).perform(click());
        Thread.sleep(2000);
        onView(withId(R.id.act_koor_edittext_nim_mahasiswa)).perform(typeText(NIM_MHS));
        pressBack();
        onView(withId(R.id.act_koor_edittext_nama_mahasiswa)).perform(typeText(NAMA_MHS));
        pressBack();
        onView(withId(R.id.act_koor_mahasiswa_button_simpan)).perform(click());
        Thread.sleep(3000);
    }

    @Test
    public void KoorTambahMahasiswa() throws InterruptedException {

        String NIM_MHS_1 = "6706160014";
        String NIM_MHS_2 = "6706162062";

        String NAMA_MHS_1 = "Muhammad Faisal Amir";
        String NAMA_MHS_2 = "M. Ikhsan Ramadhan";

        Thread.sleep(3000);
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(2000);
        onView(withText("Mahasiswa")).perform(click()); // Menu Drawer
        Thread.sleep(2000);

        createMahasiswa(NIM_MHS_1, NAMA_MHS_1);
        createMahasiswa(NIM_MHS_2, NAMA_MHS_2);
    }

}
