package org.d3ifcool.finpro.core.interfaces.objects;

import org.d3ifcool.finpro.core.models.User;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 03/03/2019.
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
public interface LoginView {

    void showProgress();

    void hideProgress();

    void onRequestSuccess(User user);

    void isEmptyObjectLogin();

    void onFailed(String message);

}
