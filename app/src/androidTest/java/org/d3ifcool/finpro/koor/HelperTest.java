package org.d3ifcool.finpro.koor;

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

    public void openDrawer(String MENU_DRAWER) throws InterruptedException {
        Thread.sleep(3000);
        onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
        Thread.sleep(2000);
        onView(withText(MENU_DRAWER)).perform(click()); // Menu Drawer
        Thread.sleep(2000);
    }

}
