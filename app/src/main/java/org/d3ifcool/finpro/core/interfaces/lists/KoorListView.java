package org.d3ifcool.finpro.core.interfaces.lists;

import org.d3ifcool.finpro.core.models.KoordinatorPa;

import java.util.List;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 24/03/2019.
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
public interface KoorListView {

    void showProgress();

    void hideProgress();

    void onGetListKoor(List<KoordinatorPa> koordinatorPa);

    void isEmptyListKoor();

    void onFailed(String message);

}
