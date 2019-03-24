package org.d3ifcool.service.interfaces;

import org.d3ifcool.service.models.Dosen;

import java.util.List;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * Finpro
 * Copyright (C) 04/03/2019.
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
public interface DosenViewResult {

    void showProgress();

    void hideProgress();

    void onGetResultDataDosen(List<Dosen> dosen);

    void onErrorLoading(String message);

}
