package org.d3ifcool.finpro;

import androidx.test.espresso.contrib.DrawerActions;

import org.d3ifcool.finpro.R;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
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
 * org.d3ifcool.finpro.koor
 */
public class HelperTest {

    public HelperTest() {
    }

    public void openDrawerKoor(String MENU_DRAWER) throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(2000);
        onView(withText(MENU_DRAWER)).perform(click()); // Menu Drawer
        Thread.sleep(2000);
    }

    public void onClickTabLayout(String MENU_TAB) throws InterruptedException {
        Thread.sleep(2000);
        onView(withText(MENU_TAB)).perform(click()); // Menu Tab
        Thread.sleep(2000);
    }

    public void mahasiswaMenuJudul() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_mhs_judulpa)).perform(click());
        Thread.sleep(2000);
    }

    public void mahasiswaMenuProyekAkhir() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_mhs_proyekakhir)).perform(click());
        Thread.sleep(2000);
    }

    public void mahasiswaMenuInformasi() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_mhs_informasi)).perform(click());
        Thread.sleep(2000);
    }

    public void DosenMenuJudul() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_dsn_judulpa)).perform(click());
        Thread.sleep(2000);
    }

    public void DosenMenuProyekAkhir() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_dsn_pa)).perform(click());
        Thread.sleep(2000);
    }

    public void DosenMenuInformasi() throws InterruptedException {
        Thread.sleep(2000);
        onView(withId(R.id.bottom_menu_dsn_informasi)).perform(click());
        Thread.sleep(2000);
    }

}
