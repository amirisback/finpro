package org.d3ifcool.service.interfaces;

import org.d3ifcool.service.models.Informasi;

import java.util.List;

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
public interface InformasiView {

    void showProgress();
    void hideProgress();
    void onGetResult(List<Informasi> informasi);
    void onErrorLoading(String message);

}
