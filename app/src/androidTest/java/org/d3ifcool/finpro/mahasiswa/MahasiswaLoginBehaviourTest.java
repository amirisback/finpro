package org.d3ifcool.finpro.mahasiswa;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

import org.d3ifcool.finpro.R;
import org.d3ifcool.finpro.activities.LoginActivity;
import org.d3ifcool.finpro.activities.MahasiswaMainActivity;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 12/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */

public class MahasiswaLoginBehaviourTest {

    @Rule
    public ActivityTestRule<LoginActivity> loginActivityTestRule = new ActivityTestRule<>(LoginActivity.class);
    public ActivityTestRule<MahasiswaMainActivity> mahasiswaActivityTestRule = new ActivityTestRule<>(MahasiswaMainActivity.class);

    @Before
    public void init(){
        loginActivityTestRule.getActivity();
        mahasiswaActivityTestRule.getActivity();
    }

    @Test
    public void loginTest() throws InterruptedException {
        String username = "6706160014";
        String password = "6706160014";

        onView(ViewMatchers.withId(R.id.act_main_edittext_username)).perform(typeText(username));
        onView(withId(R.id.act_main_edittext_password)).perform(typeText(password));
        pressBack();
        onView(withId(R.id.act_main_button_login)).perform(click());
        Thread.sleep(5000);

    }

    @Test
    public void logoutTest() throws InterruptedException {
        Thread.sleep(2000);
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Keluar")).perform(click());
        onView(withText("KELUAR")).perform(click());
        Thread.sleep(2000);
    }

}
