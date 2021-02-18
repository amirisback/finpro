package org.d3ifcool.finpro.core.interfaces.lists;

import org.d3ifcool.finpro.core.models.SiapSidang;

import java.util.List;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 28/06/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Github   : github.com/amirisback
 * LinkedIn : linkedin.com/in/faisalamircs
 * -----------------------------------------
 * FrogoBox Software Industries
 * org.d3ifcool.base.interfaces.lists
 */
public interface SiapSidangListView {

    void showProgress();

    void hideProgress();

    void onGetListSiapSidang(List<SiapSidang> siapSidangList);

    void isEmptyListSiapSidang();

    void onFailed(String message);


}
